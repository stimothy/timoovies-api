package com.steventimothy.timoovies.repository.timoovies.sessions;

import com.steventimothy.timoovies.repository.timoovies.TimooviesDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The SessionsDbService Class</h1>
 * <p>This class is responsible for talking with the sessions table in the database.</p>
 */
@Slf4j
@Component
class SessionsDbService extends TimooviesDbService {

//  private SessionsDbConfig dbConfig;
//
//  String insert(DataSession dataSession) {
//    //Do some initial setup and update the timestamps.
//    String id = null;
//
//    //Open a connection with the database.
//    Connection connection = openConnection();
//
//    try {
//      //Create a session.
//      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + dbConfig.getTableName() + " VALUES(?, ?, ?, ?, ?)");
//      preparedStatement.setString(1, dataSession.id());
//      preparedStatement.setLong(2, dataSession.user_id());
//      preparedStatement.setTimestamp(3, Timestamp.from(dataSession.expiration()));
//      preparedStatement.setTimestamp(4, Timestamp.from(dataSession.date_created()));
//      preparedStatement.setTimestamp(5, Timestamp.from(dataSession.date_created()));
//
//      //Execute the statement
//      preparedStatement.executeUpdate();
//
//      id = get(dataSession.user_id(), connection);
//    }
//    catch (SQLException ex) {
//      log.error("Could not insert the session into the database.", ex);
//    }
//
//    //Close the connection.
//    closeConnection(connection);
//
//    return id;
//  }
//
//  String get(Long userId) {
//    //Open a connection with the database.
//    Connection connection = openConnection();
//
//    String id = get(userId, connection);
//
//    //Close the connection.
//    closeConnection(connection);
//
//    return id;
//  }
//
//  Boolean update(DataSession dataSession) {
//    int affectedRows = 0;
//
//    Connection connection = openConnection();
//
//    try {
//      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + dbConfig.getTableName() + " SET expiration = ?, last_modified = ? WHERE user_id = ?");
//      preparedStatement.setTimestamp(1, Timestamp.from(dataSession.expiration()));
//      preparedStatement.setTimestamp(2, Timestamp.from(dataSession.last_modified()));
//      preparedStatement.setLong(4, dataSession.user_id());
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
//  private String get(Long userId, Connection connection) {
//    String id = null;
//
//    try {
//      //Get the sessionId with the user id.
//      PreparedStatement preparedStatement = connection.prepareStatement("SELECT s.id FROM " + dbConfig.getTableName() + " s WHERE s.user_id = ?");
//      preparedStatement.setLong(1, userId);
//
//      //Execute the statement
//      ResultSet resultSet = preparedStatement.executeQuery();
//
//      //Get the username.
//      if (resultSet.next()) {
//        id = resultSet.getString("id");
//      }
//    }
//    catch (SQLException ex) {
//      log.error("The id could not be retrieved from the database by user id.", ex);
//    }
//
//    return id;
//  }
//
//  @Autowired
//  public SessionsDbService(DbConfig dbConfig, TimooviesDbConfig timooviesDbConfig, SessionsDbConfig sessionsDbConfig, MysqlDataSource mysqlDataSource) {
//    super(dbConfig, timooviesDbConfig, mysqlDataSource);
//    //this.dbConfig = sessionsDbConfig;
//  }
}
