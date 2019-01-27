package com.steventimothy.timoovies.ams.services;

import com.steventimothy.timoovies.repository.timoovies.users.UsersDataService;
import com.steventimothy.timoovies.schemas.users.User;
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
  public Long createUser(User user) {
    if (userValidator.validateCreateUser(user)) {
      log.info("Attempting to create the user: {}", user);
      return usersDataService.createUser(user);
    }
    else {
      log.warn("The user did not contain the correct information to create. user: {}", user);
      return null;
    }
  }

  /**
   * Gets a user by id.
   *
   * @param id The id of the user to get.
   * @return The User that has that Id or null if it wasn't found.
   */
  public User getUser(Integer id) {
    if (id != null) {
      log.info("Attempting to retrieve the user by id: {}", id);
      return usersDataService.getUser(id);
    }
    else {
      log.warn("The id cannot be null.");
      return null;
    }
  }

  /**
   * Gets the user by username
   *
   * @param username The username of the user to get.
   * @return The user that has that username or null if it wasn't found.
   */
  public User getUser(String username) {
    if (username != null) {
      log.info("Attempting to retrieve the user by username: {}", username);
      return usersDataService.getUser(username);
    }
    else {
      log.warn("The username cannot be null.");
      return null;
    }
  }

  /**
   * Updates a user in the database.
   *
   * @param user The updated user.
   * @return True if it was successful, false otherwise.
   */
  public Boolean updateUser(User user) {
    if (userValidator.validateUpdateUser(user)) {
      log.info("Attempting to update a user with: {}", user);

      //Get the old user.
      User oldUser = getUser(user.userId().getEncodedValue());

      if (oldUser != null) {
        return usersDataService.updateUser(user);
      }
      else {
        log.warn("The id did not match another user. user: {}", user);
        return false;
      }
    }
    else {
      log.warn("The user did not contain the correct information to update. user: {}", user);
      return false;
    }
  }

  /**
   * Delete a user by id.
   *
   * @param id The id of the user to delete.
   * @return True if successful, false otherwise.
   */
  public Boolean deleteUser(Integer id) {
    if (id != null) {
      log.info("Attempting to delete the user by id: {}", id);
      return usersDataService.deleteUser(id);
    }
    else {
      log.warn("The id cannot be null.");
      return false;
    }
  }

  /**
   * Delete a user by username.
   *
   * @param username The username of the user to delete.
   * @return True if it was successful, false otherwise.
   */
  public Boolean deleteUser(String username) {
    if (username != null) {
      log.info("Attempting to delete the user by username: {}", username);
      return usersDataService.deleteUser(username);
    }
    else {
      log.warn("The username cannot be null.");
      return false;
    }
  }
}
