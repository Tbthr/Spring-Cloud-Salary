package com.salary.config;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryConfig {

    private DruidDataSource dataSource;

    private static ConnectionFactoryConfig connectionFactoryConfig;

    // 账号
    String public_key_user = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJndiNDBwCn2ZCqJTUh/PHvzkhB7FzUERYyHpt1Pj7xzEc5caboRA3E9oztMuFfVimnYlGP++kh1Buc/EI0L/JECAwEAAQ==";
    String encode_user = "kwZgPTM2Ep2a9b+eXqKwFIqSmB100pFUkx9qq8/Q91MKAqyqnoJSqOFEeGuWteVRtXFCq0rCBmWLitibPyexwA==";
    String user = ConfigTools.decrypt(public_key_user, encode_user);
    // 密码
    String public_key_psd = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJCdWsohJ0A9NeV/TFcd9fCpj5+1rXxTrsRfj/uG/Kn28xt5LUwweBRGJMpevd5fO1LCygAeyDTVbhvWBPvMH4sCAwEAAQ==";
    String encode_psd = "buCGzcaNvjMfzJLSv2l7+C0v26qc3Ix/xyn/P1gfh5f7skdXfPeAzi1bhO4W+Ym0lBkwmMGsE8s2nZTX9nRe4A==";
    String psd = ConfigTools.decrypt(public_key_psd, encode_psd);

    public ConnectionFactoryConfig() throws Exception {
    }

    private Connection getConnection() throws SQLException {
        System.out.println("-------------------------");
        Properties properties = new Properties();
        String url = "jdbc:mysql://47.92.165.44:3306/salary?serverTimezone=GMT";
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        properties.put("driverClassName", driverClassName);
        properties.put("url", url);
        properties.put("username", user);
        properties.put("password", psd);

        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            try {
                init();
            } catch (Exception ignored) {
            }
        }
        return dataSource.getConnection();
    }

    public static Connection getDatabaseConnection() throws Exception {
        if (connectionFactoryConfig == null) {
            connectionFactoryConfig = new ConnectionFactoryConfig();
        }
        return connectionFactoryConfig.getConnection();

    }

    public void init() {
        try {
            if (dataSource != null)
                dataSource.close();
        } catch (Exception ignored) {
        }
    }
}