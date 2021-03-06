package com.steventimothy.timoovies.ams.services;

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

//  /**
//   * The component that validates users.
//   */
//  private UserValidator userValidator;
//  /**
//   * The component used to talk with the database object class.
//   */
//  private UsersDataService usersDataService;
//
//  /**
//   * Attempts to create a user in the database. The id can only be provided
//   * if the id is 10 or below. Otherwise it needs to be null. The ids 1
//   * through 10 are used for testing purposes. All other fields need to be
//   * included.
//   *
//   * @param user The user to create.
//   * @return The id of the user that was created.
//   */
//  public UserId createUser(User user) {
//    if (userValidator.validateCreateUser(user)) {
//      return usersDataService.createUser(user);
//    }
//    else {
//      log.warn("The user did not contain the correct information to be created.");
//      return new UserId(null);
//    }
//  }
//
//  /**
//   * Gets a user by id.
//   *
//   * @param userId The id of the user to get.
//   * @return The User that has that Id or null if it wasn't found.
//   */
//  public User getUserById(UserId userId) {
//    if (userId.getEncodedValue() != null) {
//      return usersDataService.getUser(userId);
//    }
//    else {
//      log.warn("The userId cannot be empty.");
//      return null;
//    }
//  }
//
//  /**
//   * Gets a username by id.
//   *
//   * @param userId The id of the username to get.
//   * @return The username that is associated with that Id or null if it wasn't found.
//   */
//  public String getUsername(UserId userId) {
//    if (userId.getEncodedValue() != null) {
//      return usersDataService.getUsername(userId);
//    }
//    else {
//      log.warn("The userId cannot be empty.");
//      return null;
//    }
//  }
//
//  /**
//   * Gets a userId by username.
//   *
//   * @param username The username of the userId to get.
//   * @return The userId that is associated with the username or an empty userId if it wasn't found.
//   */
//  public UserId getUserId(String username) {
//    if (username != null) {
//      return usersDataService.getUserId(username);
//    }
//    else {
//      log.warn("The username cannot be null.");
//      return new UserId(null);
//    }
//  }
//
//  public UserId getUserIdByCredentials(User user) {
//    if (userValidator.validateCreateUser(user)) {
//      return usersDataService.getUserId(user);
//    }
//    else {
//      log.warn("The user did not contain the correct information to get id. user={}", user);
//      return new UserId(null);
//    }
//  }
//
//  /**
//   * Updates a user in the database.
//   *
//   * @param user The updated user.
//   * @return True if it was successful, false otherwise.
//   */
//  public Boolean updateUser(User user) {
//    if (userValidator.validateUpdateUser(user)) {
//      log.info("Attempting to update a user with: {}", user);
//      return usersDataService.updateUser(user);
//    }
//    else {
//      log.warn("The user did not contain the correct information to update. user: {}", user);
//      return false;
//    }
//  }
//
//  /**
//   * Delete a user by id.
//   *
//   * @param userId The id of the user to delete.
//   * @return True if successful, false otherwise.
//   */
//  public Boolean deleteUserById(UserId userId) {
//    if (userId.getEncodedValue() != null) {
//      return usersDataService.deleteUser(userId);
//    }
//    else {
//      log.warn("The userId cannot be empty.");
//      return false;
//    }
//  }
//
//  /**
//   * Delete a user by username.
//   *
//   * @param username The username of the user to delete.
//   * @return True if successful, false otherwise.
//   */
//  public Boolean deleteUserByUsername(String username) {
//    if (username != null) {
//      return usersDataService.deleteUser(username);
//    }
//    else {
//      log.warn("The username cannot be null.");
//      return false;
//    }
//  }
}
