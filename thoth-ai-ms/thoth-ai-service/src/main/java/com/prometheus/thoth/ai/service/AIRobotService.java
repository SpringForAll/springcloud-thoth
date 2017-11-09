package com.prometheus.thoth.ai.service;

import com.prometheus.thoth.ai.entity.AIResult;

/**
 * created by sunliangliang
 */
public interface AIRobotService<T> {
    AIResult  getAnswer(T t);
}
