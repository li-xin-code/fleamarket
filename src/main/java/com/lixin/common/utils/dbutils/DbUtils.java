package com.lixin.common.utils.dbutils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author com.lixin
 */
public class DbUtils {

    private static final DataSource DATA_SOURCE;

    static {
        try {
            InputStream in = DbUtils.class.getClassLoader()
                    .getResourceAsStream("datasource.properties");
            Properties properties = new Properties();
            properties.load(in);
            DATA_SOURCE = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException("druid连接池初始化失败...", e);
        }
    }

    private DbUtils() {
    }

    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }

    public static QueryRunner getRunner() {
        return new QueryRunner(DATA_SOURCE);
    }

}
