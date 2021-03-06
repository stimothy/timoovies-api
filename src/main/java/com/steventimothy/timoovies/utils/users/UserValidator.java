package com.steventimothy.timoovies.utils.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserValidator Class</h1>
 * <p>This class validates that a user has valid data.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserValidator {

//  private IdMapper idMapper;
//
//  /**
//   * Validates that the user has the correct data.
//   *
//   * @param user The user to validate.
//   * @return True if user is valid, false otherwise.
//   */
//  public boolean validateCreateUser(User user) {
//    return user != null && validUserData(user);
//  }
//
//  /**
//   * Validates a user being updated.
//   *
//   * @param user The updated user.
//   * @return True if all fields are valid.
//   */
//  public boolean validateUpdateUser(User user) {
//    if (user != null && validUpdateUserData(user)) {
//      return true;
//    }
//    else {
//      log.warn("User is not valid for updates. user: {}", user);
//      return false;
//    }
//  }
//
//  /**
//   * Validates that the users data is correct.
//   *
//   * @param user the user to validate.
//   * @return True if the user has valid data, false otherwise.
//   */
//  private boolean validUserData(User user) {
//    return validCreateId(user.userId()) && validUsernamePassword(user.username(), user.password());
//  }
//
//  /**
//   * Validates that the users data if correct.
//   *
//   * @param user The updated user.
//   * @return True if the user has valid data, false otherwise.
//   */
//  private boolean validUpdateUserData(User user) {
//    return validUpdateId(user.userId()) && validUsernamePassword(user.username(), user.password());
//  }
//
//  /**
//   * Validates that the id is a valid id. Ids 1 through 10 are for testing
//   * anything else should be null.
//   *
//   * @param userId The id of the user.
//   * @return true if valid, false otherwise.
//   */
//  private boolean validCreateId(UserId userId) {
//    return userId != null && (userId.getEncodedValue() == null || validIdRange(userId.getEncodedValue()));
//  }
//
//  private boolean validIdRange(String encodedValue) {
//    long rawId = Long.parseLong(idMapper.mapEncodedValueToRawString(encodedValue));
//    return rawId > 0 && rawId < 11;
//  }
//
//  /**
//   * Validates that the user cannot have a null id.
//   *
//   * @param userId The id of the updated user.
//   * @return True if the id was valid, false otherwise.
//   */
//  private boolean validUpdateId(UserId userId) {
//    if (userId != null && userId.getEncodedValue() != null) {
//      return true;
//    }
//    else {
//      log.warn("The user id cannot be null on an update.");
//      return false;
//    }
//  }
//
//  /**
//   * Validates that the username and the password are not null.
//   *
//   * @param username the username of the user.
//   * @param password the password of the user.
//   * @return True if valid, false otherwise.
//   */
//  private boolean validUsernamePassword(String username, String password) {
//    if (validUsername(username) && validPassword(password)) {
//      return true;
//    }
//    else {
//      return false;
//    }
//  }
//
//  /**
//   * Validates that the username is not null and is the right length.
//   *
//   * @param username The username to validate.
//   * @return True if it was valid, false otherwise.
//   */
//  private boolean validUsername(String username) {
//    if (username != null && username.length() > 4 && username.length() < 51) {
//      return true;
//    }
//    else {
//      log.warn("Username cannot be null and must be between 5 and 50 characters.");
//      return false;
//    }
//  }
//
//  /**
//   * Validates that the password is not null and is the right length.
//   *
//   * @param password The password to validate.
//   * @return True if it was valid, false otherwise.
//   */
//  private boolean validPassword(String password) {
//    if (password != null && password.length() > 4 && password.length() < 65) {
//      return true;
//    }
//    else {
//      log.warn("Password cannot be null and must be between 5 and 64 characters.");
//      return false;
//    }
//  }
}
