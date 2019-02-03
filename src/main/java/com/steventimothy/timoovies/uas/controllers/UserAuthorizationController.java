package com.steventimothy.timoovies.uas.controllers;

import com.steventimothy.timoovies.schemas.ids.SessionId;
import com.steventimothy.timoovies.schemas.users.User;
import com.steventimothy.timoovies.uas.services.UserAuthorizationService;
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
 * <h1>The UserAuthorizationController Class</h1>
 * <p>This class holds the endpoints used in the UAS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/uas")
@RestController
public class UserAuthorizationController {

  /**
   * The service that holds the logic for the uas endpoints.
   */
  private UserAuthorizationService userAuthorizationService;

  /**
   * Logs in a user.
   * @param user the user information to log verify before logging in.
   * @return The sessionId if the user was able to log in, bad request if not.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity login(@RequestBody User user) {
//    log.info("POST: /uas - Body: user={}", user);
//    SessionId sessionId = userAuthorizationService.login(user);
//
//    if (sessionId.getEncodedValue() != null) {
//      log.info("POST: /uas - Response: id={}", sessionId);
//      return ResponseEntity.ok(sessionId);
//    }
//    else {
//      log.warn("POST: /uas - Could not login the user: {}", user);
//      return ResponseEntity.badRequest().build();
//    }
    return ResponseEntity.ok().build();
  }
}
