package com.steventimothy.timoovies.ams.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The AdminAccountManagementController Class</h1>
 * <p>This class holds the admin endpoints used in the AMS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ams/admin")
@RestController
public class AdminAccountManagementController {


//  /**
//   * The service class that holds the logic for the endpoints.
//   */
//  private AccountManagementService accountManagementService;
//
//  /**
//   * Gets a userId by username from the database.
//   *
//   * @param username The username of the userId to retrieve.
//   * @return returns the userId associated with the username if it exists, 400 if not.
//   */
//  @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity getUserId(@PathVariable(value = "username") String username) {
//    UserId userId = accountManagementService.getUserId(username);
//
//    if (userId.getEncodedValue() != null) {
//      log.info("[200] GET: /ams/id/{} - Response: userId={}", username, userId);
//      return ResponseEntity.ok(userId);
//    }
//    else {
//      log.warn("[400] GET: /ams/id/{} - Could not retrieve the userId by username: {}", username, username);
//      return ResponseEntity.badRequest().build();
//    }
//  }
//
//  /**
//   * Deletes a user given a specific username.
//   *
//   * @param username the username of the user to delete.
//   * @return Ok if it was successful, bad request otherwise.
//   */
//  @DeleteMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity deleteUserByUsername(@PathVariable(value = "username") String username) {
//    if (accountManagementService.deleteUserByUsername(username)) {
//      log.info("[200] DELETE: /ams/username/{} - response: successful", username);
//      return ResponseEntity.ok().build();
//    }
//    else {
//      log.warn("[400] DELETE: /ams/username/{} - Could not delete user by username: {}", username, username);
//      return ResponseEntity.badRequest().build();
//    }
//  }
}
