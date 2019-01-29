package com.steventimothy.timoovies.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * <h1>The ApplicationConfig Class</h1>
 * <p>This class is in charge of finding and creating beans for autowiring.</p>
 */
@EnableAutoConfiguration
@ComponentScan
@Configuration
public class ApplicationConfig {

  /**
   * This method returns the bean restTemplate.
   *
   * @param restTemplateBuilder The builder to build a restTemplate.
   * @return The restTemplate bean.
   */
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    RestTemplate restTemplate = restTemplateBuilder
        .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
        .build();

    restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter(), new FormHttpMessageConverter()));

    return restTemplate;
  }

  /**
   * This method returns a bean of the mysqlDataSource object.
   *
   * @return The mysqlDataSource bean.
   */
  @Bean
  public MysqlDataSource mysqlDataSource() {
    return new MysqlDataSource();
  }
}
