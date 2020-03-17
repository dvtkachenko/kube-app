package com.dvt.kube.app.user.service.config;

//import oracle.jdbc.pool.OracleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.Properties;

//@Configuration
//@ConfigurationProperties("oracle")
public class OracleJpaConfiguration {

    private static Logger logger = LoggerFactory.getLogger(OracleJpaConfiguration.class);

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
//
//    @Bean
//    public PlatformTransactionManager  transactionManager(EntityManagerFactory emf) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//        return transactionManager;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan(new String[] { "io.spring.boot.entity"});
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additionalProperties());
//        return em;
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
