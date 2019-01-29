package com.steventimothy.timoovies.repository.timoovies.users;

import com.steventimothy.timoovies.repository.schemas.DataUser;
import com.steventimothy.timoovies.schemas.ids.UserId;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersDbServiceTest extends UsersBaseComponent {

  /**
   * Tests that a dataUser can be inserted into the database.
   */
  @Test
  public void testInsert_Valid() {
    DataUser dataUser = createDataUser();

    assertThat(insert(dataUser))
        .isNotNull()
        .isEqualTo(dataUser.id());
  }

  /**
   * Tests that a dataUser cannot be inserted into the database due to constraints.
   */
  @Test
  public void testInsert() {
    DataUser dataUser = createDataUser();
    dataUser.username(null);

    assertThat(insert(dataUser))
        .isNull();
  }

  /**
   * Tests that a user can be retrieved from the database.
   */
  @Test
  public void testGetUser_Valid() {
    DataUser dataUser = createDataUser();
    UserId userId = new UserId().rawId(insert(dataUser));

    assertThat(usersDbService.getUser(userId.rawId()))
        .isNotNull()
        .isEqualToIgnoringGivenFields(dataUser, "enc_password", "date_created", "last_modified");
  }

  /**
   * Tests that a user cannot be retrieved from the database if its not there.
   */
  @Test
  public void testGetUser_Invalid() {
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    assertThat(usersDbService.getUser(userId.rawId()))
        .isNull();
  }

  /**
   * Tests that a username can be retrieved from the database.
   */
  @Test
  public void testGetUsername_Valid() {
    DataUser dataUser = createDataUser();
    UserId userId = new UserId().rawId(insert(dataUser));

    assertThat(usersDbService.getUsername(userId.rawId()))
        .isNotNull()
        .isEqualTo(dataUser.username());
  }

  /**
   * Tests that a username cannot be retrieved from the database if its not there.
   */
  @Test
  public void testGetUsername_Invalid() {
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    assertThat(usersDbService.getUsername(userId.rawId()))
        .isNull();
  }

  /**
   * Tests that a userId can be retrieved from the database.
   */
  @Test
  public void testGetUserId_Valid() {
    DataUser dataUser = createDataUser();
    UserId userId = new UserId().rawId(insert(dataUser));

    assertThat(usersDbService.getUserId(dataUser.username()))
        .isNotNull()
        .isEqualTo(userId.rawId());
  }

  /**
   * Tests that a userId cannot be retrieved from the database if its not there.
   */
  @Test
  public void testGetUserId_Invalid() {
    DataUser dataUser = createDataUser();
    usersDbService.delete(dataUser.id());

    assertThat(usersDbService.getUserId(dataUser.username()))
        .isNull();
  }

  /**
   * Tests that a user can be updated in the database.
   */
  @Test
  public void testUpdateUser_Valid() {
    UserId userId = getOrCreateUserId();

    DataUser dataUser = createDataUser();
    dataUser.id(userId.rawId());

    assertThat(usersDbService.update(dataUser))
        .isTrue();
  }

  /**
   * Tests that a user cannot be updated in the database if its not there.
   */
  @Test
  public void testUpdateUser_Invalid_NotThere() {
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    DataUser dataUser = createDataUser();
    dataUser.id(userId.rawId());

    assertThat(usersDbService.update(dataUser))
        .isFalse();
  }

  /**
   * Tests that a user cannot be updated in the database due to constraints.
   */
  @Test
  public void testUpdateUser_Invalid_BadData() {
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    DataUser dataUser = createDataUser();
    dataUser.username(null);

    assertThat(usersDbService.update(dataUser))
        .isFalse();
  }

  /**
   * Tests that a user can be deleted in the database by id.
   */
  @Test
  public void testDeleteUserById_Valid() {
    UserId userId = getOrCreateUserId();

    assertThat(usersDbService.delete(userId.rawId()))
        .isTrue();
  }

  /**
   * Tests that a user cannot be deleted in the database by id if its not there.
   */
  @Test
  public void testDeleteUserById_Invalid() {
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    assertThat(usersDbService.delete(userId.rawId()))
        .isFalse();
  }

  /**
   * Tests that a user can be deleted in the database by username.
   */
  @Test
  public void testDeleteUserByUsername_Valid() {
    DataUser dataUser = createDataUser();
    insert(dataUser);

    assertThat(usersDbService.delete(dataUser.username()))
        .isTrue();
  }

  /**
   * Tests that a user cannot be deleted in the database by username if its not there.
   */
  @Test
  public void testDeleteUserByUsername_Invalid() {
    DataUser dataUser = createDataUser();
    usersDbService.delete(dataUser.username());

    assertThat(usersDbService.delete(dataUser.username()))
        .isFalse();
  }
}
