package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.ams.services.AccountManagementService;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>The AccountManagementController Class</h1>
 * <p>This class holds the endpoints used in the AMS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ams")
@RestController
public class AccountManagementController {

  /**
   * The service for this controller that holds the logic.
   */
  private AccountManagementService accountManagementService;

  /**
   * Creates a user in the system.
   *
   * @param user The user to create.
   * @return The id of the user created or a bad request if it
   * could not be created.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createUser(@RequestBody User user) {
    log.info("POST: /ams - Body: user={}", user);
    UserId userId = accountManagementService.createUser(user);

    if (userId.getEncodedValue() != null) {
      log.info("POST: /ams - Response: id={}", userId);
      return ResponseEntity.ok(userId);
    }
    else {
      log.warn("POST: /ams - Could not create the user: {}", user);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Gets a user by id from the database.
   *
   * @param id The id of the user to retrieve.
   * @return returns the user associated with that id if it exists, 400 if not.
   */
  @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUserById(@PathVariable(value = "id") String id) {
    log.info("GET: /ams/user/{} - id=", id, id);
    UserId userId = mapPathVariableToUserId(id);

    User user = accountManagementService.getUserById(userId);

    if (user != null) {
      log.info("GET: /ams/user/{} - Response: user={}", id, user);
      return ResponseEntity.ok(user);
    }
    else {
      log.warn("GET: /ams/user/{} - Could not retrieve the user by userId: {}", id, userId);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Gets a username by id from the database.
   *
   * @param id The id of the user to retrieve.
   * @return returns the username associated with that id if it exists, 400 if not.
   */
  @GetMapping(value = "/username/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUsername(@PathVariable(value = "id") String id) {
    log.info("GET: /ams/username/{} - id=", id, id);
    UserId userId = mapPathVariableToUserId(id);

    String username = accountManagementService.getUsername(userId);

    if (username != null) {
      log.info("GET: /ams/username/{} - Response: username={}", id, username);
      return ResponseEntity.ok(username);
    }
    else {
      log.warn("GET: /ams/username/{} - Could not retrieve the username by id: {}", id, userId);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Gets a userId by username from the database.
   *
   * @param username The username of the userId to retrieve.
   * @return returns the userId associated with the username if it exists, 400 if not.
   */
  @GetMapping(value = "/id/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUserId(@PathVariable(value = "username") String username) {
    log.info("GET: /ams/id/{} - username=", username, username);

    UserId userId = accountManagementService.getUserId(username);

    if (userId.getEncodedValue() != null) {
      log.info("GET: /ams/id/{} - Response: userId={}", username, userId);
      return ResponseEntity.ok(userId);
    }
    else {
      log.warn("GET: /ams/id/{} - Could not retrieve the userId by username: {}", username, username);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Updates a user in the system.
   *
   * @param user The updated user.
   * @return Ok if successful, bad request otherwise.
   */
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity updateUser(@RequestBody User user) {
    log.info("PUT: /ams - Body: user={}", user);

    if (accountManagementService.updateUser(user)) {
      log.info("PUT: /ams - Successful");
      return ResponseEntity.ok().build();
    }
    else {
      log.warn("PUT: /ams - Could not update the user: {}", user);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Deletes a user given a specific id.
   *
   * @param id the id of the user to delete.
   * @return Ok if it was successful, bad request otherwise.
   */
  @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity deleteUserById(@PathVariable(value = "id") String id) {
    log.info("DELETE: /ams/id/{} - id=", id, id);
    UserId userId = mapPathVariableToUserId(id);

    if (accountManagementService.deleteUserById(userId)) {
      log.info("DELETE: /ams/id/{} - successful", id);
      return ResponseEntity.ok().build();
    }
    else {
      log.warn("DELETE: /ams/id/{} - Could not delete user by userId: {}", id, userId);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Deletes a user given a specific username.
   *
   * @param username the username of the user to delete.
   * @return Ok if it was successful, bad request otherwise.
   */
  @DeleteMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity deleteUserByUsername(@PathVariable(value = "username") String username) {
    log.info("DELETE: /ams/username/{} - username=", username, username);

    if (accountManagementService.deleteUserByUsername(username)) {
      log.info("DELETE: /ams/username/{} - successful", username);
      return ResponseEntity.ok().build();
    }
    else {
      log.warn("DELETE: /ams/username/{} - Could not delete user by username: {}", username, username);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Maps the id from the path to a UserId.
   *
   * @param id the id from the path.
   * @return The userId containing the id from the path, or a userId with null.
   */
  private UserId mapPathVariableToUserId(String id) {
    try {
      return new UserId(id);
    }
    catch (IllegalArgumentException ex) {
      log.error("Could not convert path variable to id: {}", id);
      return new UserId();
    }
  }
}
