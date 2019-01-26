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
  Integer insert(DataUser dataUser) {
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

      id = getByUsername(dataUser.username(), connection).id();
    }
    catch (SQLException ex) {
      log.error("Could not insert dataUser into the database.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return id;
  }

  /**
   * Get a dataUser by id.
   *
   * @param id The id of the user to retrieve.
   * @return The dataUser retrieved by id, or null if it could not be found.
   */
  DataUser getById(Integer id) {
    DataUser dataUser = null;

    //Open a connection with the database.
    Connection connection = openConnection();

    try {
      //Get the dataUser with the id.
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.* FROM users u WHERE u.id = ?");
      preparedStatement.setInt(1, id);

      //Execute the statement
      ResultSet resultSet = preparedStatement.executeQuery();

      dataUser = getDataUserFromResultSet(resultSet);
    }
    catch (SQLException ex) {
      log.error("The user could not be retrieved from the database by id.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return dataUser;
  }

  /**
   * Get dataUser by username.
   *
   * @param username the username of the user to retrieve.
   * @return The dataUser retrieved or null if it didn't exist.
   */
  DataUser getByUsername(String username) {
    DataUser dataUser = null;

    //Open a connection with the database.
    Connection connection = openConnection();

    try {
      dataUser = getByUsername(username, connection);
    }
    catch (SQLException ex) {
      log.error("The user could not be retrieved from the database by username.", ex);
    }

    //Close the connection.
    closeConnection(connection);

    return dataUser;
  }

  /**
   * Updates a user in the database.
   *
   * @param dataUser The updated dataUser other than the last modified field.
   * @return True if it was successful, false otherwise.
   */
  Boolean update(DataUser dataUser) {
    int affectedRows = 0;
    dataUser.last_modified(Instant.now());

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET username = ?, enc_password = ?, last_modified = ? WHERE id = ?");
      preparedStatement.setString(1, dataUser.username());
      preparedStatement.setString(2, dataUser.enc_password());
      preparedStatement.setTimestamp(3, Timestamp.from(dataUser.last_modified()));
      preparedStatement.setInt(4, dataUser.id());


      affectedRows = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      log.error("The user could not be updated.", ex);
    }

    closeConnection(connection);

    return (affectedRows > 0);
  }

  /**
   * Deletes a user by id.
   *
   * @param id The id of the user to delete.
   * @return True if it was successful, false otherwise.
   */
  Boolean deleteById(Integer id) {
    int affectedRows = 0;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
      preparedStatement.setInt(1, id);

      affectedRows = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      log.error("The user could not be deleted from the database by id.", ex);
    }

    closeConnection(connection);

    return (affectedRows > 0);
  }

  /**
   * Deletes a user by username.
   *
   * @param username The username of the user to delete.
   * @return True if it was successful, false otherwise.
   */
  Boolean deleteByUsername(String username) {
    int affectedRows = 0;

    Connection connection = openConnection();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE username = ?");
      preparedStatement.setString(1, username);

      affectedRows = preparedStatement.executeUpdate();
    }
    catch (SQLException ex) {
      log.error("The user could not be deleted from the database by username.", ex);
    }

    closeConnection(connection);

    return (affectedRows > 0);
  }

  /**
   * Gets a dataUser by username helper method.
   *
   * @param username   the username of the user to retrieve.
   * @param connection The connection to the database.
   * @return The dataUser retrieved from the database or null if it couldn't.
   * @throws SQLException Throws if there was an sql exception on retrieving the data.
   */
  private DataUser getByUsername(String username, Connection connection) throws SQLException {
    DataUser dataUser = null;

    //Get the user with the id.
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.* FROM users u WHERE u.username = ?");
    preparedStatement.setString(1, username);

    //Execute the statement
    ResultSet resultSet = preparedStatement.executeQuery();

    return getDataUserFromResultSet(resultSet);
  }

  /**
   * Get a dataUser from a ResultSet.
   *
   * @param resultSet the resultSet to get the dataUser from.
   * @return The dataUser from the resultSet, null if it doesn't exist.
   * @throws SQLException Throws if there was a sql exception involved.
   */
  private DataUser getDataUserFromResultSet(ResultSet resultSet) throws SQLException {
    DataUser dataUser = null;

    //Get the dataUser.
    if (resultSet.next()) {
      dataUser = new DataUser()
          .id(resultSet.getInt("id"))
          .username(resultSet.getString("username"))
          .enc_password(resultSet.getString("enc_password"))
          .date_created(resultSet.getTimestamp("date_created").toInstant())
          .last_modified(resultSet.getTimestamp("last_modified").toInstant());
    }

    return dataUser;
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
