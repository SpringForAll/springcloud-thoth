package com.prometheus.thoth.data.hbase.core;

import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Scan;

import java.util.List;

/**
 * Interface that specifies a basic set of Hbase operations, implemented by {@link HbaseTemplate}.
 * Not often used, but a useful option to enhance testability, as it can easily be mocked or
 * stubbed.
 *
 * @author liangliang
 * @since 2017/04/10
 */
public interface HbaseOperations {

    /**
     * Executes the given action against the specified table handling resource management.
     * <p>
     * Application exceptions thrown by the action object get propagated to the caller (can only be
     * unchecked). Allows for returning a result object (typically a domain object or collection of
     * domain objects).
     *
     * @param tableName the target table
     * @param <T>       action type
     * @return the result object of the callback action, or null
     */
    <T> T execute(String tableName, TableCallback<T> callback);

    /**
     * Scans the target table, using the given column family.
     * The content is processed row by row by the given action, returning a list of domain objects.
     *
     * @param tableName target table
     * @param family    column family
     * @param mapper    row mapper handling the scanner results
     * @param <T>       action type
     * @return a list of objects mapping the scanned rows
     */
    <T> List<T> find(String tableName, String family, final RowMapper<T> mapper);

    /**
     * Scans the target table, using the given column family.
     * The content is processed row by row by the given action, returning a list of domain objects.
     *
     * @param tableName target table
     * @param family    column family
     * @param qualifier column qualifier
     * @param mapper    row mapper handling the scanner results
     * @param <T>       action type
     * @return a list of objects mapping the scanned rows
     */
    <T> List<T> find(String tableName, String family, String qualifier, final RowMapper<T> mapper);

    /**
     * Scans the target table using the given {@link Scan} object. Suitable for maximum control over
     * the scanning process. The content is processed row by row by the given action, returning a
     * list of domain objects.
     *
     * @param tableName target table
     * @param scan      table scanner
     * @param mapper    row mapper handling the scanner results
     * @param <T>       action type
     * @return a list of objects mapping the scanned rows
     */
    <T> List<T> find(String tableName, final Scan scan, final RowMapper<T> mapper);

    /**
     * Gets an individual row from the given table. The content is mapped by the given action.
     *
     * @param tableName target table
     * @param rowName   row name
     * @param mapper    row mapper
     * @param <T>       mapper type
     * @return object mapping the target row
     */
    <T> T get(String tableName, String rowName, final RowMapper<T> mapper);

    /**
     * Gets an individual row from the given table. The content is mapped by the given action.
     *
     * @param tableName  target table
     * @param rowName    row name
     * @param familyName column family
     * @param mapper     row mapper
     * @param <T>        mapper type
     * @return object mapping the target row
     */
    <T> T get(String tableName, String rowName, String familyName, final RowMapper<T> mapper);

    /**
     * Gets an individual row from the given table. The content is mapped by the given action.
     *
     * @param tableName  target table
     * @param rowName    row name
     * @param familyName family
     * @param qualifier  column qualifier
     * @param mapper     row mapper
     * @param <T>        mapper type
     * @return object mapping the target row
     */
    <T> T get(String tableName, final String rowName, final String familyName, final String
            qualifier, final RowMapper<T> mapper);

    /**
     * 执行put update or delete
     *
     * @param tableName
     * @param action
     */
    void execute(String tableName, MutatorCallback action);

    /**
     * @param tableName
     * @param mutation
     */
    void saveOrUpdate(String tableName, Mutation mutation);

    /**
     * @param tableName
     * @param mutations
     */
    void saveOrUpdates(String tableName, List<? extends Mutation> mutations);
}
