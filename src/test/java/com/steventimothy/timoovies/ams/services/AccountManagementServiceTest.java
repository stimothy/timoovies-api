package com.steventimothy.timoovies.ams.services;

import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementServiceTest extends ServicesBaseComponent {

  /**
   * Tests that a user can be created.
   */
  @Test
  public void testCreateUser() {
    //Setup the user.
    User user = createLocalUser();

    //Create the user.
    UserId userId = createUser(user);
    assertThat(userId.getEncodedValue())
        .isNotNull();

    //Get the created user.
    User user2 = accountManagementService.getUserById(userId);
    assertThat(user)
        .isEqualToIgnoringGivenFields(user2, "password");
  }

  /**
   * Tests that an empty userId is returned if the user could not be created.
   */
  @Test
  public void testCreateUser_EmptyUserId() {
    //Setup the user.
    User user = createLocalUser();
    user.username(null);

    //Create the user.
    UserId userId = createUser(user);
    assertThat(userId.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that you can retrieve a user from the database.
   */
  @Test
  public void testGetUser() {
    //Setup the user.
    User user = createLocalUser();

    //Create the user.
    UserId userId = createUser(user);
    assertThat(userId.getEncodedValue())
        .isNotNull();

    //Get the user.
    User user2 = accountManagementService.getUserById(userId);
    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
  }

  /**
   * Tests that you get a null user if there was a problem getting the user.
   */
  @Test
  public void testGetUser_NullUser() {
    //Get the user with an empty id.
    User user2 = accountManagementService.getUserById(new UserId());
    assertThat(user2)
        .isNull();
  }

  /**
   * Tests that you can retrieve a username from the database.
   */
  @Test
  public void testGetUsername() {
    //Setup the user.
    User user = createLocalUser();

    //Create the user.
    UserId userId = createUser(user);
    assertThat(userId.getEncodedValue())
        .isNotNull();

    //Get the username.
    String username = accountManagementService.getUsername(userId);
    assertThat(username)
        .isEqualTo(user.username());
  }

  /**
   * Tests that a null username is returned if there was a problem.
   */
  @Test
  public void testGetUsername_NullUsername() {
    //Get the username given a blank userId.
    String username = accountManagementService.getUsername(new UserId());
    assertThat(username)
        .isNull();
  }

  /**
   * Tests that you can retrieve a userId from the database.
   */
  @Test
  public void testGetUserId() {
    //Setup the user.
    User user = createLocalUser();

    //Create the user.
    UserId userId = createUser(user);
    assertThat(userId.getEncodedValue())
        .isNotNull();

    //Get the userId.
    UserId userId2 = accountManagementService.getUserId(user.username());
    assertThat(userId2)
        .isEqualTo(userId);
  }

  /**
   * Tests that you get an empty userId if there was a problem.
   */
  @Test
  public void testGetUserId_EmptyUserId() {
    //Get the userId given a null username.
    UserId userId2 = accountManagementService.getUserId(null);
    assertThat(userId2.getEncodedValue())
        .isNull();
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

    //Update the user.
    assertThat(accountManagementService.updateUser(user))
        .isTrue();

    //Get the updated user.
    User user2 = accountManagementService.getUserById(userId);
    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
  }

  /**
   * Tests that the service returns false if it fails on an update.
   */
  @Test
  public void testUpdateUser_Fail() {
    //Setup a user.
    User user = createLocalUser();

    //Create the user.
    UserId userId = createUser(user);
    assertThat(userId.getEncodedValue())
        .isNotNull();

    //Create a different user.
    User user2 = createAltLocalUser();
    user2.username(null);

    //Update the user.
    assertThat(accountManagementService.updateUser(user2))
        .isFalse();

    //Get the not updated user.
    User user3 = accountManagementService.getUserById(userId);
    assertThat(user3)
        .isEqualToIgnoringGivenFields(user, "password");
  }

  /**
   * Tests that a user is deleted from the database by id.
   */
  @Test
  public void testDeleteUserById() {
    //Create and delete the user.
    UserId userId = getOrCreateUserId();
    assertThat(userId.getEncodedValue())
        .isNotNull();

    //Get the created user
    assertThat(accountManagementService.getUserById(userId))
        .isNotNull();

    //Delete the user
    assertThat(accountManagementService.deleteUserById(userId))
        .isTrue();

    //Get the deleted user
    assertThat(accountManagementService.getUserById(userId))
        .isNull();
  }

  /**
   * Tests that false is returned if the user could not be deleted by userId.
   */
  @Test
  public void testDeleteUserById_Fail() {
    //Delete the user with an empty userId.
    assertThat(accountManagementService.deleteUserById(new UserId()))
        .isFalse();
  }

  /**
   * Tests that a user is deleted from the database by username.
   */
  @Test
  public void testDeleteUserByUsername() {
    //Setup the user.
    User user = createLocalUser();

    //Create the user.
    UserId userId = createUser(user);
    assertThat(userId.getEncodedValue())
        .isNotNull();

    //Get the user.
    assertThat(accountManagementService.getUserById(userId))
        .isNotNull();

    //Delete the user
    assertThat(accountManagementService.deleteUserByUsername(user.username()))
        .isTrue();

    //Get the deleted user.
    assertThat(accountManagementService.getUserById(userId))
        .isNull();
  }

  /**
   * Tests that false is returned if it could not delete the user by username.
   */
  @Test
  public void testDeleteUserByUsername_Fail() {
    //Delete the user
    assertThat(accountManagementService.deleteUserByUsername(null))
        .isFalse();
  }
}
