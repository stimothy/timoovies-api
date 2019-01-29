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
    User user = createLocalUser();
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
    User user = createLocalUser();
    user.username(null);
    UserId userId = createUser(user);

    assertThat(userId.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that a user can be retrieved from the database.
   */
  @Test
  public void testGetUser_Valid() {
    User user = createLocalUser();
    UserId userId = createUser(user);
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
    UserId userId = createUserId();
    usersDataService.deleteUser(userId);

    User user = usersDataService.getUser(userId);

    assertThat(user)
        .isNull();
  }

  /**
   * Tests that a username can be retrieved from the database.
   */
  @Test
  public void testGetUsername_Valid() {
    User user = createLocalUser();
    UserId userId = createUser(user);

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
    UserId userId = createUserId();
    usersDataService.deleteUser(userId);

    String username = usersDataService.getUsername(userId);

    assertThat(username)
        .isNull();
  }

  /**
   * Tests that a userId can be retrieved from the database.
   */
  @Test
  public void testGetUserId_Valid() {
    User user = createLocalUser();
    createUser(user);

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
    User user = createLocalUser();
    usersDataService.deleteUser(user.userId());

    UserId userId = usersDataService.getUserId(user.username());

    assertThat(userId.getEncodedValue())
        .isNull();
  }

  /**
   * Tests that a user can be updated in the database.
   */
  @Test
  public void testUpdateUser_Valid() {
    UserId userId = getOrCreateUserId();
    User user = createLocalUser();
    user.userId(userId);

    assertThat(usersDataService.updateUser(user))
        .isTrue();
  }

  /**
   * Tests that a user cannot be updated in the database if its not there.
   */
  @Test
  public void testUpdateUser_Invalid_NotFound() {
    UserId userId = createUserId();
    usersDataService.deleteUser(userId);

    User user = createLocalUser();
    user.userId(userId);

    assertThat(usersDataService.updateUser(user))
        .isFalse();
  }

  /**
   * Tests that a user cannot be updated in the database invalid constraints.
   */
  @Test
  public void testUpdateUser_Invalid_BadData() {
    UserId userId = getOrCreateUserId();

    User user = createLocalUser();
    user.userId(userId);
    user.username(null);

    assertThat(usersDataService.updateUser(user))
        .isFalse();
  }

  /**
   * Tests that a user can be deleted from the database given a userId.
   */
  @Test
  public void testDeleteUserById_Valid() {
    UserId userId = getOrCreateUserId();

    assertThat(usersDataService.deleteUser(userId))
        .isTrue();
  }

  /**
   * Tests that a user cannot be deleted from the database given a userId if its not there.
   */
  @Test
  public void testDeleteUserById_Invalid() {
    UserId userId = createUserId();
    usersDataService.deleteUser(userId);

    assertThat(usersDataService.deleteUser(userId))
        .isFalse();
  }

  /**
   * Tests that a user can be deleted from the database given a username.
   */
  @Test
  public void testDeleteUserByUsername_Valid() {
    User user = createLocalUser();
    createUser(user);

    assertThat(usersDataService.deleteUser(user.username()))
        .isTrue();
  }

  /**
   * Tests that a user cannot be deleted from the database given a username if its not there.
   */
  @Test
  public void testDeleteUserByUsername_Invalid() {
    User user = createLocalUser();
    usersDataService.deleteUser(user.username());

    assertThat(usersDataService.deleteUser(user.username()))
        .isFalse();
  }
}
