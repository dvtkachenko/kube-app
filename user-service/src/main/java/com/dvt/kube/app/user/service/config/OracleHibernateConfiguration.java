package com.dvt.kube.app.user.service.config;

//import oracle.jdbc.pool.OracleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.Properties;

//@Configuration
//@ConfigurationProperties("oracle")
public class OracleHibernateConfiguration {

    private static Logger logger = LoggerFactory.getLogger(OracleHibernateConfiguration.class);

//    @Autowired
//    public DataSource dataSource;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String url;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    @Bean
//    DataSource dataSource() {
//        try {
//            OracleDataSource dataSource = new OracleDataSource();
//            dataSource.setUser(username);
//            dataSource.setPassword(password);
//            dataSource.setURL(url);
//            dataSource.setImplicitCachingEnabled(true);
//            dataSource.setFastConnectionFailoverEnabled(true);
//            return dataSource;
//        } catch (SQLException e) {
//            logger.error("Oracle data source bean can not be created !", e);
//            return null;
//        }
//    }

//    @Bean
//    public HibernateTransactionManager transactionManager(EntityManagerFactory emf) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(new String[] { "io.spring.boot.entity"});
//        sessionFactory.setHibernateProperties(additionalProperties());
//
//        logger.info("Hibernate session factory is created !", sessionFactory);
//
//        return sessionFactory;
//    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }
}
