package com.steventimothy.timoovies.ams;

import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementServiceSystemTest extends AmsBaseComponent {

  /**
   * Tests the functionality of ams using userIds.
   */
  @Test
  public void testAmsSystemFunctionalitiesUsingIds() {
    //Create a user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the user.
    User user2 = getUserById(userId);
    String username = getUsername(userId);
    assertThat(user2.username())
        .isEqualTo(username)
        .isEqualTo(user.username());

    //Update the user.
    User user3 = createAltLocalUser();
    user3.userId(user2.userId());
    updateUser(user3);
    ResponseEntity updateResponseEntity = requestUpdateUser(user2);

    //Get the user.
    User user4 = getUserById(user2.userId());
    assertThat(user4)
        .isEqualToComparingOnlyGivenFields(user2, "userId");
    assertThat(user4.username())
        .isNotEqualTo(user2.username());

    //Delete the user.
    deleteUserById(user4.userId());

    //Get the user.
    ResponseEntity responseEntity = requestGetUserById(user4.userId());
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests the functionality of ams using usernames.
   */
  @Test
  public void testAmsSystemFunctionalitiesUsingUsernames() {
    //Create a user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the user.
    UserId userId2 = getUserId(user.username());
    assertThat(userId2)
        .isEqualTo(userId);

    //Update the user.
    User user2 = createAltLocalUser();
    user2.userId(user.userId());
    updateUser(user2);

    UserId userId3 = getUserId(user2.username());
    assertThat(userId3)
        .isEqualTo(userId2)
        .isEqualTo(userId);

    //Delete the user.
    deleteByUsername(user2.username());

    //Get the user.
    ResponseEntity<UserId> responseEntity = requestGetUserId(user2.username());
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
  }

  /**
   * Tests the functionality of ams using userIds and userNames.
   */
  @Test
  public void testAmsSystemFunctionalitiesUsingIdsAndUsernames() {
    //Create a user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the user.
    User user2 = getUserById(userId);
    String username = getUsername(userId);
    UserId userId2 = getUserId(user.username());

    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
    assertThat(username)
        .isEqualTo(user.username())
        .isEqualTo(user2.username());
    assertThat(userId2)
        .isEqualTo(userId)
        .isEqualTo(userId2);

    //Update the user.
    User user3 = createAltLocalUser();
    user3.userId(userId);
    updateUser(user3);

    //Get the user.
    User user4 = getUserById(userId);
    String username2 = getUsername(userId);
    UserId userId3 = getUserId(user3.username());

    assertThat(user4.userId())
        .isEqualTo(userId)
        .isEqualTo(userId2)
        .isEqualTo(userId3);
    assertThat(username2)
        .isEqualTo(user3.username())
        .isNotEqualTo(username);

    //Delete the user.
    deleteUserById(userId);

    UserId userId4 = getOrCreateUserId();
    String username3 = getUsername(userId4);
    deleteByUsername(username3);

    //Get the user.
    ResponseEntity<User> responseEntity = requestGetUserById(userId);
    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
    ResponseEntity<String> responseEntity2 = requestGetUsername(userId);
    assertStatus(responseEntity2, HttpStatus.BAD_REQUEST);
    ResponseEntity<UserId> responseEntity3 = requestGetUserId(username3);
    assertStatus(responseEntity3, HttpStatus.BAD_REQUEST);
  }
}
