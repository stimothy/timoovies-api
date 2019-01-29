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

    //Clean up.
    deleteUserById(userId);
  }

  /**
   * This tests that a user can be created even with duplicate passwords.
   */
  @Test
  public void testCreateUser_DuplicatePasswords() {
    //Create the users.
    User user = createLocalUser();
    UserId userId = createUser(user);

    User user2 = createAltLocalUser();
    user2.password(user.password());
    UserId userId2 = createUser(user2);

    //Get the created user.
    User user3 = getUserById(userId2);

    assertThat(user2)
        .isEqualToIgnoringGivenFields(user3, "password");

    //Clean up.
    deleteUserById(userId);
    deleteUserById(userId2);
  }

  /**
   * Tests that a user can be created with an id of zero.
   */
  @Test
  public void testCreateUser_IdZero() {
    //Create the user.
    User user = createLocalUser();
    user.userId().rawId(0L);
    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);

    //Clean up
    ResponseEntity responseEntity2 = requestGetUserById(user.userId());
    if (HttpStatus.OK.equals(responseEntity2.getStatusCode())) {
      deleteUserById(user.userId());
    }
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

    //Clean up
    ResponseEntity responseEntity2 = requestGetUserById(user.userId());
    if (HttpStatus.OK.equals(responseEntity2.getStatusCode())) {
      deleteUserById(user.userId());
    }
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

    //Clean up
    ResponseEntity responseEntity2 = requestGetUserById(user.userId());
    if (HttpStatus.OK.equals(responseEntity2.getStatusCode())) {
      deleteUserById(user.userId());
    }
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

    //Clean up
    ResponseEntity responseEntity2 = requestGetUserById(user.userId());
    if (HttpStatus.OK.equals(responseEntity2.getStatusCode())) {
      deleteUserById(user.userId());
    }
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
    UserId userId = createUser(user);

    User user2 = createAltLocalUser();
    user2.username(user.username());

    ResponseEntity<UserId> responseEntity = requestCreateUser(user2);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);

    //Clean up.
    deleteUserById(userId);
    if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
      deleteUserById(responseEntity.getBody());
    }
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

    //Clean up.
    deleteUserById(userId);
  }

  /**
   * Tests that you cannot retrieve a user from the database if its not there.
   */
  @Test
  public void testGetUser_IdNotInDB() {
    ResponseEntity<User> responseEntity = requestGetUserById(createUserId());
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

    //Clean up.
    deleteUserById(userId);
  }

  /**
   * Tests that you cannot retrieve a username from the database given its not there.
   */
  @Test
  public void testGetUsername_IdNotInDB() {
    ResponseEntity<String> responseEntity = requestGetUsername(createUserId());
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

    //Clean up.
    deleteUserById(userId);
  }

  /**
   * Tests that you cannot retrieve a userId from the database if its not there.
   */
  @Test
  public void testGetUserId_UsernameNotInDB() {
    ResponseEntity<UserId> responseEntity = requestGetUserId("myFakeUserName");
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




  //Still need to test delete by userid and delete by username.










//
//  /**
//   * Tests that a user can be gotten by username.
//   */
//  @Test
//  public void testGetUser_ByUsername() {
//    //Create the user.
//    User user = new User()
//        .id(3)
//        .username("bob1234")
//        .password("myPassword");
//    Integer userId = createUser(user);
//
//    //Get the created user.
//    User user2 = getUser(user.username());
//
//    assertThat(user)
//        .isEqualTo(user2);
//
//    //Clean up.
//    deleteUser(userId);
//  }
//
//  /**
//   * Tests that a user cannot be retrieved if the you give the endpoint null id.
//   */
//  @Test
//  public void testGetUser_ById_Null() {
//    ResponseEntity<User> responseEntity = requestGetUserById(null);
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests that a user cannot be retrieved if the you give the endpoint null username.
//   */
//  @Test
//  public void testGetUser_ByUsername_Null() {
//    ResponseEntity<User> responseEntity = requestGetUserByUsername(null);
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests that a user can be updated.
//   */
//  @Test
//  public void testUpdateUser() {
//    Integer userId = getOrCreateUserId();
//    User user = new User()
//        .id(userId)
//        .username("bob1234")
//        .password("myPassword");
//
//    updateUser(user);
//
//    User user2 = getUser(userId);
//
//    assertThat(user)
//        .isEqualTo(user2);
//  }
//
//  /**
//   * Tests that a user cannot be updated if the username is null.
//   */
//  @Test
//  public void testUpdateUser_NullUsername() {
//    Integer userId = getOrCreateUserId();
//    User user = new User()
//        .id(userId)
//        .password("myPassword");
//
//    ResponseEntity responseEntity = requestUpdateUser(user);
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests that the user cannot be updated if the password was null.
//   */
//  @Test
//  public void testUpdateUser_NullPassword() {
//    Integer userId = getOrCreateUserId();
//    User user = new User()
//        .id(userId)
//        .username("bob1234");
//
//    ResponseEntity responseEntity = requestUpdateUser(user);
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests that a user cannot be updated if the id doesn't exist.
//   */
//  @Test
//  public void testUpdateUser_DifferentIds() {
//    getOrCreateUserId();
//    User user = new User()
//        .id(3)
//        .username("bob1234")
//        .password("myPassword");
//
//    ResponseEntity responseEntity = requestUpdateUser(user);
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//
//    //Clean up.
//    requestDeleteUserById(user.id());
//  }
//
//  /**
//   * Tests that a user is deleted from the database by id.
//   */
//  @Test
//  public void testDeleteUser_ById() {
//    //Create and delete the user.
//    Integer userId = getOrCreateUserId();
//    deleteUser(userId);
//
//    ResponseEntity<User> responseEntity = requestGetUserById(userId);
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests that a user is deleted from the database by username.
//   */
//  @Test
//  public void testDeleteUser_ByUsername() {
//    //Create and delete the user.
//    Integer userId = getOrCreateUserId();
//    User user = getUser(userId);
//    deleteUser(user.username());
//
//    ResponseEntity<User> responseEntity = requestGetUserById(userId);
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests that a user cannot be deleted if id is null.
//   */
//  @Test
//  public void testDeleteUser_ById_NullId() {
//    ResponseEntity responseEntity = requestDeleteUserById(null);
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests that a user is deleted from the database by username.
//   */
//  @Test
//  public void testDeleteUser_ByUsername_NullUsername() {
//    ResponseEntity responseEntity = requestDeleteUserByUsername(null);
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
}
