package com.steventimothy.timoovies.repository.timoovies.users;

import com.steventimothy.timoovies.repository.schemas.DataUser;
import com.steventimothy.timoovies.schemas.users.User;
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
  public Long createUser(User user) {
    DataUser dataUser = userMapper.map(user);
    Long id = usersDbService.insert(dataUser);

    if (id == null) {
      log.warn("The user was not created in the database. user: {}", user);
      return null;
    }
    else {
      log.info("The user was created successfully. user: {}", user);
      return id;
    }
  }

  /**
   * Gets a user by id.
   *
   * @param id The id of the user to get.
   * @return The User that matches that id, or null if it wasn't found.
   */
  public User getUser(Integer id) {
    DataUser dataUser = usersDbService.getById(id);

    if (dataUser == null) {
      log.warn("The user did not exist in the database with id: {}", id);
      return null;
    }
    else {
      log.info("The user retrieved: {}", dataUser);
      return userMapper.map(dataUser);
    }
  }

  /**
   * Gets a user by username.
   *
   * @param username The username of the user to get.
   * @return The user that matches the username, or null if it wasn't found.
   */
  public User getUser(String username) {
    DataUser dataUser = usersDbService.getByUsername(username);

    if (dataUser == null) {
      log.warn("The user did not exist in the database with username: {}", username);
      return null;
    }
    else {
      log.info("The user retrieved: {}", dataUser);
      return userMapper.map(dataUser);
    }
  }

  /**
   * Updates a user in the database.
   *
   * @param user The updated user.
   * @return True if it was successful, false otherwise.
   */
  public Boolean updateUser(User user) {
    DataUser dataUser = userMapper.map(user);

    if (!usersDbService.update(dataUser)) {
      log.warn("A user was not updated in the database with. user: {}", user);
      return false;
    }
    else {
      log.info("The user was updated successfully. user: {}", user);
      return true;
    }
  }

  /**
   * Deletes a user by id
   *
   * @param id The id of the user to delete.
   * @return True if it was successful, false otherwise.
   */
  public Boolean deleteUser(Integer id) {
    if (!usersDbService.deleteById(id)) {
      log.warn("The user could not be deleted with id: {}", id);
      return false;
    }
    else {
      log.info("The user was deleted");
      return true;
    }
  }

  /**
   * Deletes a user by username.
   *
   * @param username The username of the user to be deleted.
   * @return True if it was successful, false otherwise.
   */
  public Boolean deleteUser(String username) {
    if (!usersDbService.deleteByUsername(username)) {
      log.warn("The user could not be deleted with username: {}", username);
      return false;
    }
    else {
      log.info("The user was deleted");
      return true;
    }
  }
}
