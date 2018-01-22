package com.tanb.commpt.core.global;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Tanbo on 2018/1/11.
 *
 * 可以实现分布式事务jdbcTemplate ,但此时使用mybatis时还需增加SqlSessionFactoryBean等配置
 */
@Configuration
public class DataSourceConfig {

    @Resource
    private Environment env;

    /**
     * 配置数据源
     **/

    @Bean(name = "dataSource1")
    @Primary
    public DataSource dataSource1() throws SQLException {
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setUniqueResourceName("dataSource1");
        xaDataSource.setXaDataSourceClassName(env.getProperty("spring.datasource.oracle.xa.driver-class-name"));

        Properties xaProperties = new Properties();
        xaProperties.setProperty("URL", env.getProperty("spring.datasource.url"));
        xaProperties.setProperty("user", env.getProperty("spring.datasource.username"));
        xaProperties.setProperty("password", env.getProperty("spring.datasource.password"));
        xaDataSource.setXaProperties(xaProperties);
        return xaDataSource;
    }

    @Bean(name = "dataSource2")
    public DataSource dataSource2() throws SQLException {
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setUniqueResourceName("dataSource2");
        xaDataSource.setXaDataSourceClassName(env.getProperty("spring.datasource.mysql.xa.driver-class-name"));

        Properties xaProperties = new Properties();
        xaProperties.setProperty("URL", env.getProperty("spring.datasource.mysql.url"));
        xaProperties.setProperty("user", env.getProperty("spring.datasource.mysql.username"));
        xaProperties.setProperty("password", env.getProperty("spring.datasource.mysql.password"));
        xaDataSource.setXaProperties(xaProperties);
        return xaDataSource;
    }

    @Bean("jdbcTemplate1")
    @Primary
    public JdbcTemplate jdbcTemplate1(@Qualifier("dataSource1") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("jdbcTemplate2")
    public JdbcTemplate jdbcTemplate2(@Qualifier("dataSource2") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



//    @Bean(name = "sqlSessionFactory1")
//    @Primary
//    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") DataSource dataSource)
//            throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
//
//    @Bean(name = "sqlSessionTemplate1")
//    @Primary
//    public SqlSessionTemplate sqlSessionTemplate1(
//            @Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    @Bean(name = "sqlSessionFactory2")
//    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSource2") DataSource dataSource)
//            throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
//
//    @Bean(name = "sqlSessionTemplate2")
//    public SqlSessionTemplate testSqlSessionTemplate(
//            @Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }


    /***
     * atomikos
     *
     * @return
     */
//    @Bean(name = "userTransaction")
//    public UserTransaction userTransaction() throws Throwable {
//        UserTransactionImp userTransactionImp = new UserTransactionImp();
//        userTransactionImp.setTransactionTimeout(10000);
//        return userTransactionImp;
//    }
//
//    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
//    public TransactionManager atomikosTransactionManager() throws Throwable {
//        UserTransactionManager userTransactionManager = new UserTransactionManager();
//        userTransactionManager.setForceShutdown(false);
//        return userTransactionManager;
//    }
//
//    @Bean(name = "transactionManager")
//    @DependsOn({"userTransaction", "atomikosTransactionManager"})
//    public PlatformTransactionManager transactionManager() throws Throwable {
//        UserTransaction userTransaction = userTransaction();
//        JtaTransactionManager manager = new JtaTransactionManager(userTransaction, atomikosTransactionManager());
//        return manager;
//    }

}
