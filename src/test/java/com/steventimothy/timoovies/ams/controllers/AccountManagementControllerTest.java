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
   * This tests that a user can be created and also retrieved by id.
   */
  @Test
  public void testCreateUser() {
    //Create the user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the created user.
    User user2 = getUserById(userId);

    assertThat(user)
        .isEqualToIgnoringGivenFields(user2, "password");
  }

  /**
   * This tests that a user can be created even with duplicate passwords.
   */
  @Test
  public void testCreateUser_DuplicatePasswords() {
    //Create the users.
    User user = createLocalUser();
    createUser(user);

    User user2 = createAltLocalUser();
    user2.password(user.password());
    UserId userId2 = createUser(user2);

    //Get the created user.
    User user3 = getUserById(userId2);

    assertThat(user2)
        .isEqualToIgnoringGivenFields(user3, "password");
  }

  /**
   * Tests that a user cannot be created with an id of zero.
   */
  @Test
  public void testCreateUser_IdZero() {
    //Create the user.
    User user = createLocalUser();
    user.userId().rawId(0L);
    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created if their id is greater than 10.
   */
  @Test
  public void testCreateUser_NonTestId() {
    //Create the user.
    User user = createLocalUser();
    user.userId().rawId(11L);
    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created if their username is null.
   */
  @Test
  public void testCreateUser_NoUsername() {
    //Create the user.
    User user = createLocalUser();
    user.username(null);
    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created if their password is null.
   */
  @Test
  public void testCreateUser_NoPassword() {
    //Create the user.
    User user = createLocalUser();
    user.password(null);
    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created with identical ids.
   */
  @Test
  public void testCreateUser_IdenticalIds() {
    //Create the users.
    UserId userId = getOrCreateUserId();
    User user = createLocalUser();
    user.userId(userId);
    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created with identical usernames.
   */
  @Test
  public void testCreateUser_IdenticalUsernames() {
    //Create the users.
    User user = createLocalUser();
    createUser(user);

    User user2 = createAltLocalUser();
    user2.username(user.username());

    ResponseEntity<UserId> responseEntity = requestCreateUser(user2);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created if the username is too small.
   */
  @Test
  public void testCreateUser_UsernameTooSmall() {
    //Create the user.
    User user = createLocalUser(createUserId(), "1234", "myPassword");

    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created if the password is too small.
   */
  @Test
  public void testCreateUser_PasswordTooSmall() {
    //Create the user.
    User user = createLocalUser(createUserId(), "myUsername", "1234");

    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created if the username is too big.
   */
  @Test
  public void testCreateUser_UsernameTooBig() {
    //Create the user.
    User user = createLocalUser(createUserId(), "123456789012345678901234567890123456789012345678901", "myPassword");

    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created if the password is too big.
   */
  @Test
  public void testCreateUser_PasswordTooBig() {
    //Create the user.
    User user = createLocalUser(createUserId(), "myUsername", "12345678901234567890123456789012345678901234567890123456789012345");

    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that you can retrieve a user from the database.
   */
  @Test
  public void testGetUser() {
    //Create a user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the user.
    User user2 = getUserById(userId);

    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
  }

  /**
   * Tests that you cannot retrieve a user from the database if its not there.
   */
  @Test
  public void testGetUser_IdNotInDB() {
    UserId userId = createUserId();
    requestDeleteUserById(userId);

    ResponseEntity<User> responseEntity = requestGetUserById(userId);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }


  /**
   * Tests that you cannot retrieve a user from the database given no id.
   */
  @Test
  public void testGetUser_IdNull() {
    ResponseEntity<User> responseEntity = requestGetUserById(new UserId());
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that you cannot retrieve a user from the database given a bad id type.
   */
  @Test
  public void testGetUser_DifferentIdType() {
    SessionId sessionId = createSessionId();
    ResponseEntity<User> responseEntity = this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/user/" + sessionId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), User.class);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that you can retrieve a username from the database.
   */
  @Test
  public void testGetUsername() {
    //Create the user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the username.
    String username = getUsername(userId);

    assertThat(username)
        .isEqualTo(user.username());
  }

  /**
   * Tests that you cannot retrieve a username from the database given its not there.
   */
  @Test
  public void testGetUsername_IdNotInDB() {
    UserId userId = createUserId();
    requestDeleteUserById(userId);

    ResponseEntity<String> responseEntity = requestGetUsername(userId);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that you cannot retrieve a username from the database given no id.
   */
  @Test
  public void testGetUsername_IdNull() {
    ResponseEntity<String> responseEntity = requestGetUsername(new UserId());
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that you cannot retrieve a username from the database given a bad id type.
   */
  @Test
  public void testGetUsername_DifferentIdType() {
    SessionId sessionId = createSessionId();
    ResponseEntity<String> responseEntity = this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/username/" + sessionId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), String.class);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that you can retrieve a userId from the database.
   */
  @Test
  public void testGetUserId() {
    //Create the user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the userId.
    UserId userId2 = getUserId(user.username());

    assertThat(userId2)
        .isEqualTo(userId);
  }

  /**
   * Tests that you cannot retrieve a userId from the database if its not there.
   */
  @Test
  public void testGetUserId_UsernameNotInDB() {
    User user = createLocalUser();
    requestDeleteByUsername(user.username());

    ResponseEntity<UserId> responseEntity = requestGetUserId(user.username());
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that you cannot retrieve a userId from the database given no username.
   */
  @Test
  public void testGetUserId_UsernameNull() {
    ResponseEntity<UserId> responseEntity = requestGetUserId(null);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user can be updated in the database.
   */
  @Test
  public void testUpdateUser() {
    //Create a user.
    UserId userId = getOrCreateUserId();

    //Create an updated user.
    User user = createLocalUser();
    user.userId(userId);

    updateUser(user);

    User user2 = getUserById(userId);

    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
  }

  /**
   * Tests that a user cannot be updated in the database if it doesn't exist.
   */
  @Test
  public void testUpdateUser_UserDoesNotExist() {
    User user = createLocalUser();
    requestDeleteUserById(user.userId());

    //Create an updated user.
    User user2 = createAltLocalUser();
    user2.userId(user.userId());

    ResponseEntity responseEntity = requestUpdateUser(user2);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * This tests that a user can be updated even with duplicate passwords.
   */
  @Test
  public void testUpdateUser_DuplicatePasswords() {
    //Create the users.
    User user = createLocalUser();
    createUser(user);
    User user2 = createAltLocalUser();
    createUser(user2);

    //Update the user.
    user2.password(user.password());
    ResponseEntity responseEntity = requestUpdateUser(user2);

    assertStatus(responseEntity, HttpStatus.OK);
  }

  /**
   * Tests that a user cannot be updated if their username is null.
   */
  @Test
  public void testUpdateUser_NoUsername() {
    //Create the user.
    UserId userId = getOrCreateUserId();
    User user = createLocalUser();
    user.userId(userId);
    user.username(null);

    //Update the users.
    ResponseEntity responseEntity = requestUpdateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be updated if their password is null.
   */
  @Test
  public void testUpdateUser_NoPassword() {
    //Create the user.
    UserId userId = getOrCreateUserId();
    User user = createLocalUser();
    user.userId(userId);
    user.password(null);

    //Update the user.
    ResponseEntity responseEntity = requestUpdateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be updated with identical usernames.
   */
  @Test
  public void testUpdateUser_IdenticalUsernames() {
    //Create the users.
    User user = createLocalUser();
    createUser(user);
    UserId userId2 = getOrCreateAltUserId();

    //Create alternate user.
    User user2 = createAltLocalUser();
    user2.userId(userId2);
    user2.username(user.username());

    //Update the user.
    ResponseEntity responseEntity = requestUpdateUser(user2);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be updated if the username is too small.
   */
  @Test
  public void testUpdateUser_UsernameTooSmall() {
    //Create the users.
    UserId userId = getOrCreateUserId();
    User user = createLocalUser(userId, "1234", "myPassword");

    ResponseEntity responseEntity = requestUpdateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be updated if the password is too small.
   */
  @Test
  public void testUpdateUser_PasswordTooSmall() {
    //Create the user.
    UserId userId = getOrCreateUserId();
    User user = createLocalUser(userId, "myUsername", "1234");

    ResponseEntity responseEntity = requestUpdateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be updated if the username is too big.
   */
  @Test
  public void testUpdateUser_UsernameTooBig() {
    //Create the user.
    UserId userId = getOrCreateUserId();
    User user = createLocalUser(userId, "123456789012345678901234567890123456789012345678901", "myPassword");

    ResponseEntity responseEntity = requestUpdateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be updated if the password is too big.
   */
  @Test
  public void testUpdateUser_PasswordTooBig() {
    //Create the user.
    UserId userId = getOrCreateUserId();
    User user = createLocalUser(userId, "myUsername", "12345678901234567890123456789012345678901234567890123456789012345");

    ResponseEntity responseEntity = requestUpdateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user is deleted from the database by id.
   */
  @Test
  public void testDeleteUser_ById() {
    //Create and delete the user.
    UserId userId = getOrCreateUserId();
    deleteUserById(userId);

    //Try and get the deleted user.
    ResponseEntity<User> responseEntity = requestGetUserById(userId);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user is deleted from the database by username.
   */
  @Test
  public void testDeleteUser_ByUsername() {
    //Create and delete the user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Delete the user
    deleteByUsername(user.username());

    //Try and get the deleted user.
    ResponseEntity<User> responseEntity = requestGetUserById(userId);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be deleted if id is null.
   */
  @Test
  public void testDeleteUser_ById_NullId() {
    ResponseEntity responseEntity = requestDeleteUserById(new UserId());
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user is deleted from the database by username.
   */
  @Test
  public void testDeleteUser_ByUsername_NullUsername() {
    ResponseEntity responseEntity = requestDeleteByUsername(null);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be deleted if id is not there.
   */
  @Test
  public void testDeleteUser_ById_NotInDB() {
    UserId userId = createUserId();
    requestDeleteUserById(userId);

    ResponseEntity responseEntity = requestDeleteUserById(userId);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user is deleted from the database by username.
   */
  @Test
  public void testDeleteUser_ByUsername_NotInDB() {
    User user = createLocalUser();
    requestDeleteByUsername(user.username());

    ResponseEntity responseEntity = requestDeleteByUsername(user.username());
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }
}
