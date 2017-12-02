package com.prometheus.thoth.ai.service;

import com.prometheus.thoth.ai.entity.Interlocution;
import com.prometheus.thoth.robot.entity.Robot;

import java.util.List;

/**
 * created by sunliangliang
 */
public interface RobotInterlocutionService {
    public String getAnswer(String question);
    public Interlocution getInterlocation(String question);
    public List<Robot>    listRobots();
}
