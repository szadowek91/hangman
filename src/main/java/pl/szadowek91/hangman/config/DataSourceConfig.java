package pl.szadowek91.hangman.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(Properties.SPRING_DATASOURCE_USERNAME)
                .password(Properties.SPRING_DATASOURCE_PASSWORD)
                .url(Properties.SPRING_DATASOURCE_URL)
                .driverClassName(Properties.DRIVER_CLASS_NAME)
                .build();
    }
}
