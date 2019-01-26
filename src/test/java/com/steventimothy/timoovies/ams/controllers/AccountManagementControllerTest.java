package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.BaseComponent;
import com.steventimothy.timoovies.schema.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementControllerTest extends BaseComponent {

  /**
   * This tests that a user can be created and also retrieved by id.
   */
  @Test
  public void testCreateUserGetUserById() {
    //Create the user.
    User user = new User()
        .id(3)
        .username("bob1234")
        .password("myPassword");
    Integer userId = createUser(user);

    //Get the created user.
    User user2 = getUser(userId);

    assertThat(user)
        .isEqualTo(user2);

    //Clean up.
    deleteUser(userId);
  }

  /**
   * This tests that a user can be created even with duplicate passwords.
   */
  @Test
  public void testCreateUser_DuplicatePasswords() {
    //Create the users.
    Integer userId = getOrCreateUserId();
    User user = getUser(userId);
    User user2 = new User()
        .id(3)
        .username("bob1234")
        .password(user.password());
    Integer userId2 = createUser(user2);

    //Get the created user.
    User user3 = getUser(userId2);

    assertThat(user2)
        .isEqualTo(user3);

    //Clean up.
    deleteUser(userId2);
  }

  /**
   * Tests that a user can be created with an id of zero.
   */
  @Test
  public void testCreateUser_IdZero() {
    //Create the user.
    User user = new User()
        .id(0)
        .username("bob1234")
        .password("myPassword");
    ResponseEntity<Integer> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);

    //Clean up
    ResponseEntity responseEntity2 = requestGetUserById(user.id());
    if (HttpStatus.OK.equals(responseEntity2.getStatusCode())) {
      deleteUser(user.id());
    }
  }

  /**
   * Tests that a user cannot be created if their id is greater than 10.
   */
  @Test
  public void testCreateUser_NonTestId() {
    //Create the user.
    User user = new User()
        .id(11)
        .username("bob1234")
        .password("myPassword");
    ResponseEntity<Integer> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);

    //Clean up
    ResponseEntity responseEntity2 = requestGetUserById(user.id());
    if (HttpStatus.OK.equals(responseEntity2.getStatusCode())) {
      deleteUser(user.id());
    }
  }

  /**
   * Tests that a user cannot be created if their username is null.
   */
  @Test
  public void testCreateUser_NoUsername() {
    //Create the user.
    User user = new User()
        .id(3)
        .password("myPassword");
    ResponseEntity<Integer> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);

    //Clean up
    ResponseEntity responseEntity2 = requestGetUserById(user.id());
    if (HttpStatus.OK.equals(responseEntity2.getStatusCode())) {
      deleteUser(user.id());
    }
  }

  /**
   * Tests that a user cannot be created if their password is null.
   */
  @Test
  public void testCreateUser_NoPassword() {
    //Create the user.
    User user = new User()
        .id(3)
        .username("bob1234");
    ResponseEntity<Integer> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);

    //Clean up
    ResponseEntity responseEntity2 = requestGetUserById(user.id());
    if (HttpStatus.OK.equals(responseEntity2.getStatusCode())) {
      deleteUser(user.id());
    }
  }

  /**
   * Tests that a user cannot be created with identical ids.
   */
  @Test
  public void testCreateUser_IdenticalIds() {
    //Create the users.
    Integer userId = getOrCreateUserId();
    User user = new User()
        .id(userId)
        .username("bob1234")
        .password("myPassword");
    ResponseEntity<Integer> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be created with identical usernames.
   */
  @Test
  public void testCreateUser_IdenticalUsernames() {
    //Create the users.
    Integer userId = getOrCreateUserId();
    User user = getUser(userId);
    User user2 = new User()
        .id(3)
        .username(user.username())
        .password("myPassword");
    ResponseEntity<Integer> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);

    //Clean up.
    requestDeleteUserById(user2.id());
  }

  /**
   * Tests that a user can be gotten by username.
   */
  @Test
  public void testGetUser_ByUsername() {
    //Create the user.
    User user = new User()
        .id(3)
        .username("bob1234")
        .password("myPassword");
    Integer userId = createUser(user);

    //Get the created user.
    User user2 = getUser(user.username());

    assertThat(user)
        .isEqualTo(user2);

    //Clean up.
    deleteUser(userId);
  }

  /**
   * Tests that a user cannot be retrieved if the you give the endpoint null id.
   */
  @Test
  public void testGetUser_ById_Null() {
    ResponseEntity<User> responseEntity = requestGetUserById(null);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be retrieved if the you give the endpoint null username.
   */
  @Test
  public void testGetUser_ByUsername_Null() {
    ResponseEntity<User> responseEntity = requestGetUserByUsername(null);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user can be updated.
   */
  @Test
  public void testUpdateUser() {
    Integer userId = getOrCreateUserId();
    User user = new User()
        .id(userId)
        .username("bob1234")
        .password("myPassword");

    updateUser(user);

    User user2 = getUser(userId);

    assertThat(user)
        .isEqualTo(user2);
  }

  /**
   * Tests that a user cannot be updated if the username is null.
   */
  @Test
  public void testUpdateUser_NullUsername() {
    Integer userId = getOrCreateUserId();
    User user = new User()
        .id(userId)
        .password("myPassword");

    ResponseEntity responseEntity = requestUpdateUser(user);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that the user cannot be updated if the password was null.
   */
  @Test
  public void testUpdateUser_NullPassword() {
    Integer userId = getOrCreateUserId();
    User user = new User()
        .id(userId)
        .username("bob1234");

    ResponseEntity responseEntity = requestUpdateUser(user);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be updated if the id doesn't exist.
   */
  @Test
  public void testUpdateUser_DifferentIds() {
    getOrCreateUserId();
    User user = new User()
        .id(3)
        .username("bob1234")
        .password("myPassword");

    ResponseEntity responseEntity = requestUpdateUser(user);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);

    //Clean up.
    requestDeleteUserById(user.id());
  }

  /**
   * Tests that a user is deleted from the database by id.
   */
  @Test
  public void testDeleteUser_ById() {
    //Create and delete the user.
    Integer userId = getOrCreateUserId();
    deleteUser(userId);

    ResponseEntity<User> responseEntity = requestGetUserById(userId);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user is deleted from the database by username.
   */
  @Test
  public void testDeleteUser_ByUsername() {
    //Create and delete the user.
    Integer userId = getOrCreateUserId();
    User user = getUser(userId);
    deleteUser(user.username());

    ResponseEntity<User> responseEntity = requestGetUserById(userId);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user cannot be deleted if id is null.
   */
  @Test
  public void testDeleteUser_ById_NullId() {
    ResponseEntity responseEntity = requestDeleteUserById(null);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests that a user is deleted from the database by username.
   */
  @Test
  public void testDeleteUser_ByUsername_NullUsername() {
    ResponseEntity responseEntity = requestDeleteUserByUsername(null);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }
}
