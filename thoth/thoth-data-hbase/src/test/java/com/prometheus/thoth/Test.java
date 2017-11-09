package com.prometheus.thoth;

import com.prometheus.thoth.data.hbase.demo.Info;

/**
 * Created by liangliang on 2017/04/22.
 *
 * @author liangliang
 * @since 2017/04/22
 */
public class Test {

    public static void main(String args[]) {
        Info<String> i1 = new Info<String>();      // 泛型类型为String
        i1.getVar();
    }
}
