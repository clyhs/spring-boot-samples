package org.abigfish.mybatis.database;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库上下文切换
 * Created by yu on 2017/3/8.
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static List<String> dataSourceKeys =new ArrayList<>();

    /**
     * 设置当前数据库。
     *
     * @param dbType
     */
    public static void setDatasourceType(String dbType) {
        contextHolder.set(dbType);

    }

    /**
     * 取得当前数据源。
     *
     * @return
     */
    public static String getDatasourceType() {
        String str = contextHolder.get();
        return str;
    }

    /**
     * 清除上下文数据
     */
    public static void clearDatasourceType() {
        contextHolder.remove();
    }

    /**
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceKeys.contains(dataSourceId);
    }

}
