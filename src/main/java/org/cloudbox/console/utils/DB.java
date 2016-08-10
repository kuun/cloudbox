package org.cloudbox.console.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.cloudbox.console.app.AppConfiguration;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Created by kevin on 7/27/16.
 */
public class DB {
    private static final Logger log = LoggerFactory.getLogger(DB.class);
    private static SqlSessionFactory sqlSessionFactory = null;

    public static void init(AppConfiguration conf) {
        String resource = "mybatis/config.xml";
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resource);
            PooledDataSource dataSource = new PooledDataSource(
                    conf.getDataSourceFactory().getDriverClass(),
                    conf.getDataSourceFactory().getUrl(),
                    conf.getDataSourceFactory().getUser(),
                    conf.getDataSourceFactory().getPassword()
            );
            Environment env = new Environment.Builder("dev")
                    .transactionFactory(new JdbcTransactionFactory())
                    .dataSource(dataSource)
                    .build();
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            sqlSessionFactory.getConfiguration().setEnvironment(env);

            // start h2 webserver
            try {
                Server.createWebServer("-webPort", "8082", "-webDaemon").start();
            } catch (SQLException e) {
                log.error("start h2 web server error: {}", ExceptionUtils.getStackTrace(e));
            }
        } catch (IOException e) {
            log.error("load resource error: {}", ExceptionUtils.getStackTrace(e));
        }
    }

    public static SqlSession getSqlSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }
}
