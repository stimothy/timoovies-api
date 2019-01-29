package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.ams.AmsBaseComponent;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.After;
import org.junit.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ControllersBaseComponent extends AmsBaseComponent {

  /**
   * The userId of a created test user.
   */
  private UserId userId;
  /**
   * The userId of an alternate test user.
   */
  private UserId altUserId;

  /**
   * Clean up the state before starting the test.
   */
  @Before
  public void setup() {
    cleanUp();
  }

  /**
   * Clean up the state before finishing the test.
   */
  @After
  public void tearDown() {
    cleanUp();
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
    assertThat(responseEntity.getBody())
        .isNotNull();
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
    return this.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getAmsPath())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(user), UserId.class);
  }

  /**
   * Gets the user based on an id. The user will not contain a password.
   *
   * @param userId The id of the user to get.
   * @return The user retrieved from the database.
   */
  protected User getUserById(UserId userId) {
    ResponseEntity<User> responseEntity = requestGetUserById(userId);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody().userId())
        .isEqualTo(userId);
    assertThat(responseEntity.getBody().username())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Gets the user based on an id. It will not contain the password stored in the database.
   *
   * @param userId The id of the user to get.
   * @return The response of the rest call to get a user by id.
   */
  protected ResponseEntity<User> requestGetUserById(UserId userId) {
    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/user/" + userId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), User.class);
  }

  /**
   * Gets the username based on an id.
   *
   * @param userId The id of the username to get.
   * @return The username retrieved from the database.
   */
  protected String getUsername(UserId userId) {
    ResponseEntity<String> responseEntity = requestGetUsername(userId);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Gets the username based on an id.
   *
   * @param userId The id of the username to get.
   * @return The response of the rest call to get a username by id.
   */
  protected ResponseEntity<String> requestGetUsername(UserId userId) {
    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/username/" + userId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), String.class);
  }

  /**
   * Gets the userId based on a username.
   *
   * @param username The username of the userId to get.
   * @return The userId retrieved from the database.
   */
  protected UserId getUserId(String username) {
    ResponseEntity<UserId> responseEntity = requestGetUserId(username);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody().getEncodedValue())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Gets the userId based on a username.
   *
   * @param username The username of the userId to get.
   * @return The response of the rest call to get a userId by username.
   */
  protected ResponseEntity<UserId> requestGetUserId(String username) {
    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/id/" + username)
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), UserId.class);
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
   * Cleans up the users in the database and sets their ids to null.
   */
  private void cleanUp() {
    try {
      requestDeleteUserById(userId);
      requestDeleteUserById(altUserId);
    }
    catch (Exception ex) {
      //Noop.
    }

    userId = null;
    altUserId = null;
  }
}
