package com.prometheus.thoth.data.hbase.core;

import com.prometheus.thoth.data.hbase.exception.HbaseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutatorParams;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liangliang on 2017/04/10.
 *
 * @author liangliang
 * @since 2017/04/10
 */
public class HbaseTemplate implements HbaseOperations {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Configuration configuration;

    /** 初始化线程 */
    private int initialSize = 10;

    /** 默认最大的 */
    private int maximumPoolSize = 100;

    /** 默认60s */
    private long keepAliveTime = 60L;

    private volatile Connection connection;

    public HbaseTemplate(Configuration configuration) {
        this.setConfiguration(configuration);
        Assert.notNull(configuration, " a valid configuration is required");
    }

    public HbaseTemplate(Configuration configuration, int initialSize, int maximumPoolSize, long keepAliveTime) {
        this(configuration);
        this.initialSize = initialSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    @Override
    public <T> T execute(String tableName, TableCallback<T> callback) {
        Assert.notNull(callback, "Callback object must not be null");
        Assert.notNull(tableName, "No table specified");

        StopWatch sw = new StopWatch();
        sw.start();
        Table table = null;
        try {
            table = this.getConnection().getTable(TableName.valueOf(tableName));
            return callback.doInTable(table);
        } catch (Throwable throwable) {
            logger.error("hbase execute error", throwable);
            throw new HbaseException(throwable);
        } finally {
            if (null != table) {
                try {
                    table.close();
                    sw.stop();
                } catch (IOException e) {
                    logger.error("hbase close fialure!");
                }
            }
        }
    }

    @Override
    public <T> List<T> find(String tableName, String family, RowMapper<T> mapper) {
        Scan scan = new Scan();
        scan.setCaching(5000);
        scan.addFamily(Bytes.toBytes(family));
        return this.find(tableName, scan, mapper);
    }

    @Override
    public <T> List<T> find(String tableName, String family, String qualifier, RowMapper<T>
            mapper) {
        Scan scan = new Scan();
        scan.setCaching(5000);
        scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
        return this.find(tableName, scan, mapper);
    }

    @Override
    public <T> List<T> find(String tableName, final Scan scan, final RowMapper<T> mapper) {
        return this.execute(tableName, new TableCallback<List<T>>() {
            @Override
            public List<T> doInTable(Table table) throws Throwable {
                int caching = scan.getCaching();
                // 如果caching未设置(默认是1)，将默认配置成5000
                if (caching == 1) {
                    scan.setCaching(5000);
                }
                ResultScanner scanner = table.getScanner(scan);
                try {
                    List<T> rs = new ArrayList<T>();
                    int rowNum = 0;
                    for (Result result : scanner) {
                        rs.add(mapper.mapRow(result, rowNum++));
                    }
                    return rs;
                } finally {
                    scanner.close();
                }
            }
        });
    }

    @Override
    public <T> T get(String tableName, String rowKey, RowMapper<T> mapper) {
        return this.get(tableName, rowKey, null, null, mapper);
    }

    @Override
    public <T> T get(String tableName, String rowKey, String familyName, RowMapper<T> mapper) {
        return this.get(tableName, rowKey, familyName, null, mapper);
    }

    @Override
    public <T> T get(String tableName, final String rowKey, final String familyName, final
    String qualifier, final RowMapper<T> mapper) {
        return this.execute(tableName, new TableCallback<T>() {
            @Override
            public T doInTable(Table table) throws Throwable {
                Get get = new Get(Bytes.toBytes(rowKey));
                if (StringUtils.isNotBlank(familyName)) {
                    byte[] family = Bytes.toBytes(familyName);
                    if (StringUtils.isNotBlank(qualifier)) {
                        get.addColumn(family, Bytes.toBytes(qualifier));
                    } else {
                        get.addFamily(family);
                    }
                }
                Result result = table.get(get);
                if (result.isEmpty()) {
                    return null;
                }
                return mapper.mapRow(result, 0);
            }
        });
    }

    @Override
    public void execute(String tableName, MutatorCallback action) {
        Assert.notNull(action, "Callback object must not be null");
        Assert.notNull(tableName, "No table specified");

        StopWatch sw = new StopWatch();
        sw.start();
        BufferedMutator mutator = null;
        try {
            BufferedMutatorParams mutatorParams = new BufferedMutatorParams(TableName.valueOf
                    (tableName));
            mutator = this.getConnection().getBufferedMutator(mutatorParams.writeBufferSize(3 *
                    1024 * 1024));
            action.doInMutator(mutator);
        } catch (Throwable throwable) {
            sw.stop();
            throw new HbaseException(throwable);
        } finally {
            if (null != mutator) {
                try {
                    mutator.flush();
                    mutator.close();
                    sw.stop();
                } catch (IOException e) {
                    logger.error("hbase mutator close failure!");
                }
            }
        }
    }

    @Override
    public void saveOrUpdate(String tableName, final Mutation mutation) {
        this.execute(tableName, new MutatorCallback() {
            @Override
            public void doInMutator(BufferedMutator mutator) throws Throwable {
                mutator.mutate(mutation);
            }
        });
    }

    @Override
    public void saveOrUpdates(String tableName, final List<? extends Mutation> mutations) {
        this.execute(tableName, new MutatorCallback() {
            @Override
            public void doInMutator(BufferedMutator mutator) throws Throwable {
                mutator.mutate(mutations);
            }
        });
    }

    protected Connection getConnection() {
        if (null == this.connection) {
            synchronized (this) {
                if (null == this.connection) {
                    try {
                        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(initialSize,
                                maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new
                                SynchronousQueue<Runnable>());
                        // init pool
                        poolExecutor.prestartCoreThread();
                        this.connection = ConnectionFactory.createConnection(configuration,
                                poolExecutor);
                    } catch (IOException e) {
                        logger.error("hbase connection pool failure!");
                    }
                }
            }
        }
        return this.connection;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
