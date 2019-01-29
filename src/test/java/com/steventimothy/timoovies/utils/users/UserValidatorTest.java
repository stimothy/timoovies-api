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
    User user = createLocalUser();
    user.userId().rawId(1L);

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that the upper bound of userids are valid on create.
   */
  @Test
  public void testValidateCreateUser_TestId_UpperBound() {
    User user = createLocalUser();
    user.userId().rawId(10L);

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that a user is valid if it has a null raw id on create.
   */
  @Test
  public void testValidateCreateUser_NullId() {
    User user = createLocalUser();
    user.userId().rawId(null);

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that non test users cannot provide an id on create lower bound check.
   */
  @Test
  public void testValidateCreateUser_NotTestId_LowerBound() {
    User user = createLocalUser();
    user.userId().rawId(0L);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that non test users cannot provide an id on create upper bound check.
   */
  @Test
  public void testValidateCreateUser_NotTestId_UpperBound() {
    User user = createLocalUser();
    user.userId().rawId(11L);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that no userId wrapper is not valid on create.
   */
  @Test
  public void testValidateCreateUser_NullUserIdWrapper() {
    User user = createLocalUser();
    user.userId(null);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that no username is not valid on create.
   */
  @Test
  public void testValidateCreateUser_NullUsername() {
    User user = createLocalUser();
    user.username(null);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that no password is not valid on create.
   */
  @Test
  public void testValidateCreateUser_NullPassword() {
    User user = createLocalUser();
    user.password(null);

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that the user can be created with a username more than 4 characters long.
   */
  @Test
  public void testValidateCreateUser_UsernameMinLength() {
    User user = createLocalUser();
    user.username("12345");

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that the user can be created with a username less than 51 characters long.
   */
  @Test
  public void testValidateCreateUser_UsernameMaxLength() {
    User user = createLocalUser();
    user.username("12345678901234567890123456789012345678901234567890");

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that the user cannot be created with a username less than 5 characters long.
   */
  @Test
  public void testValidateCreateUser_UsernameLengthTooSmall() {
    User user = createLocalUser();
    user.username("1234");

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that the user cannot be created with a username more than 50 characters long.
   */
  @Test
  public void testValidateCreateUser_UsernameLengthTooBig() {
    User user = createLocalUser();
    user.username("123456789012345678901234567890123456789012345678901");

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that the user can be created with a password more than 4 characters long.
   */
  @Test
  public void testValidateCreateUser_PasswordMinLength() {
    User user = createLocalUser();
    user.password("12345");

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that the user can be created with a password less than 65 characters long.
   */
  @Test
  public void testValidateCreateUser_PasswordMaxLength() {
    User user = createLocalUser();
    user.password("1234567890123456789012345678901234567890123456789012345678901234");

    assertThat(userValidator.validateCreateUser(user))
        .isTrue();
  }

  /**
   * Tests that the user cannot be created with a password less than 5 characters long.
   */
  @Test
  public void testValidateCreateUser_PasswordLengthTooSmall() {
    User user = createLocalUser();
    user.password("1234");

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests that the user cannot be created with a password more than 64 characters long.
   */
  @Test
  public void testValidateCreateUser_PasswordLengthTooBig() {
    User user = createLocalUser();
    user.password("12345678901234567890123456789012345678901234567890123456789012345");

    assertThat(userValidator.validateCreateUser(user))
        .isFalse();
  }

  /**
   * Tests a valid user on update.
   */
  @Test
  public void testValidateUpdateUser() {
    User user = createLocalUser();

    assertThat(userValidator.validateUpdateUser(user))
        .isTrue();
  }

  /**
   * Tests that a null raw id on update is invalid.
   */
  @Test
  public void testValidateUpdateUser_NullId() {
    User user = createLocalUser();
    user.userId().rawId(null);

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that a null UserId wrapper is not valid on update.
   */
  @Test
  public void testValidateUpdateUser_NullUserIdWrapper() {
    User user = createLocalUser();
    user.userId(null);

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that a null username on update is invalid.
   */
  @Test
  public void testValidateUpdateUser_NullUsername() {
    User user = createLocalUser();
    user.username(null);

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that a null password on update is invalid.
   */
  @Test
  public void testValidateUpdateUser_NullPassword() {
    User user = createLocalUser();
    user.password(null);

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that the user can be updated with a username more than 4 characters long.
   */
  @Test
  public void testValidateUpdateUser_UsernameMinLength() {
    User user = createLocalUser();
    user.username("12345");

    assertThat(userValidator.validateUpdateUser(user))
        .isTrue();
  }

  /**
   * Tests that the user can be updated with a username less than 51 characters long.
   */
  @Test
  public void testValidateUpdateUser_UsernameMaxLength() {
    User user = createLocalUser();
    user.username("12345678901234567890123456789012345678901234567890");

    assertThat(userValidator.validateUpdateUser(user))
        .isTrue();
  }

  /**
   * Tests that the user cannot be updated with a username less than 5 characters long.
   */
  @Test
  public void testValidateUpdateUser_UsernameLengthTooSmall() {
    User user = createLocalUser();
    user.username("1234");

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that the user cannot be updated with a username more than 50 characters long.
   */
  @Test
  public void testValidateUpdateUser_UsernameLengthTooBig() {
    User user = createLocalUser();
    user.username("123456789012345678901234567890123456789012345678901");

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that the user can be updated with a password more than 4 characters long.
   */
  @Test
  public void testValidateUpdateUser_PasswordMinLength() {
    User user = createLocalUser();
    user.password("12345");

    assertThat(userValidator.validateUpdateUser(user))
        .isTrue();
  }

  /**
   * Tests that the user can be updated with a password less than 65 characters long.
   */
  @Test
  public void testValidateUpdateUser_PasswordMaxLength() {
    User user = createLocalUser();
    user.password("1234567890123456789012345678901234567890123456789012345678901234");

    assertThat(userValidator.validateUpdateUser(user))
        .isTrue();
  }

  /**
   * Tests that the user cannot be updated with a password less than 5 characters long.
   */
  @Test
  public void testValidateUpdateUser_PasswordLengthTooSmall() {
    User user = createLocalUser();
    user.password("1234");

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }

  /**
   * Tests that the user cannot be updated with a password more than 64 characters long.
   */
  @Test
  public void testValidateUpdateUser_PasswordLengthTooBig() {
    User user = createLocalUser();
    user.password("12345678901234567890123456789012345678901234567890123456789012345");

    assertThat(userValidator.validateUpdateUser(user))
        .isFalse();
  }
}
