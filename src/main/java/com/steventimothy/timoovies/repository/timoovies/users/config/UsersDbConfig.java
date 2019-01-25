package com.steventimothy.timoovies.repository.timoovies.users.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <h1>The UsersDbConfig Class</h1>
 * <p>This class holds the properties of the users table.</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "db.timoovies.users")
@PropertySource("classpath:db.properties")
@Component
public class UsersDbConfig {

  /**
   * The table name value.
   */
  private String tableName;
}
