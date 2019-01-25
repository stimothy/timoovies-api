package com.steventimothy.timoovies.repository.timoovies.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>The TimooviesDbConfig</h1>
 * <p>This class holds the properties for the timoovies database.</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "db.timoovies")
@PropertySource("classpath:db.properties")
@Component
public class TimooviesDbConfig {

  /**
   * The database name value.
   */
  private String databaseName;
}
