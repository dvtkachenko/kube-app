package com.dvt.kube.app.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// for OracleHibernateConfiguration should use exclude
//@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
//        assert(ctx != null);
//        logger.info("Let's inspect the beans provided by Spring Boot:");
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        Stream.of(beanNames).forEach(logger::info);
//        logger.info("Total beans provided by Spring Boot: " + beanNames.length);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            Stream.of(beanNames).forEach(beanName -> System.out.println(beanName));
//        };
//    }
}
