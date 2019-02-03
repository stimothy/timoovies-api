package com.steventimothy.timoovies;

import com.steventimothy.timoovies.repository.schemas.DataUser;
import com.steventimothy.timoovies.schemas.ids.SessionId;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
   * The userId of a created test user.
   */
  private UserId userId;
  /**
   * The userId of an alternate test user.
   */
  private UserId altUserId;
  /**
   * The cache of userIds needing to be clean up.
   */
  protected List<UserId> cleanUpCache = new ArrayList<>();

  /**
   * Clean up the state before starting the test.
   */
  @After
  public void tearDown() {
    cleanUp();
  }

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
   * Creates a dataUser.
   *
   * @return The dataUser created.
   */
  protected DataUser createDataUser() {
    return createDataUser(createUserId().rawId(), "testUser3", "ch33t@sRunFaSt", Instant.now(), Instant.now());
  }

  /**
   * Creates a dataUser.
   *
   * @return The dataUser created.
   */
  protected DataUser createDataUser(Long id, String username, String enc_password, Instant date_created, Instant last_modified) {
    //Create the dataUser.
    DataUser dataUser = new DataUser()
        .id(id)
        .username(username)
        .enc_password(enc_password)
        .date_created(date_created)
        .last_modified(last_modified);

    assertThat(dataUser.username())
        .isEqualTo(username);
    assertThat(dataUser.enc_password())
        .isEqualTo(enc_password);
    assertThat(dataUser.date_created())
        .isEqualTo(date_created);
    assertThat(last_modified)
        .isNotNull();

    return dataUser;
  }

  /**
   * Creates a dataUser.
   *
   * @return The dataUser created.
   */
  protected DataUser createAltDataUser() {
    return createDataUser(createAltUserId().rawId(), "testUser4", "anTsW@lk!na1ine", Instant.now(), Instant.now());
  }

  /**
   * Gets the existing id of test user 1, or creates a user and returns that id.
   *
   * @return the id of the test user 1.
   */
  protected UserId getOrCreateUserId() {
    if (userId != null) {
      return userId;
    }
    else {
      userId = createUser(new User()
          .userId(new UserId().rawId(1L))
          .username("testUser1")
          .password("hiPPos3atGr@ss"));

      assertThat(userId.getEncodedValue())
          .isNotNull();

      return userId;
    }
  }

  /**
   * Gets the existing id of test user 2, or creates a user and returns that id.
   *
   * @return the id of the test user 2.
   */
  protected UserId getOrCreateAltUserId() {
    if (altUserId != null) {
      return altUserId;
    }
    else {
      altUserId = createUser(new User()
          .userId(new UserId().rawId(2L))
          .username("testUser2")
          .password("w0rmSEatD!rt"));

      assertThat(altUserId.getEncodedValue())
          .isNotNull();

      return altUserId;
    }
  }

  /**
   * Creates a user in the database and returns its id.
   *
   * @param user The user to create in the database.
   * @return The id of the user created.
   */
  protected UserId createUser(User user) {
    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody().getEncodedValue())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Creates a user in the database.
   *
   * @param user The user to create in the database.
   * @return The response from the rest call to create a user.
   */
  protected ResponseEntity<UserId> requestCreateUser(User user) {
    cleanUpCache.add(user.userId());
    return this.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getAmsPath())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(user), UserId.class);
  }

  /**
   * Deletes a user by id.
   *
   * @param userId The id of the user to delete.
   */
  protected void deleteUserById(UserId userId) {
    ResponseEntity responseEntity = requestDeleteUserById(userId);
    assertStatus(responseEntity, HttpStatus.OK);
  }

  /**
   * Deletes a user by id.
   *
   * @param userId The id of the user to delete.
   * @return The response of the rest call to delete a user by id.
   */
  protected ResponseEntity requestDeleteUserById(UserId userId) {
    return this.restTemplate.exchange(RequestEntity.delete(UriComponentsBuilder.fromUriString(getAmsPath() + "/id/" + userId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), String.class);
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

  /**
   * Returns the uas base path.
   *
   * @return The uas path.
   */
  protected String getUasPath() {
    return "/uas";
  }

  /**
   * Cleans up the users in the database and sets their ids to null.
   */
  private void cleanUp() {
    for (UserId delUserId : cleanUpCache) {
      try {
        requestDeleteUserById(delUserId);
      }
      catch (Exception ex) {
        //Noop.
      }
    }

    cleanUpCache.clear();
    userId = null;
    altUserId = null;
  }
}
