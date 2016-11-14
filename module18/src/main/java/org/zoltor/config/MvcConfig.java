package org.zoltor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.zoltor.db.h2.DataGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel Ordenko on 31/10/2016, 23:18.
 */
@Configuration
@EnableWebMvc
@ComponentScan({
        "org.zoltor.db.h2",
        "org.zoltor.constants",
        "org.zoltor.dao",
})
public class MvcConfig {

    @Bean
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> messageConverterList = new ArrayList<>();
        messageConverterList.add(new Jaxb2CollectionHttpMessageConverter());
        messageConverterList.add(new MappingJackson2HttpMessageConverter());
        requestMappingHandlerAdapter.setMessageConverters(messageConverterList);
        return requestMappingHandlerAdapter;
    }

    @Bean
    public DataGenerator getDataGenerator() {
        return new DataGenerator();
    }

}
