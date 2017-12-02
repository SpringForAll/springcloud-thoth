package com.prometheus.thoth.ai.entity;

import java.io.Serializable;

/**
 * created by sunliangliang
 * 聊天记录
 */
public class ChatRecord implements Serializable{
    private String msgId;
    /** 消息唯一标识 */

    /* 消息发送者，即访客id */
    private String visitorId;

    /* 机器人id，即消息接收者 */
    private Long robotId;

    /* 机器人名称 */
    private String robotName;

    /** 消息内容 */
    private String content;

    /** 创建时间 */
    private Long gmtCreate;

    /** 更新时间*/
    private Long gmtModified;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public Long getRobotId() {
        return robotId;
    }

    public void setRobotId(Long robotId) {
        this.robotId = robotId;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
