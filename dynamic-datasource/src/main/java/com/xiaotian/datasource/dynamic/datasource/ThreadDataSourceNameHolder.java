package com.xiaotian.datasource.dynamic.datasource;

public class ThreadDataSourceNameHolder {

    private static final ThreadLocal<String> datasourceName = new ThreadLocal<>();

    public static void set(String dataSourceName) {
        datasourceName.set(dataSourceName);
    }

    public static String get() {
        return datasourceName.get();
    }
}
