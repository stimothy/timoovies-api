package com.steventimothy.timoovies.repository.timoovies.users;

import com.steventimothy.timoovies.repository.schema.DataUser;
import com.steventimothy.timoovies.schema.User;
import com.steventimothy.timoovies.utils.users.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The UsersDataService Class</h1>
 * <p>This classes responsibility is the connection between the application and the
 * database class. It sets up the data to be queried in the database.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UsersDataService {

  /**
   * The component used to query the database.
   */
  private UsersDbService usersDbService;
  /**
   * The component to map a user to dataUser of vice versa.
   */
  private UserMapper userMapper;

  /**
   * Creates a user in the database.
   *
   * @param user the user to create in the database.
   * @return The id of the user created in the database. Null if it couldn't be created.
   */
  public Integer createUser(User user) {
    DataUser dataUser = userMapper.map(user);
    Integer id = usersDbService.insert(dataUser);

    if (id == null) {
      log.warn("The user was not created in the database. user: {}", user);
      return null;
    }
    else {
      log.info("The user was created successfully. user: {}", user);
      return id;
    }
  }
}
