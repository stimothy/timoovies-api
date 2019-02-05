package com.steventimothy.timoovies.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The DbService Class</h1>
 * <p>This class is responsible for talking with the a database and establishing connections.</p>
 */
@Slf4j
@Component
public abstract class DbService {

//  /**
//   * This holds information needed to connect to the database.
//   */
//  private MysqlDataSource mysqlDataSource;
//  /**
//   * The dbms config class.
//   */
//  private DbConfig dbConfig;
//
//  /**
//   * This function opens a connection to the database.
//   *
//   * @param databaseName The name of the database to connect to.
//   * @return The connection of the database.
//   */
//  protected Connection openConnection(String databaseName) {
//    //Setup the connection variables.
//    mysqlDataSource.setServerName(dbConfig.getHost());
//    mysqlDataSource.setUser(dbConfig.getUser());
//    mysqlDataSource.setPassword(dbConfig.getPassword());
//    mysqlDataSource.setDatabaseName(databaseName);
//
//    try {
//      return mysqlDataSource.getConnection();
//    }
//    catch (SQLException e) {
//      throw new IllegalStateException("Could not connect to the database.");
//    }
//  }
//
//  /**
//   * This function closes a connection with a database.
//   *
//   * @param connection the connection to close.
//   */
//  protected void closeConnection(Connection connection) {
//    try {
//      connection.close();
//    }
//    catch (SQLException e) {
//      throw new IllegalStateException("Could not close the connection to the database.");
//    }
//  }
//
//  /**
//   * The constructor. It sets up some injections.
//   *
//   * @param dbConfig        The dbms config class.
//   * @param mysqlDataSource The datasource class used for connections.
//   */
//  public DbService(DbConfig dbConfig, MysqlDataSource mysqlDataSource) {
//    this.dbConfig = dbConfig;
//    this.mysqlDataSource = mysqlDataSource;
//  }
}
