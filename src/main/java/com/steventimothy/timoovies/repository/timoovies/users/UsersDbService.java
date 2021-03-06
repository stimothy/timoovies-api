package com.steventimothy.timoovies.repository.timoovies.users;

import com.steventimothy.timoovies.repository.timoovies.TimooviesDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The UsersDbService Class</h1>
 * <p>This class is responsible for talking with the users table in the database.</p>
 */
@Slf4j
@Component
class UsersDbService extends TimooviesDbService {

//  /**
//   * The config for the users table.
//   */
//  private UsersDbConfig dbConfig;
//
//  /**
//   * Inserts a user into the users table.
//   *
//   * @param dataUser the user to insert.
//   * @return The id of the user after inserting.
//   */
//  Long insert(DataUser dataUser) {
//    //Do some initial setup and update the timestamps.
//    Long id = null;
//
//    //Open a connection with the database.
//    Connection connection = openConnection();
//
//    try {
//      //Insert the user.
//      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dbConfig.getTableName() + " VALUES(?, ?, ?, ?, ?)");
//      if (dataUser.id() == null) {
//        preparedStatement.setNull(1, Types.BIGINT);
//      }
//      else {
//        preparedStatement.setLong(1, dataUser.id());
//      }
//      preparedStatement.setString(2, dataUser.username());
//      preparedStatement.setString(3, dataUser.enc_password());
//      preparedStatement.setTimestamp(4, Timestamp.from(dataUser.date_created()));
//      preparedStatement.setTimestamp(5, Timestamp.from(dataUser.date_created()));
//
//      //Execute the statement
//      preparedStatement.executeUpdate();
//
//      id = getIdByUsername(dataUser.username(), connection);
//    }
//    catch (SQLException ex) {
//      log.error("Could not insert dataUser into the database.", ex);
//    }
//
//    //Close the connection.
//    closeConnection(connection);
//
//    return id;
//  }
//
//  /**
//   * Get a dataUser by id.
//   *
//   * @param id The id of the user to retrieve.
//   * @return The dataUser retrieved by id, or null if it could not be found.
//   */
//  DataUser getUser(Long id) {
//    DataUser dataUser = null;
//
//    //Open a connection with the database.
//    Connection connection = openConnection();
//
//    try {
//      //Get the dataUser with the id.
//      PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.id, u.username FROM " + dbConfig.getTableName() + " u WHERE u.id = ?");
//      preparedStatement.setLong(1, id);
//
//      //Execute the statement
//      ResultSet resultSet = preparedStatement.executeQuery();
//
//      //Get the dataUser.
//      if (resultSet.next()) {
//        dataUser = new DataUser()
//            .id(resultSet.getLong("id"))
//            .username(resultSet.getString("username"));
//      }
//    }
//    catch (SQLException ex) {
//      log.error("The user could not be retrieved from the database by id.", ex);
//    }
//
//    //Close the connection.
//    closeConnection(connection);
//
//    return dataUser;
//  }
//
//  /**
//   * Get a username by id.
//   *
//   * @param id The id of the username to retrieve.
//   * @return The username retrieved by id, or null if it could not be found.
//   */
//  String getUsername(Long id) {
//    String username = null;
//
//    //Open a connection with the database.
//    Connection connection = openConnection();
//
//    try {
//      //Get the username with the id.
//      PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.username FROM " + dbConfig.getTableName() + " u WHERE u.id = ?");
//      preparedStatement.setLong(1, id);
//
//      //Execute the statement
//      ResultSet resultSet = preparedStatement.executeQuery();
//
//      //Get the username.
//      if (resultSet.next()) {
//        username = resultSet.getString("username");
//      }
//    }
//    catch (SQLException ex) {
//      log.error("The username could not be retrieved from the database by id.", ex);
//    }
//
//    //Close the connection.
//    closeConnection(connection);
//
//    return username;
//  }
//
//  /**
//   * Get an id by username.
//   *
//   * @param username The username of the id to retrieve.
//   * @return The id retrieved by username, or null if it could not be found.
//   */
//  Long getUserId(String username) {
//    Long id = null;
//
//    //Open a connection with the database.
//    Connection connection = openConnection();
//
//    try {
//      //Get the id with the username.
//      PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.id FROM " + dbConfig.getTableName() + " u WHERE u.username = ?");
//      preparedStatement.setString(1, username);
//
//      //Execute the statement
//      ResultSet resultSet = preparedStatement.executeQuery();
//
//      //Get the id.
//      if (resultSet.next()) {
//        id = resultSet.getLong("id");
//      }
//    }
//    catch (SQLException ex) {
//      log.error("The id could not be retrieved from the database by username.", ex);
//    }
//
//    //Close the connection.
//    closeConnection(connection);
//
//    return id;
//  }
//
//  Long getUserId(DataUser dataUser) {
//    Long id = null;
//
//    //Open a connection with the database.
//    Connection connection = openConnection();
//
//    try {
//      //Get the id with the username and password.
//      PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.id FROM " + dbConfig.getTableName() + " u WHERE u.username = ? AND u.enc_password = ?");
//      preparedStatement.setString(1, dataUser.username());
//      preparedStatement.setString(2, dataUser.enc_password());
//
//      //Execute the statement
//      ResultSet resultSet = preparedStatement.executeQuery();
//
//      //Get the id.
//      if (resultSet.next()) {
//        id = resultSet.getLong("id");
//      }
//    }
//    catch (SQLException ex) {
//      log.error("The id could not be retrieved from the database by user credentials.", ex);
//    }
//
//    //Close the connection.
//    closeConnection(connection);
//
//    return id;
//  }
//
//  /**
//   * Updates a user in the database.
//   *
//   * @param dataUser The updated dataUser other than the last modified field.
//   * @return True if it was successful, false otherwise.
//   */
//  Boolean update(DataUser dataUser) {
//    int affectedRows = 0;
//
//    Connection connection = openConnection();
//
//    try {
//      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + dbConfig.getTableName() + " SET username = ?, enc_password = ?, last_modified = ? WHERE id = ?");
//      preparedStatement.setString(1, dataUser.username());
//      preparedStatement.setString(2, dataUser.enc_password());
//      preparedStatement.setTimestamp(3, Timestamp.from(dataUser.last_modified()));
//      preparedStatement.setLong(4, dataUser.id());
//
//
//      affectedRows = preparedStatement.executeUpdate();
//    }
//    catch (SQLException ex) {
//      log.error("The user could not be updated.", ex);
//    }
//
//    closeConnection(connection);
//
//    return (affectedRows > 0);
//  }
//
//  /**
//   * Deletes a user by id.
//   *
//   * @param id The id of the user to delete.
//   * @return True if it was successful, false otherwise.
//   */
//  Boolean delete(Long id) {
//    int affectedRows = 0;
//
//    Connection connection = openConnection();
//
//    try {
//      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + dbConfig.getTableName() + " WHERE id = ?");
//      preparedStatement.setLong(1, id);
//
//      affectedRows = preparedStatement.executeUpdate();
//    }
//    catch (SQLException ex) {
//      log.error("The user could not be deleted from the database by id.", ex);
//    }
//
//    closeConnection(connection);
//
//    return (affectedRows > 0);
//  }
//
//  /**
//   * Deletes a user by username.
//   *
//   * @param username The username of the user to delete.
//   * @return True if it was successful, false otherwise.
//   */
//  Boolean delete(String username) {
//    int affectedRows = 0;
//
//    Connection connection = openConnection();
//
//    try {
//      PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + dbConfig.getTableName() + " WHERE username = ?");
//      preparedStatement.setString(1, username);
//
//      affectedRows = preparedStatement.executeUpdate();
//    }
//    catch (SQLException ex) {
//      log.error("The user could not be deleted from the database by username.", ex);
//    }
//
//    closeConnection(connection);
//
//    return (affectedRows > 0);
//  }
//
//  /**
//   * Gets a dataUser by username helper method.
//   *
//   * @param username   the username of the user to retrieve.
//   * @param connection The connection to the database.
//   * @return The id retrieved from the database or null if it couldn't.
//   * @throws SQLException Throws if there was an sql exception on retrieving the data.
//   */
//  private Long getIdByUsername(String username, Connection connection) throws SQLException {
//    //Get the user with the id.
//    PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.id FROM " + dbConfig.getTableName() + " u WHERE u.username = ?");
//    preparedStatement.setString(1, username);
//
//    //Execute the statement
//    ResultSet resultSet = preparedStatement.executeQuery();
//
//    return getUserIdFromResultSet(resultSet);
//  }
//
//  /**
//   * Get a dataUser from a ResultSet.
//   *
//   * @param resultSet the resultSet to get the dataUser from.
//   * @return The id from the resultSet, null if it doesn't exist.
//   * @throws SQLException Throws if there was a sql exception involved.
//   */
//  private Long getUserIdFromResultSet(ResultSet resultSet) throws SQLException {
//    Long id = null;
//
//    //Get the dataUser.
//    if (resultSet.next()) {
//      id = resultSet.getLong("id");
//    }
//
//    return id;
//  }
//
//  /**
//   * The constructor that receives the autowired components.
//   *
//   * @param dbConfig          The dbms config.
//   * @param mysqlDataSource   The datasource object for dbms.
//   * @param timooviesDbConfig The timoobies db config.
//   * @param usersDbConfig     The users table config.
//   */
//  @Autowired
//  public UsersDbService(DbConfig dbConfig, TimooviesDbConfig timooviesDbConfig, UsersDbConfig usersDbConfig, MysqlDataSource mysqlDataSource) {
//    super(dbConfig, timooviesDbConfig, mysqlDataSource);
//    //this.dbConfig = usersDbConfig;
//  }
}
