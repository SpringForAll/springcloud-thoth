package com.prometheus.thoth.data.kafka.partitioner;

import com.sun.org.apache.bcel.internal.generic.LDIV;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Kafka分区.
 *
 * @author liangliang
 * @since 2017/03/18
 */
public class SimplePartitioner implements Partitioner {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AtomicLong increment = new AtomicLong();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[]
            valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();

        long partition = -1;
        if (keyBytes != null) {
            partition = toPositive(Utils.murmur2(keyBytes)) % numPartitions;
            logger.debug("partition topic:{}, key:{}, partition:{}", topic, new String(keyBytes), partition);
        }

        //自增
        if (increment.get() == Long.MAX_VALUE) {
            increment.getAndSet(0);
        }
        long tempKey = increment.incrementAndGet();

        if (partition == -1) {
            partition = Math.abs(tempKey) % numPartitions;
        }
        logger.info("SimplePartitioner.partition topic:{}, key:{}, increment:{}, partition:{}", topic,
                    String.valueOf(key), tempKey, partition);
        return new Long(partition).intValue();
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }

    private int toPositive(int number) {
        return number & 0x7fffffff;
    }
}