package com.steventimothy.timoovies.utils.users;

import com.steventimothy.timoovies.schema.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserValidator Class</h1>
 * <p>This class validates that a user has valid data.</p>
 */
@Slf4j
@Component
public class UserValidator {

  /**
   * Validates that the user has the correct data.
   *
   * @param user The user to validate.
   * @return True if user is valid, false otherwise.
   */
  public boolean validateUser(User user) {
    if (user != null && validUserData(user)) {
      return true;
    }
    else {
      if (user == null) {
        log.warn("Couldn't validate a null user.");
      }
      return false;
    }
  }

  /**
   * Validates a user being updated.
   *
   * @param user The updated user.
   * @return True if all fields are valid.
   */
  public boolean validateUpdateUser(User user) {
    if (user != null && validUpdateUserData(user)) {
      return true;
    }
    else {
      if (user == null) {
        log.warn("Couldn't validate a null user.");
      }
      return false;
    }
  }

  /**
   * Validates that the users data is correct.
   *
   * @param user the user to validate.
   * @return True if the user has valid data, false otherwise.
   */
  private boolean validUserData(User user) {
    return (validId(user.id()) && validUsernamePassword(user.username(), user.password()));
  }

  /**
   * Validates that the users data if correct.
   *
   * @param user The updated user.
   * @return True if the user has valid data, false otherwise.
   */
  private boolean validUpdateUserData(User user) {
    return validUpdateId(user.id()) && validUsernamePassword(user.username(), user.password());
  }

  /**
   * Validates that the id is a valid id. Ids 1 through 10 are for testing
   * anything else should be null.
   *
   * @param id The id of the user.
   * @return true if valid, false otherwise.
   */
  private boolean validId(Integer id) {
    if (id == null || (id > 0 && id <= 10)) {
      return true;
    }
    else {
      log.warn("The user id cannot contain any value unless it is between 1 and 10");
      return false;
    }
  }

  /**
   * Validates that the user cannot have a null id.
   *
   * @param id The id of the updated user.
   * @return True if the id was valid, false otherwise.
   */
  private boolean validUpdateId(Integer id) {
    if (id != null) {
      return true;
    }
    else {
      log.warn("The user id cannot be null on an update.");
      return false;
    }
  }

  /**
   * Validates that the username and the password are not null.
   *
   * @param username the username of the user.
   * @param password the password of the user.
   * @return True if valid, false otherwise.
   */
  private boolean validUsernamePassword(String username, String password) {
    if (username != null && password != null) {
      return true;
    }
    else {
      log.warn("The user username and password cannot be null");
      return false;
    }
  }
}
