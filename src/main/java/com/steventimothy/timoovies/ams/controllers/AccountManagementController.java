package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.ams.services.AccountManagementService;
import com.steventimothy.timoovies.schema.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
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
}
