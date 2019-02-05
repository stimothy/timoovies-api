package com.steventimothy.timoovies.repository.timoovies;

import com.steventimothy.timoovies.repository.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The TimooviesDbService Class</h1>
 * <p>This class is responsible for talking with the timoovies database and establishing connections.</p>
 */
@Slf4j
@Component
public abstract class TimooviesDbService extends DbService {

//  /**
//   * The timoovies database config.
//   */
//  private TimooviesDbConfig dbConfig;
//
//  /**
//   * Open a connection with the database.
//   *
//   * @return The connection to the database.
//   */
//  protected Connection openConnection() {
//    return super.openConnection(dbConfig.getDatabaseName());
//  }
//
//  /**
//   * Close the connection with the database.
//   *
//   * @param connection The connection to close.
//   */
//  @Override
//  protected void closeConnection(Connection connection) {
//    super.closeConnection(connection);
//  }
//
//  /**
//   * The constructor the set up the injections.
//   *
//   * @param dbConfig          The dbms config class.
//   * @param mysqlDataSource   The datasource for connecting with a database.
//   * @param timooviesDbConfig The timoovies db config class.
//   */
//  public TimooviesDbService(DbConfig dbConfig, TimooviesDbConfig timooviesDbConfig, MysqlDataSource mysqlDataSource) {
//    super(dbConfig, mysqlDataSource);
//    this.dbConfig = timooviesDbConfig;
//  }
}
