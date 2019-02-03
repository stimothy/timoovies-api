package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.schemas.ids.SessionId;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementControllerTest extends ControllersBaseComponent {

  /**
   * Tests that a user can be created.
   */
  @Test
  public void testCreateUser() {
    //Setup a user to create.
    User user = createLocalUser();

    //Create the user.
    UserId userId = createUser(user);

    //Get the created user.
    User user2 = getUserById(userId);
    assertThat(user)
        .isEqualToIgnoringGivenFields(user2, "password");
  }

  /**
   * Tests that a 400 is returned if the user cannot be created.
   */
  @Test
  public void testCreateUser_Failed() {
    //Setup a user to create.
    User user = createLocalUser();
    user.username(null);

    //Create a user.
    ResponseEntity createResponseEntity = requestCreateUser(user);
    assertStatus(createResponseEntity, HttpStatus.BAD_REQUEST);

    //Get the created user.
    ResponseEntity getResponseEntity = requestGetUserById(user.userId());
    assertStatus(getResponseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user can be retrieved from the system.
   */
  @Test
  public void testGetUser() {
    //Setup and create a user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the created user from the system.
    User user2 = getUserById(userId);
    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
  }

  /**
   * Tests that a 400 is returned if the user cannot be retrieved.
   */
  @Test
  public void testGetUser_Failed() {
    //Get a user the wrong type of id.
    SessionId sessionId = createSessionId();
    ResponseEntity<User> responseEntity = this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/user/" + sessionId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), User.class);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user can get a username given a userId.
   */
  @Test
  public void testGetUsername() {
    //Setup and create a user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the username of the user.
    String username = getUsername(userId);
    assertThat(username)
        .isEqualTo(user.username());
  }

  /**
   * Tests that a 400 is returned if the username cannot be retrieved.
   */
  @Test
  public void testGetUsername_Fail() {
    //Get the username with the wrong type of id.
    SessionId sessionId = createSessionId();
    ResponseEntity<String> responseEntity = this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/user/" + sessionId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), String.class);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user can get a userId given a username.
   */
  @Test
  public void testGetUserId() {
    //Setup and create the user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the userId.
    UserId userId2 = getUserId(user.username());
    assertThat(userId2)
        .isEqualTo(userId);
  }

  /**
   * Tests that a 400 is returned if the userId cannot be retrieved.
   */
  @Test
  public void testGetUserId_Fail() {
    //Get the userId.
    ResponseEntity<UserId> responseEntity = requestGetUserId(null);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user can be updated given a userId.
   */
  @Test
  public void testUpdateUser() {
    //Create a user.
    UserId userId = getOrCreateUserId();

    //Create an updated user.
    User user = createLocalUser();
    user.userId(userId);

    //Update the user.
    ResponseEntity responseEntity = requestUpdateUser(user);
    assertStatus(responseEntity, HttpStatus.OK);

    //Get the updated user.
    User user2 = getUserById(userId);
    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
    assertThat(user2.userId())
        .isEqualTo(userId);
  }

  /**
   * Tests that a 400 is returned if the user cannot be updated.
   */
  @Test
  public void testUpdateUser_Fail() {
    //Create an updated user.
    User user = createLocalUser();
    user.userId(null);

    //Update the user.
    ResponseEntity responseEntity = requestUpdateUser(user);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user can be deleted given a userId.
   */
  @Test
  public void testDeleteUserById() {
    //Create the user to delete.
    UserId userId = getOrCreateUserId();

    //Get the created user.
    User user = getUserById(userId);
    assertThat(user.userId())
        .isEqualTo(userId);
    assertThat(user.username())
        .isNotNull();

    //Delete the user.
    ResponseEntity deleteResponseEntity = requestDeleteUserById(userId);
    assertStatus(deleteResponseEntity, HttpStatus.OK);

    //Try and get the deleted user.
    ResponseEntity<User> getResponseEntity = requestGetUserById(userId);
    assertStatus(getResponseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a 400 is returned if the user cannot be deleted.
   */
  @Test
  public void testDeleteUserById_Fail() {
    //Delete the user with bad type of id.
    SessionId sessionId = createSessionId();
    ResponseEntity deleteResponseEntity = this.restTemplate.exchange(RequestEntity.delete(UriComponentsBuilder.fromUriString(getAmsPath() + "/id/" + sessionId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), String.class);
    assertStatus(deleteResponseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user can be deleted given a username.
   */
  @Test
  public void testDeleteUserByUsername() {
    //Create the user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the user.
    User user2 = getUserById(userId);
    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");

    //Delete the user
    ResponseEntity deleteResponseEntity = requestDeleteByUsername(user.username());
    assertStatus(deleteResponseEntity, HttpStatus.OK);

    //Try and get the deleted user.
    ResponseEntity getResponseEntity = requestGetUserById(userId);
    assertStatus(getResponseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a 400 is returned if the user cannot be deleted.
   */
  @Test
  public void testDeleteUserByUsername_Fail() {
    //Delete the user
    ResponseEntity deleteResponseEntity = requestDeleteByUsername(null);
    assertStatus(deleteResponseEntity, HttpStatus.BAD_REQUEST);
  }
}
