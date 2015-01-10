package by.fly.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
@PropertySource(value = PropertyConfig.PROPERTIES_CLASSPATH_PATH)
@PropertySource(value = PropertyConfig.PROPERTIES_PATH, ignoreResourceNotFound = true)
public class PropertyConfig {

    public static final String PROPERTIES = "application.properties";
    public static final String PROPERTIES_CLASSPATH_PATH = "classpath:" + PROPERTIES;
    public static final String PROPERTIES_PATH = "file:${user.dir}/" + PROPERTIES;
    public static final String PROPERTIES_FILE_PATH = System.getProperty("user.dir") + File.separator + PROPERTIES;

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = PROPERTIES)
    public PropertiesFactoryBean applicationProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new FileSystemResource(PROPERTIES_FILE_PATH));
        bean.setIgnoreResourceNotFound(true);
        return bean;
    }

}