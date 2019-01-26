package com.steventimothy.timoovies.ams.services;

import com.steventimothy.timoovies.repository.timoovies.users.UsersDataService;
import com.steventimothy.timoovies.schema.User;
import com.steventimothy.timoovies.utils.users.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The AccountManagementService Class</h1>
 * <p>This class holds the logic for the AMS system.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AccountManagementService {

  /**
   * The component that validates users.
   */
  private UserValidator userValidator;
  /**
   * The component used to talk with the database object class.
   */
  private UsersDataService usersDataService;

  /**
   * Attempts to create a user in the database. The id can only be provided
   * if the id is 10 or below. Otherwise it needs to be null. The ids 1
   * through 10 are used for testing purposes. All other fields need to be
   * included.
   *
   * @param user The user to create.
   * @return The id of the user that was created.
   */
  public Integer createUser(User user) {
    if (userValidator.validateUser(user)) {
      log.info("Attempting to create the user: {}", user);
      return usersDataService.createUser(user);
    }
    else {
      log.info("The user did not contain the correct information to create. user: {}", user);
      return null;
    }
  }

  public User getUser(Integer id) {
    if (id != null) {
      log.info("Attempting to retrieve the user by id: {}", id);
      return usersDataService.getUser(id);
    }
    else {
      log.info("The id cannot be null.");
      return null;
    }
  }

  public User getUser(String username) {
    if (username != null) {
      log.info("Attempting to retrieve the user by username: {}", username);
      return usersDataService.getUser(username);
    }
    else {
      log.info("The username cannot be null.");
      return null;
    }
  }

  public Boolean deleteUser(Integer id) {
    if (id != null) {
      log.info("Attempting to delete the user by id: {}", id);
      return usersDataService.deleteUser(id);
    }
    else {
      log.info("The id cannot be null.");
      return false;
    }
  }

  public Boolean deleteUser(String username) {
    if (username != null) {
      log.info("Attempting to delete the user by username: {}", username);
      return usersDataService.deleteUser(username);
    }
    else {
      log.info("The username cannot be null.");
      return false;
    }
  }
}