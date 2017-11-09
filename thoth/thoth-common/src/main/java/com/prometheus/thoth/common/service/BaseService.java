package com.prometheus.thoth.common.service;

import java.util.List;

/**
 * Created by liangliang on 2017/02/16.
 */
public interface BaseService<T> {

    Boolean save(T entity);

    Boolean update(T entity);

    Boolean delete(Long id);

    T getById(Long id);

    List<T> listAll();

    List<T> listAll(T entity);
}
