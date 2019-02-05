package com.steventimothy.timoovies.uas.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserAuthorizationService Class</h1>
 * <p>This class holds the logic for the UAS system.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserAuthorizationService {

//  /**
//   * The data layer for the sessions table.
//   */
//  private SessionsDataService sessionsDataService;
//  /**
//   * Validates that a user has correct data.
//   */
//  private UserValidator userValidator;
//  /**
//   * The client that talks to the ams service.
//   */
//  private AmsClient amsClient;
//
//  /**
//   * Logs in a user.
//   * @param user The user to log in.
//   * @return The sessionId of the user logged in, or an empty sessionId.
//   */
//  public SessionId login(User user) {
//    if (userValidator.validateCreateUser(user)) {
//      UserId userId = amsClient.getUserIdByCredentials(user);
//
//      if (userId.getEncodedValue() != null) {
//        return sessionsDataService.login(userId);
//      }
//      else {
//        log.warn("The user did not provide valid credentials. user={}", user);
//        return null;//new SessionId();
//      }
//    }
//    else {
//      log.warn("The user did not have valid data to log in. user={}", user);
//      return null;//new SessionId();
//    }
//  }
}
