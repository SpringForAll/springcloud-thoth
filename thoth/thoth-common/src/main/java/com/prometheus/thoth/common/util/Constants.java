package com.prometheus.thoth.common.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sunliangliang on 2017/2/17.
 * 数据字典
 */
public class Constants {
    // 系统过期时间:2099-12-30 00:00:00
    public static final Long PACKAGE_SYSTEM_EXPIRE_TIME = 4102243200000L;

    //内部调用Rpc接口
    public static final int RPC_INVOKE_SUCCESS = 1;//成功

    /**
     * 会话
     */
    public static final String KAFKA_TOPIC_SESSION = "session";
    /**
     * 聊天记录
     */
    public static final String KAFKA_TOPIC_IM_MSG = "_msg";
}
