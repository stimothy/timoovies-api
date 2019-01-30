package com.steventimothy.timoovies.repository.timoovies.sessions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The SessionsDataService Class</h1>
 * <p>This classes responsibility is the connection between the application and the
 * database class. It sets up the data to be queried in the database.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class SessionsDataService {

  private SessionsDbService sessionsDbService;
}
