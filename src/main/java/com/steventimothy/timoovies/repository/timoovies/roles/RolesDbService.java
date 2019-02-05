package com.steventimothy.timoovies.repository.timoovies.roles;

import com.steventimothy.timoovies.repository.timoovies.TimooviesDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The UsersDbService Class</h1>
 * <p>This class is responsible for talking with the users table in the database.</p>
 */
@Slf4j
@Component
public class RolesDbService extends TimooviesDbService {

//  /**
//   * The constructor that receives the autowired components.
//   *
//   * @param dbConfig          The dbms config.
//   * @param mysqlDataSource   The datasource object for dbms.
//   * @param timooviesDbConfig The timoobies db config.
//   * @param usersDbConfig     The users table config.
//   */
//  @Autowired
//  public RolesDbService(DbConfig dbConfig, TimooviesDbConfig timooviesDbConfig, RolesDbConfig usersDbConfig, MysqlDataSource mysqlDataSource) {
//    super(dbConfig, timooviesDbConfig, mysqlDataSource);
//    //this.dbConfig = usersDbConfig;
//  }
}
