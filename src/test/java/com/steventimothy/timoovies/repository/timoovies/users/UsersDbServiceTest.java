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
    //Setup a user.
    DataUser dataUser = createDataUser();

    //Create the user.
    assertThat(insert(dataUser))
        .isNotNull()
        .isEqualTo(dataUser.id());
  }

  /**
   * Tests that a dataUser cannot be inserted into the database if there is no username.
   */
  @Test
  public void testInsert_Invalid_NoUsername() {
    //Setup the user.
    DataUser dataUser = createDataUser();
    dataUser.username(null);

    //Create the user.
    assertThat(insert(dataUser))
        .isNull();
  }

  /**
   * Tests that a dataUser cannot be inserted into the database if there is no password.
   */
  @Test
  public void testInsert_Invalid_NoPassword() {
    //Setup the user.
    DataUser dataUser = createDataUser();
    dataUser.enc_password(null);

    //Create the user.
    assertThat(insert(dataUser))
        .isNull();
  }

  /**
   * Tests that a dataUser cannot be inserted into the database if there is already the userId.
   */
  @Test
  public void testInsert_Invalid_DuplicateUserId() {
    //Create a user in the database.
    UserId userId = getOrCreateUserId();

    //Setup the new user.
    DataUser dataUser = createDataUser();
    dataUser.id(userId.rawId());

    //Create the user.
    assertThat(insert(dataUser))
        .isNull();
  }

  /**
   * Tests that a dataUser cannot be inserted into the database if there is already the username.
   */
  @Test
  public void testInsert_Invalid_DuplicateUsername() {
    //Create a user in the database.
    DataUser dataUser = createDataUser();
    insert(dataUser);

    //Setup the new user.
    DataUser dataUser2 = createAltDataUser();
    dataUser2.username(dataUser.username());

    //Create the user.
    assertThat(insert(dataUser2))
        .isNull();
  }

  /**
   * Tests that a user can be retrieved from the database.
   */
  @Test
  public void testGetUser_Valid() {
    //Create the user.
    DataUser dataUser = createDataUser();
    UserId userId = new UserId().rawId(insert(dataUser));

    //Get the user.
    assertThat(usersDbService.getUser(userId.rawId()))
        .isNotNull()
        .isEqualToIgnoringGivenFields(dataUser, "enc_password", "date_created", "last_modified");
  }

  /**
   * Tests that a user cannot be retrieved from the database if its not there.
   */
  @Test
  public void testGetUser_Invalid() {
    //Make sure the user is not in the database.
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    //Get the user.
    assertThat(usersDbService.getUser(userId.rawId()))
        .isNull();
  }

  /**
   * Tests that a username can be retrieved from the database.
   */
  @Test
  public void testGetUsername_Valid() {
    //Create a user.
    DataUser dataUser = createDataUser();
    UserId userId = new UserId().rawId(insert(dataUser));

    //Get the username.
    assertThat(usersDbService.getUsername(userId.rawId()))
        .isNotNull()
        .isEqualTo(dataUser.username());
  }

  /**
   * Tests that a username cannot be retrieved from the database if its not there.
   */
  @Test
  public void testGetUsername_Invalid() {
    //Make sure the user is not in the database.
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    //Get the username.
    assertThat(usersDbService.getUsername(userId.rawId()))
        .isNull();
  }

  /**
   * Tests that a userId can be retrieved from the database.
   */
  @Test
  public void testGetUserId_Valid() {
    //Create a user.
    DataUser dataUser = createDataUser();
    UserId userId = new UserId().rawId(insert(dataUser));

    //Get the userId.
    assertThat(usersDbService.getUserId(dataUser.username()))
        .isNotNull()
        .isEqualTo(userId.rawId());
  }

  /**
   * Tests that a userId cannot be retrieved from the database if its not there.
   */
  @Test
  public void testGetUserId_Invalid() {
    //Make sure the user is not in the database.
    DataUser dataUser = createDataUser();
    usersDbService.delete(dataUser.id());

    //Get the userId.
    assertThat(usersDbService.getUserId(dataUser.username()))
        .isNull();
  }

  /**
   * Tests that a user can be updated in the database.
   */
  @Test
  public void testUpdateUser_Valid() {
    //Create a user.
    UserId userId = getOrCreateUserId();

    //Setup the updated user.
    DataUser dataUser = createDataUser();
    dataUser.id(userId.rawId());

    //Update the user.
    assertThat(usersDbService.update(dataUser))
        .isTrue();
  }

  /**
   * Tests that a user cannot be updated in the database if its not there.
   */
  @Test
  public void testUpdateUser_Invalid_NotThere() {
    //Make sure the user is not ther.
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    //Setup the updated user.
    DataUser dataUser = createDataUser();
    dataUser.id(userId.rawId());

    //Update the user.
    assertThat(usersDbService.update(dataUser))
        .isFalse();
  }

  /**
   * Tests that a user cannot be updated in the database due to no username.
   */
  @Test
  public void testUpdateUser_Invalid_NoUsername() {
    //Create a user.
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    //Setup an updated user.
    DataUser dataUser = createDataUser();
    dataUser.username(null);

    //Update the user.
    assertThat(usersDbService.update(dataUser))
        .isFalse();
  }

  /**
   * Tests that a user cannot be updated in the database due to no password.
   */
  @Test
  public void testUpdateUser_Invalid_NoPassword() {
    //Create a user.
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    //Setup an updated user.
    DataUser dataUser = createDataUser();
    dataUser.enc_password(null);

    //Update the user.
    assertThat(usersDbService.update(dataUser))
        .isFalse();
  }

  /**
   * Tests that a user can be deleted in the database by id.
   */
  @Test
  public void testDeleteUserById_Valid() {
    //Create a user.
    UserId userId = getOrCreateUserId();

    //Delete the user.
    assertThat(usersDbService.delete(userId.rawId()))
        .isTrue();
  }

  /**
   * Tests that a user cannot be deleted in the database by id if its not there.
   */
  @Test
  public void testDeleteUserById_Invalid() {
    //Make sure the user is not in the database.
    UserId userId = createUserId();
    usersDbService.delete(userId.rawId());

    //Delete the user.
    assertThat(usersDbService.delete(userId.rawId()))
        .isFalse();
  }

  /**
   * Tests that a user can be deleted in the database by username.
   */
  @Test
  public void testDeleteUserByUsername_Valid() {
    //Create the user.
    DataUser dataUser = createDataUser();
    insert(dataUser);

    //Delete the user.
    assertThat(usersDbService.delete(dataUser.username()))
        .isTrue();
  }

  /**
   * Tests that a user cannot be deleted in the database by username if its not there.
   */
  @Test
  public void testDeleteUserByUsername_Invalid() {
    //Make sure the user is not in the database.
    DataUser dataUser = createDataUser();
    usersDbService.delete(dataUser.username());

    //Delete the user.
    assertThat(usersDbService.delete(dataUser.username()))
        .isFalse();
  }
}
