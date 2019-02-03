package com.steventimothy.timoovies.ams;

import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementComponentTest extends AmsBaseComponent {

  /**
   * Tests that a user can be handle appropriately given good calls.
   */
  @Test
  public void testValidAmsCalls() {
    //Create the user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the user.
    User user2 = getUserById(userId);
    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
    //Get the username.
    String username = getUsername(userId);
    assertThat(username)
        .isEqualTo(user.username());
    //Get the userId.
    UserId userId2 = getUserId(user.username());
    assertThat(userId2)
        .isEqualTo(userId);

    //Setup updated user.
    User user3 = createAltLocalUser();
    user3.userId(userId);

    //Update the user.
    updateUser(user3);

    //Get the user.
    User user4 = getUserById(userId);
    assertThat(user4)
        .isEqualToIgnoringGivenFields(user3, "password");
    //Get the username.
    String username2 = getUsername(userId);
    assertThat(username2)
        .isEqualTo(user3.username());
    //Get the userId.
    UserId userId3 = getUserId(user3.username());
    assertThat(userId3)
        .isEqualTo(userId);

    //Create another user.
    UserId userId4 = getOrCreateUserId();

    //Delete user by userId and username.
    deleteUserById(userId4);
    deleteByUsername(user3.username());
  }

  /**
   * Tests that a user can be handle appropriately given bad calls.
   */
  @Test
  public void testInvalidAmsCalls() {
    //Create the user.
    User user = createLocalUser();
    user.username(null);
    ResponseEntity<UserId> createResponseEntity = requestCreateUser(user);
    assertStatus(createResponseEntity, HttpStatus.BAD_REQUEST);

    UserId userId = getOrCreateUserId();

    //Get the user.
    ResponseEntity<User> getUserResponseEntity = requestGetUserById(new UserId());
    assertStatus(getUserResponseEntity, HttpStatus.BAD_REQUEST);
    //Get the username.
    ResponseEntity<String> getUsernameResponseEntity = requestGetUsername(new UserId());
    assertStatus(getUsernameResponseEntity, HttpStatus.BAD_REQUEST);
    //Get the userId.
    ResponseEntity<UserId> getUserIdResponseEntity = requestGetUserId(null);
    assertStatus(getUserIdResponseEntity, HttpStatus.BAD_REQUEST);

    User user2 = createAltLocalUser();
    user2.password(null);

    //Update the user.
    ResponseEntity updateResponseEntity = requestUpdateUser(user2);
    assertStatus(updateResponseEntity, HttpStatus.BAD_REQUEST);

    //Delete user by userId and username.
    ResponseEntity deleteResponseEntity = requestDeleteUserById(new UserId());
    assertStatus(deleteResponseEntity, HttpStatus.BAD_REQUEST);
    deleteResponseEntity = requestDeleteByUsername(null);
    assertStatus(deleteResponseEntity, HttpStatus.BAD_REQUEST);
  }
}
