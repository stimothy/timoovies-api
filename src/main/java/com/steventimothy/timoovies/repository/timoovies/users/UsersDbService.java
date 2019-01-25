package com.steventimothy.timoovies.repository.timoovies.users;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timoovies.repository.config.DbConfig;
import com.steventimothy.timoovies.repository.schema.DataUser;
import com.steventimothy.timoovies.repository.timoovies.TimooviesDbService;
import com.steventimothy.timoovies.repository.timoovies.config.TimooviesDbConfig;
import com.steventimothy.timoovies.repository.timoovies.users.config.UsersDbConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.Instant;

/**
 * <h1>The UsersDbService Class</h1>
 * <p>This class is responsible for talking with the users table in the database.</p>
 */
@Slf4j
@Component
class UsersDbService extends TimooviesDbService {

  /**
   * The config for the users table.
   */
  private UsersDbConfig dbConfig;

  /**
   * Inserts a user into the users table.
   *
   * @param dataUser the user to insert.
   * @return The id of the user after inserting.
   */
  public Integer insert(DataUser dataUser) {
    //Do some initial setup and update the timestamps.
    Integer id = null;
    dataUser.date_created(Instant.now());
    dataUser.last_modified(dataUser.date_created());

    //Open a connection with the database.
    Connection connection = openConnection();

    try {
      //Insert the user.
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dbConfig.getTableName() + " VALUES(?, ?, ?, ?, ?)");
      preparedStatement.setInt(1, dataUser.id());
      preparedStatement.setString(2, dataUser.username());
      preparedStatement.setString(3, dataUser.enc_password());
      preparedStatement.setTimestamp(4, Timestamp.from(dataUser.date_created()));
      preparedStatement.setTimestamp(5, Timestamp.from(dataUser.date_created()));

      //Execute the statement
      preparedStatement.executeUpdate();

      //Get the id back for the inserted user.
      preparedStatement = connection.prepareStatement("SELECT u.id FROM users u WHERE u.username = ?");
      preparedStatement.setString(1, dataUser.username());

      //Execute the statement
      ResultSet resultSet = preparedStatement.executeQuery();

      //Get the id.
      if (resultSet.next()) {
        id = resultSet.getInt("id");
      }
    }
    catch (SQLException ex) {
      log.error("Could not insert dataUser into the database.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return id;
  }

  /**
   * The constructor that receives the autowired components.
   *
   * @param dbConfig          The dbms config.
   * @param mysqlDataSource   The datasource object for dbms.
   * @param timooviesDbConfig The timoobies db config.
   * @param usersDbConfig     The users table config.
   */
  @Autowired
  public UsersDbService(DbConfig dbConfig, MysqlDataSource mysqlDataSource, TimooviesDbConfig timooviesDbConfig, UsersDbConfig usersDbConfig) {
    super(dbConfig, mysqlDataSource, timooviesDbConfig);
    this.dbConfig = usersDbConfig;
  }
}
