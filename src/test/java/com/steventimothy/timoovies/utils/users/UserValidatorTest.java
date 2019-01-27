package com.steventimothy.timoovies.utils.users;

import com.steventimothy.timoovies.schemas.users.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserValidatorTest extends UsersBaseComponent {

  /**
   * Tests that the lower bound of tests userids is valid on create.
   */
  @Test
  public void testValidateCreateUser_TestId_LowerBound() {
    User user = createUser();
    user.userId().rawId(1L);

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that the upper bound of userids are valid on create.
   */
  @Test
  public void testValidateCreateUser_TestId_UpperBound() {
    User user = createUser();
    user.userId().rawId(10L);

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that a user is valid if it has a null raw id on create.
   */
  @Test
  public void testValidateCreateUser_NullId() {
    User user = createUser();
    user.userId().rawId(null);

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that non test users cannot provide an id on create lower bound check.
   */
  @Test
  public void testValidateCreateUser_NotTestId_LowerBound() {
    User user = createUser();
    user.userId().rawId(0L);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that non test users cannot provide an id on create upper bound check.
   */
  @Test
  public void testValidateCreateUser_NotTestId_UpperBound() {
    User user = createUser();
    user.userId().rawId(11L);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that no userId wrapper is not valid on create.
   */
  @Test
  public void testValidateCreateUser_NullUserIdWrapper() {
    User user = createUser();
    user.userId(null);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that no username is not valid on create.
   */
  @Test
  public void testValidateCreateUser_NullUsername() {
    User user = createUser();
    user.username(null);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that no password is not valid on create.
   */
  @Test
  public void testValidateCreateUser_NullPassword() {
    User user = createUser();
    user.password(null);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests a valid user on update.
   */
  @Test
  public void testValidateUpdateUser() {
    User user = createUser();

    assertThat(userValidator.validateUpdateUser(user))
        .isTrue();
  }

  /**
   * Tests that a null raw id on update is invalid.
   */
  @Test
  public void testValidateUpdateUser_NullId() {
    User user = createUser();
    user.userId().rawId(null);

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that a null UserId wrapper is not valid on update.
   */
  @Test
  public void testValidateUpdateUser_NullUserIdWrapper() {
    User user = createUser();
    user.userId(null);

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that a null username on update is invalid.
   */
  @Test
  public void testValidateUpdateUser_NullUsername() {
    User user = createUser();
    user.username(null);

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that a null password on update is invalid.
   */
  @Test
  public void testValidateUpdateUser_NullPassword() {
    User user = createUser();
    user.password(null);

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }
}
