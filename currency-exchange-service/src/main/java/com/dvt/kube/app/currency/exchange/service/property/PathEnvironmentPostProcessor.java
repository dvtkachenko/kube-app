package com.dvt.kube.app.currency.exchange.service.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.core.env.StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME;

@Order(Ordered.LOWEST_PRECEDENCE)
public class PathEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Object properties = environment.getPropertySources();
        System.out.println(properties);

        PropertySource<?> system = environment.getPropertySources()
                .get(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME);
//        if (!hasOurPriceProperties(system)) {
//            // error handling code omitted
//        }
//        Map<String, Object> prefixed = names.stream()
//                .collect(Collectors.toMap(this::rename, system::getProperty));
//        environment.getPropertySources()
//                .addAfter(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, new MapPropertySource("prefixer", prefixed));
    }
}
