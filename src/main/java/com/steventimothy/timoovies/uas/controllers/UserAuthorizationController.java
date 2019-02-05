package com.steventimothy.timoovies.uas.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The UserAuthorizationController Class</h1>
 * <p>This class holds the endpoints used in the UAS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/uas")
@RestController
public class UserAuthorizationController {

//  /**
//   * The service that holds the logic for the uas endpoints.
//   */
//  private UserAuthorizationService userAuthorizationService;
//
//  /**
//   * Gets a general sessionId for a client to use.
//   * @return A general sessionId.
//   */
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity getGeneralSessionId() {
//    SessionId sessionId = new GeneralSessionId(UUID.randomUUID());
//
//    log.info("[200] GET: /uas - Response: body={}", sessionId.getEncodedValue());
//    return ResponseEntity.ok(sessionId.getEncodedValue());
//  }
//
//  /**
//   * Logs in a user.
//   * @param user the user information to log verify before logging in.
//   * @return The sessionId if the user was able to log in, bad request if not.
//   */
//  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity login(@RequestBody User user) {
//    SessionId sessionId = userAuthorizationService.login(user);
//
//    if (sessionId.getEncodedValue() != null) {
//      log.info("[200] POST: /uas - Body: user={} - Response: id={}", user, sessionId);
//      return ResponseEntity.ok(sessionId);
//    }
//    else {
//      log.warn("[400] POST: /uas - Could not login the user: {}", user);
//      return ResponseEntity.badRequest().build();
//    }
//  }
}
