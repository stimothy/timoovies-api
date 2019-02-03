package com.steventimothy.timoovies.repository.timoovies.users;

import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersDataServiceTest extends UsersBaseComponent {

  /**
   * Tests that a valid user can be created.
   */
  @Test
  public void testCreateUser() {
    //Setup a user.
    User user = createLocalUser();

    //Create a user.
    UserId userId = createUser(user);
    assertThat(userId.getEncodedValue())
        .isNotNull()
        .isEqualTo(user.userId().getEncodedValue());
  }

  /**
   * Tests that an invalid user cannot be created.
   */
  @Test
  public void testCreateUser_Invalid() {
    //Set up a user.
    User user = createLocalUser();
    user.username(null);

    //Create a user.
    UserId userId = createUser(user);
    assertThat(userId.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that a user can be retrieved from the database.
   */
  @Test
  public void testGetUser_Valid() {
    //Set up a user and create a user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the user.
    User user2 = usersDataService.getUser(userId);
    assertThat(user2)
        .isNotNull()
        .isEqualToIgnoringGivenFields(user, "password");
  }

  /**
   * Tests that a user cannot be retrieved from the database if its not there.
   */
  @Test
  public void testGetUser_Invalid() {
    //Make sure no user exists in the database
    UserId userId = createUserId();
    usersDataService.deleteUser(userId);

    //Get the user.
    User user = usersDataService.getUser(userId);
    assertThat(user)
        .isNull();
  }

  /**
   * Tests that a username can be retrieved from the database.
   */
  @Test
  public void testGetUsername_Valid() {
    //Set up and create the user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the username.
    String username = usersDataService.getUsername(userId);
    assertThat(username)
        .isNotNull()
        .isEqualTo(user.username());
  }

  /**
   * Tests that a username cannot be retrieved from the database if its not there.
   */
  @Test
  public void testGetUsername_Invalid() {
    //Make sure the user doesn't exist in the database.
    UserId userId = createUserId();
    usersDataService.deleteUser(userId);

    //Get the username.
    String username = usersDataService.getUsername(userId);
    assertThat(username)
        .isNull();
  }

  /**
   * Tests that a userId can be retrieved from the database.
   */
  @Test
  public void testGetUserId_Valid() {
    //Setup and create the user.
    User user = createLocalUser();
    createUser(user);

    //Get the userId.
    UserId userId = usersDataService.getUserId(user.username());
    assertThat(userId.getEncodedValue())
        .isNotNull()
        .isEqualTo(user.userId().getEncodedValue());
  }

  /**
   * Tests that a userId cannot be retrieved from the database if its not there.
   */
  @Test
  public void testGetUserId_Invalid() {
    //Make user the user doesn't exist in the database.
    User user = createLocalUser();
    usersDataService.deleteUser(user.userId());

    //Get the userId.
    UserId userId = usersDataService.getUserId(user.username());
    assertThat(userId.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that a user can be updated in the database.
   */
  @Test
  public void testUpdateUser_Valid() {
    //Create a user in the database.
    UserId userId = getOrCreateUserId();

    //Setup an updated user.
    User user = createLocalUser();
    user.userId(userId);

    //Update the user.
    assertThat(usersDataService.updateUser(user))
        .isTrue();
  }

  /**
   * Tests that a user cannot be updated in the database if its not there.
   */
  @Test
  public void testUpdateUser_Invalid_NotFound() {
    //Make sure the user doesn't exist.
    UserId userId = createUserId();
    usersDataService.deleteUser(userId);

    //Setup an updated user.
    User user = createLocalUser();
    user.userId(userId);

    //Update the user.
    assertThat(usersDataService.updateUser(user))
        .isFalse();
  }

  /**
   * Tests that a user cannot be updated in the database invalid constraints.
   */
  @Test
  public void testUpdateUser_Invalid_BadData() {
    //Create a user.
    UserId userId = getOrCreateUserId();

    //Setup an updated user.
    User user = createLocalUser();
    user.userId(userId);
    user.username(null);

    //Update the user.
    assertThat(usersDataService.updateUser(user))
        .isFalse();
  }

  /**
   * Tests that a user can be deleted from the database given a userId.
   */
  @Test
  public void testDeleteUserById_Valid() {
    //Create a user.
    UserId userId = getOrCreateUserId();

    //Delete the user.
    assertThat(usersDataService.deleteUser(userId))
        .isTrue();
  }

  /**
   * Tests that a user cannot be deleted from the database given a userId if its not there.
   */
  @Test
  public void testDeleteUserById_Invalid() {
    //Make user the user doesn't exist.
    UserId userId = createUserId();
    usersDataService.deleteUser(userId);

    //Delete the user.
    assertThat(usersDataService.deleteUser(userId))
        .isFalse();
  }

  /**
   * Tests that a user can be deleted from the database given a username.
   */
  @Test
  public void testDeleteUserByUsername_Valid() {
    //Setup and create a user.
    User user = createLocalUser();
    createUser(user);

    //Delete the user.
    assertThat(usersDataService.deleteUser(user.username()))
        .isTrue();
  }

  /**
   * Tests that a user cannot be deleted from the database given a username if its not there.
   */
  @Test
  public void testDeleteUserByUsername_Invalid() {
    //Make sure the user doesn't exist.
    User user = createLocalUser();
    usersDataService.deleteUser(user.username());

    //Delete the user.
    assertThat(usersDataService.deleteUser(user.username()))
        .isFalse();
  }
}
