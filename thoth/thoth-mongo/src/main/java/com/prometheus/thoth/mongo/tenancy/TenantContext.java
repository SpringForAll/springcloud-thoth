package com.prometheus.thoth.mongo.tenancy;

/**
 * Created by zhuhuaiqi on 2017/3/20.
 */
public class TenantContext {
    final public static String DEFAULT_TENANT = "rampage_dev";

    private static ThreadLocal<String> currentTenant = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return DEFAULT_TENANT;
        }
    };

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}

