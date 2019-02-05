package com.steventimothy.timoovies.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The AmsClient Class</h1>
 * <p>This class holds the calls to other services..</p>
 */
@Slf4j
@Component
public class AmsClient extends BaseClient {

//  /**
//   * Gets the userId of the user.
//   *
//   * @param user The user to get the userId for.
//   * @return The userId of the user or an empty userId if the user was not found.
//   */
//  public UserId getUserIdByCredentials(User user) {
//    try {
//      return restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath())
//          .queryParam("username", user.username())
//          .queryParam("password", user.password())
//          .build().toUri())
//          .accept(MediaType.APPLICATION_JSON)
//          .build(), UserId.class).getBody();
//    }
//    catch (HttpClientErrorException ex) {
//      log.warn("The users credentials were not valid. user={}", user);
//      return new UserId(null);
//    }
//  }
//
//
//  /**
//   * The Constructor.
//   *
//   * @param restTemplate The rest caller.
//   */
//  @Autowired
//  public AmsClient(Environment environment, RestTemplate restTemplate) {
//    super(environment, restTemplate);
//  }
}
