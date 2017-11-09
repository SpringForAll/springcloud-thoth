package com.prometheus.thoth.data.hbase.demo.controller;

import com.prometheus.thoth.data.hbase.demo.entity.UserInfo;
import com.prometheus.thoth.data.hbase.demo.service.HbaseAccountInfoService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by liangliang on 2017/04/11.
 *
 * @author liangliang
 * @since 2017/04/11
 */
@Controller
public class HbaseAccountController {

    private final static String TABLE_NAME = "user";

    private final static String FAMILY_INFO = "info";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HbaseAccountInfoService hbaseAccountInfoService;

    @RequestMapping(value = "/user/get/{rowKey}")
    public String get(@PathVariable String rowKey, Model model) {
        UserInfo userInfo = hbaseAccountInfoService.getUserInfo(TABLE_NAME, FAMILY_INFO,
                rowKey);

        logger.debug("get :{}", JSON.toJSONString(userInfo));
        if (userInfo == null) {
            userInfo = new UserInfo();
        }

        model.addAttribute("userInfo", userInfo);
        return "userInfo/info";

    }

    @RequestMapping(value = "/user/list")
    public String list(Model model) {
        List<UserInfo> userInfos = hbaseAccountInfoService.listUserInfos(TABLE_NAME, FAMILY_INFO);

        model.addAttribute("userInfos", userInfos);

        return "userInfo/list";

    }
}
