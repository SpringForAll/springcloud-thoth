package com.prometheus.thoth.robot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * created by sunliangliang
 */
@ApiModel
public class RobotLibDto {
    private Long robotId;
    @ApiModelProperty("库id，数组")
    @NotNull
    private List<Long> libIds;

    public List<Long> getLibIds() {
        return libIds;
    }

    public void setLibIds(List<Long> libIds) {
        this.libIds = libIds;
    }

    public Long getRobotId() {
        return robotId;
    }

    public void setRobotId(Long robotId) {
        this.robotId = robotId;
    }
}
