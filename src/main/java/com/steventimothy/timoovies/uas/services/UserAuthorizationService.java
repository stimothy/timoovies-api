package com.steventimothy.timoovies.uas.services;

import com.steventimothy.timoovies.repository.timoovies.sessions.SessionsDataService;
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

  private SessionsDataService sessionsDataService;
}
