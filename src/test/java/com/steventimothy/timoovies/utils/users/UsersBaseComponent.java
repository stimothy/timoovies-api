package com.steventimothy.timoovies.utils.users;

import com.steventimothy.timoovies.repository.schemas.DataUser;
import com.steventimothy.timoovies.schemas.users.User;
import com.steventimothy.timoovies.utils.UtilsBaseComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class UsersBaseComponent extends UtilsBaseComponent {

  /**
   * The mapper that maps users and dataUsers.
   */
  @Autowired
  protected UserMapper userMapper;
  /**
   * The validator that validates users.
   */
  @Autowired
  protected UserValidator userValidator;

  /**
   * Creates a user.
   * @return The created user.
   */
  protected User createUser() {
    //Create the user.
    User user = new User()
        .userId(createUserId())
        .username("testUser1")
        .password("hiPPos3atGr@ss");

    assertThat(user.username())
        .isEqualTo("testUser1");
    assertThat(user.password())
        .isEqualTo("hiPPos3atGr@ss");

    return user;
  }

  /**
   * Creates a dataUser.
   * @return The dataUser created.
   */
  protected DataUser createDataUser() {
    //Create the dataUser.
    DataUser dataUser = new DataUser()
        .id(createUserId().rawId())
        .username("testUser1")
        .enc_password("hiPPos3atGr@ss")
        .date_created(Instant.now())
        .last_modified(Instant.now());

    assertThat(dataUser.username())
        .isEqualTo("testUser1");
    assertThat(dataUser.enc_password())
        .isEqualTo("hiPPos3atGr@ss");
    assertThat(dataUser.date_created())
        .isNotNull();
    assertThat(dataUser.last_modified())
        .isNotNull();

    return dataUser;
  }
}
