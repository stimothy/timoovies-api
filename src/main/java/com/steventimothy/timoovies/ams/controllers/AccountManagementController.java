package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.ams.services.AccountManagementService;
import com.steventimothy.timoovies.schema.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <h1>The AmsHealthCheckController Class</h1>
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
    Integer id = accountManagementService.createUser(user);

    if (id != null) {
      log.info("POST: /ams - Response: id={}", id);
      return ResponseEntity.ok(id);
    }
    else {
      log.warn("POST: /ams - Could not create the user: {}", user);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Gets a user by id from the database.
   * @param id The id of the user to retrieve.
   * @return returns the user associated with that id if it exists, 400 if not.
   */
  @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUserById(@PathVariable(value = "id") Integer id) {
    log.info("GET: /ams/id/{id} - id=", id);
    User user = accountManagementService.getUser(id);

    if (user != null) {
      log.info("GET: /ams/id/{id} - Response: user={}", user);
      return ResponseEntity.ok(user);
    }
    else {
      log.warn("GET: /ams/id/{id} - Could not retrieve the user by id: {}", id);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Gets a user by username from the database.
   * @param username The username of the user to retrieve.
   * @return returns the user associated with that username if it exists, 400 if not.
   */
  @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUserById(@PathVariable(value = "username") String username) {
    log.info("GET: /ams/username/{username} - username=", username);
    User user = accountManagementService.getUser(username);

    if (user != null) {
      log.info("GET: /ams/username/{username} - Response: user={}", user);
      return ResponseEntity.ok(user);
    }
    else {
      log.warn("GET: /ams/username/{username} - Could not retrieve the user by username: {}", username);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Deletes a user given a specific id.
   * @param id the id of the user to delete.
   * @return Ok if it was successful, false otherwise.
   */
  @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity deleteUserById(@PathVariable(value = "id") Integer id) {
    log.info("DELETE: /ams/id/{id} - id=", id);

    if (accountManagementService.deleteUser(id)) {
      log.info("DELETE: /ams/id/{id} - successful");
      return ResponseEntity.ok().build();
    }
    else {
      log.warn("DELETE: /ams/id/{id} - Could not delete user by id: {}", id);
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * Deletes a user given a specific username.
   * @param username the username of the user to delete.
   * @return Ok if it was successful, false otherwise.
   */
  @DeleteMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity deleteUserById(@PathVariable(value = "username") String username) {
    log.info("DELETE: /ams/username/{username} - username=", username);

    if (accountManagementService.deleteUser(username)) {
      log.info("DELETE: /ams/username/{username} - successful");
      return ResponseEntity.ok().build();
    }
    else {
      log.warn("DELETE: /ams/username/{username} - Could not delete user by username: {}", username);
      return ResponseEntity.badRequest().build();
    }
  }
}
