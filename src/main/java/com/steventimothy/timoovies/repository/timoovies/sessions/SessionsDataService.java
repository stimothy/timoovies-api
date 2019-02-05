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

//  private SessionsDbService sessionsDbService;
//
//  public SessionId login(UserId userId) {
//    SessionId sessionId = null;//new SessionId();
//    String session = sessionsDbService.get(null);
//    if (session != null) {
//      //sessionId.rawId(UUID.fromString(session));
//    }
//
//    if (sessionId.getEncodedValue() == null) {
//      DataSession dataSession = new DataSession()
//          .id(UUID.randomUUID().toString())
//          .user_id(null)
//          .expiration(calculateExpiration());
//      dataSession.date_created(Instant.now());
//      dataSession.last_modified(dataSession.date_created());
//
//      //sessionId.rawId(UUID.fromString(sessionsDbService.insert(dataSession)));
//    }
//    else {
//      DataSession dataSession = new DataSession()
//          .user_id(null)
//          .expiration(calculateExpiration())
//          .last_modified(Instant.now());
//      sessionsDbService.update(dataSession);
//    }
//
//    return sessionId;
//  }
//
//  private Instant calculateExpiration() {
//    return Instant.now().plus(3, ChronoUnit.HOURS);
//  }
}
