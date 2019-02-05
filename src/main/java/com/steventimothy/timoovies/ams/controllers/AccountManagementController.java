package com.steventimothy.timoovies.ams.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The AccountManagementController Class</h1>
 * <p>This class holds the endpoints used in the AMS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ams")
@RestController
public class AccountManagementController {


//  /**
//   * The service for this controller that holds the logic.
//   */
//  private AccountManagementService accountManagementService;
//  private IdMapper idMapper;
//  private PmsClient pmsClient;
//
//  /**
//   * Creates a user in the system.
//   *
//   * @param user The user to create.
//   * @return The id of the user created or a bad request if it
//   * could not be created.
//   */
//  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity createUser(@RequestBody User user,
//                                   @RequestHeader("Authorization") String authorizationHeader) {
//
//    try {
//      Id sessionId = idMapper.mapEncodedValueToId(authorizationHeader);
//
//      if (sessionId instanceof SessionId && pmsClient.hasPermission((SessionId) sessionId, Permission.COMMON)) {
//        UserId userId = accountManagementService.createUser(user);
//
//        if (userId.getEncodedValue() != null) {
//          log.info("[200] POST: /ams - sessionId={} - Response: userId={}", sessionId, userId);
//          return ResponseEntity.ok(userId.getEncodedValue());
//        }
//        else {
//          log.warn("[400] POST: /ams - sessionId={} - The user could not be created in the database.", sessionId);
//        }
//      }
//      else {
//        log.warn("[400] POST: /ams - sessionId={} - The user did not have the correct permission. {}", sessionId);
//      }
//    }
//    catch (IllegalArgumentException ex) {
//      log.warn("[400] POST: /ams - authorizationHeader={} - Invalid sessionId: {}", authorizationHeader);
//    }
//
//    return ResponseEntity.badRequest().build();
//  }
//
//  /**
//   * Gets a username by id from the database.
//   *
//   * @param id The id of the user to retrieve.
//   * @return returns the username associated with that id if it exists, 400 if not.
//   */
//  @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity getUsername(@PathVariable("id") String id,
//                                    @RequestHeader("Authorization") String authorization) {
//
//    if (authorization != null) {
//      UserId userId = mapPathVariableToUserId(id);
//
//      String username = accountManagementService.getUsername(userId);
//
//      if (username != null) {
//        log.info("[200] GET: /ams/username/{} - Response: username={}", id, username);
//        return ResponseEntity.ok(username);
//      }
//      else {
//        log.warn("[400] GET: /ams/username/{} - Could not retrieve the username by id: {}", id, userId);
//        return ResponseEntity.badRequest().build();
//      }
//    }
//    else {
//      log.warn("[400] GET: /ams/username/{} - Could not retrieve the username by id: {}", id, id);
//      return ResponseEntity.badRequest().build();
//    }
//  }
//
//  /**
//   * Gets the userId of the user who matches the username and password.
//   *
//   * @param username The username of the user.
//   * @param password The password of the user.
//   * @return The userId of the user if valid credentials, bad request if not.
//   */
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity getUserIdByCredentials(@RequestParam("username") String username,
//                                               @RequestParam("password") String password) {
//    User user = new User()
//        .userId(null)
//        .username(username)
//        .password(password);
//    UserId userId = accountManagementService.getUserIdByCredentials(user);
//
//    if (userId.getEncodedValue() != null) {
//      log.info("[200] GET: /ams/admin/id - params: username={}, password={} - response: userId={}", username, password, userId);
//      return ResponseEntity.ok(userId);
//    }
//    else {
//      log.info("[400] GET: /ams/admin/id - params: username={}, password={} - No matching credentials", username, password);
//      return ResponseEntity.badRequest().build();
//    }
//  }
//
//  /**
//   * Updates a user in the system.
//   *
//   * @param user The updated user.
//   * @return Ok if successful, bad request otherwise.
//   */
//  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity updateUser(@RequestBody User user,
//                                   @RequestHeader("Authorization") String authorization) {
//    if (accountManagementService.updateUser(user)) {
//      log.info("[200] PUT: /ams - body={} - response: Successful", user);
//      return ResponseEntity.ok().build();
//    }
//    else {
//      log.warn("[400] PUT: /ams - Could not update the user: {}", user);
//      return ResponseEntity.badRequest().build();
//    }
//  }
//
//  /**
//   * Deletes a user given a specific id.
//   *
//   * @param id the id of the user to delete.
//   * @return Ok if it was successful, bad request otherwise.
//   */
//  @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity deleteUserById(@PathVariable("id") String id,
//                                       @RequestHeader("Authorization") String authorization) {
//    UserId userId = mapPathVariableToUserId(id);
//
//    if (accountManagementService.deleteUserById(userId)) {
//      log.info("[200] DELETE: /ams/id/{} - response: successful", id);
//      return ResponseEntity.ok().build();
//    }
//    else {
//      log.warn("[400] DELETE: /ams/id/{} - Could not delete user by userId: {}", id, userId);
//      return ResponseEntity.badRequest().build();
//    }
//  }
//
//  /**
//   * Maps the id from the path to a UserId.
//   *
//   * @param id the id from the path.
//   * @return The userId containing the id from the path, or a userId with null.
//   */
//  private UserId mapPathVariableToUserId(String id) {
//    try {
//      return null;//new UserId(id);
//    }
//    catch (IllegalArgumentException ex) {
//      log.error("Could not convert path variable to id: {}", id);
//      return null;//new UserId();
//    }
//  }
}
