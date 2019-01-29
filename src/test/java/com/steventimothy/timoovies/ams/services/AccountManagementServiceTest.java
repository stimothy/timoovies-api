package com.steventimothy.timoovies.ams.services;

import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementServiceTest extends ServicesBaseComponent {

  /**
   * This tests that a user can be created and also retrieved by id.
   */
  @Test
  public void testCreateUser() {
    //Create the user.
    User user = createLocalUser();
    UserId userId = createUser(user);

    //Get the created user.
    User user2 = accountManagementService.getUserById(userId);

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
    User user3 = accountManagementService.getUserById(userId2);

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
    UserId userId = createUser(user);

    assertThat(userId.getEncodedValue())
        .isNull();

    User user2 = accountManagementService.getUserById(user.userId());
    assertThat(user2)
        .isNull();
  }

  /**
   * Tests that a user cannot be created if their id is greater than 10.
   */
  @Test
  public void testCreateUser_NonTestId() {
    //Create the user.
    User user = createLocalUser();
    user.userId().rawId(11L);
    UserId userId = createUser(user);

    assertThat(userId.getEncodedValue())
        .isNull();

    User user2 = accountManagementService.getUserById(user.userId());
    assertThat(user2)
        .isNull();
  }

  /**
   * Tests that a user cannot be created if their username is null.
   */
  @Test
  public void testCreateUser_NoUsername() {
    //Create the user.
    User user = createLocalUser();
    user.username(null);
    UserId userId = createUser(user);

    assertThat(userId.getEncodedValue())
        .isNull();

    User user2 = accountManagementService.getUserById(user.userId());
    assertThat(user2)
        .isNull();
  }

  /**
   * Tests that a user cannot be created if their password is null.
   */
  @Test
  public void testCreateUser_NoPassword() {
    //Create the user.
    User user = createLocalUser();
    user.password(null);
    UserId userId = createUser(user);

    assertThat(userId.getEncodedValue())
        .isNull();

    User user2 = accountManagementService.getUserById(user.userId());
    assertThat(user2)
        .isNull();
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
    UserId userId2 = createUser(user);

    assertThat(userId2.getEncodedValue())
        .isNull();

    User user2 = accountManagementService.getUserById(userId);
    assertThat(user2)
        .isNotNull();
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

    UserId userId2 = createUser(user);

    assertThat(userId2.getEncodedValue())
        .isNull();

    User user3 = accountManagementService.getUserById(user2.userId());
    assertThat(user3)
        .isNull();
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
    User user2 = accountManagementService.getUserById(userId);

    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
  }

  /**
   * Tests that you cannot retrieve a user from the database if its not there.
   */
  @Test
  public void testGetUser_IdNotInDB() {
    UserId userId = createUserId();
    accountManagementService.deleteUserById(userId);

    assertThat(accountManagementService.getUserById(userId))
        .isNull();
  }


  /**
   * Tests that you cannot retrieve a user from the database given no id.
   */
  @Test
  public void testGetUser_IdNull() {
    assertThat(accountManagementService.getUserById(new UserId()))
        .isNull();
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
    String username = accountManagementService.getUsername(userId);

    assertThat(username)
        .isEqualTo(user.username());
  }

  /**
   * Tests that you cannot retrieve a username from the database given its not there.
   */
  @Test
  public void testGetUsername_IdNotInDB() {
    UserId userId = createUserId();
    accountManagementService.deleteUserById(userId);

    assertThat(accountManagementService.getUsername(userId))
        .isNull();
  }

  /**
   * Tests that you cannot retrieve a username from the database given no id.
   */
  @Test
  public void testGetUsername_IdNull() {
    assertThat(accountManagementService.getUserById(new UserId()))
        .isNull();
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
    UserId userId2 = accountManagementService.getUserId(user.username());

    assertThat(userId2)
        .isEqualTo(userId);
  }

  /**
   * Tests that you cannot retrieve a userId from the database if its not there.
   */
  @Test
  public void testGetUserId_UsernameNotInDB() {
    User user = createLocalUser();
    accountManagementService.deleteUserByUsername(user.username());

    assertThat(accountManagementService.getUserId(user.username()).getEncodedValue())
        .isNull();
  }

  /**
   * Tests that you cannot retrieve a userId from the database given no username.
   */
  @Test
  public void testGetUserId_UsernameNull() {
    assertThat(accountManagementService.getUserId(null).getEncodedValue())
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

    assertThat(accountManagementService.updateUser(user))
        .isTrue();

    User user2 = accountManagementService.getUserById(userId);

    assertThat(user2)
        .isEqualToIgnoringGivenFields(user, "password");
  }

  /**
   * Tests that a user cannot be updated in the database if it doesn't exist.
   */
  @Test
  public void testUpdateUser_UserDoesNotExist() {
    User user = createLocalUser();
    accountManagementService.deleteUserById(user.userId());

    //Create an updated user.
    User user2 = createAltLocalUser();
    user2.userId(user.userId());

    assertThat(accountManagementService.updateUser(user2))
        .isFalse();
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
    assertThat(accountManagementService.updateUser(user2))
        .isTrue();
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
    assertThat(accountManagementService.updateUser(user))
        .isFalse();
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

    //Update the users.
    assertThat(accountManagementService.updateUser(user))
        .isFalse();
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

    //Update the users.
    assertThat(accountManagementService.updateUser(user2))
        .isFalse();
  }

  /**
   * Tests that a user is deleted from the database by id.
   */
  @Test
  public void testDeleteUser_ById() {
    //Create and delete the user.
    UserId userId = getOrCreateUserId();
    assertThat(accountManagementService.deleteUserById(userId))
        .isTrue();

    assertThat(accountManagementService.getUserById(userId))
        .isNull();
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
    assertThat(accountManagementService.deleteUserByUsername(user.username()))
        .isTrue();

    assertThat(accountManagementService.getUserById(userId))
        .isNull();
  }

  /**
   * Tests that a user cannot be deleted if id is null.
   */
  @Test
  public void testDeleteUser_ById_NullId() {
    assertThat(accountManagementService.deleteUserById(new UserId()))
        .isFalse();
  }

  /**
   * Tests that a user is deleted from the database by username.
   */
  @Test
  public void testDeleteUser_ByUsername_NullUsername() {
    assertThat(accountManagementService.deleteUserByUsername(null))
        .isFalse();
  }

  /**
   * Tests that a user cannot be deleted if id is not there.
   */
  @Test
  public void testDeleteUser_ById_NotInDB() {
    UserId userId = createUserId();
    accountManagementService.deleteUserById(userId);

    assertThat(accountManagementService.deleteUserById(userId))
        .isFalse();
  }

  /**
   * Tests that a user is deleted from the database by username.
   */
  @Test
  public void testDeleteUser_ByUsername_NotInDB() {
    User user = createLocalUser();
    accountManagementService.deleteUserByUsername(user.username());

    assertThat(accountManagementService.deleteUserByUsername(user.username()))
        .isFalse();
  }
}
