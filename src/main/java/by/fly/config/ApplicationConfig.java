package by.fly.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("by.fly")
@Import({PropertyConfig.class, MailConfig.class})
public class ApplicationConfig {
}
