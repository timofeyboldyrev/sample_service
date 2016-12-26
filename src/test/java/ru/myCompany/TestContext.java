package ru.myCompany;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import ru.myCompany.config.Context;

/**
 * Created by t.boldyrev on 26.12.2016.
 */
@Configuration
@Import(Context.class)
public class TestContext {

    @Bean
    public static PropertyPlaceholderConfigurer propertiesResolver() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("application.properties"));
        return ppc;
    }
}
