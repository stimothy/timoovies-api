package com.steventimothy.timoovies;

import com.steventimothy.timoovies.schemas.ids.SessionId;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseComponent {

  /**
   * The restTemplate used for testing endpoints.
   */
  @Autowired
  protected TestRestTemplate restTemplate;

  /**
   * Creates a userId.
   *
   * @return The userId created.
   */
  protected UserId createUserId() {
    return createUserId(3L);
  }

  /**
   * Creates a userId.
   *
   * @param id The id to create.
   * @return The userId created.
   */
  protected UserId createUserId(Long id) {

    //Create a userId.
    UserId userId = new UserId()
        .rawId(id);

    assertThat(userId.rawId())
        .isEqualTo(id);

    return userId;
  }

  /**
   * Creates a universally unique sessionId.
   *
   * @return The sessionId created.
   */
  protected SessionId createSessionId() {
    return createSessionId(UUID.randomUUID());
  }

  /**
   * Creates a universally unique sessionId.
   *
   * @param uuid the uuid to create.
   * @return The sessionId with that uuid.
   */
  protected SessionId createSessionId(UUID uuid) {
    //Create a sessionId.
    SessionId sessionId = new SessionId()
        .rawId(uuid);

    assertThat(sessionId.rawId())
        .isEqualTo(uuid);

    return sessionId;
  }

  /**
   * Creates a userId.
   *
   * @return The userId created.
   */
  protected UserId createAltUserId() {
    return createUserId(4L);
  }

  /**
   * Creates a local user.
   *
   * @return The local created user.
   */
  protected User createLocalUser() {
    return createLocalUser(createUserId(), "testUser3", "ch33t@sRunFaSt");
  }

  /**
   * Creates a local user.
   *
   * @param userId   The userId of the user.
   * @param username The username of the user.
   * @param password The password of the user.
   * @return the local created user.
   */
  protected User createLocalUser(UserId userId, String username, String password) {

    //Create the user.
    User user = new User()
        .userId(userId)
        .username(username)
        .password(password);

    assertThat(user.userId())
        .isEqualTo(userId);
    assertThat(user.username())
        .isEqualTo(username);
    assertThat(user.password())
        .isEqualTo(password);

    return user;
  }

  /**
   * Create an alternate local user.
   *
   * @return The created alternate local user.
   */
  protected User createAltLocalUser() {
    return createLocalUser(createAltUserId(), "testUser4", "anTsW@lk!na1ine");
  }

  /**
   * Asserts that the response status on the response is equal to the httpStatus provided.
   *
   * @param responseEntity The response of the request.
   * @param status         The status expected.
   */
  protected void assertStatus(ResponseEntity responseEntity, HttpStatus status) {
    assertThat(responseEntity.getStatusCode())
        .isEqualTo(status);
  }

  /**
   * Returns the ams base path.
   *
   * @return The ams path.
   */
  protected String getAmsPath() {
    return "/ams";
  }
}
