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
   * Creates a dataUser.
   * @return The dataUser created.
   */
  protected DataUser createDataUser() {
    //Create the dataUser.
    DataUser dataUser = new DataUser()
        .id(createUserId().rawId())
        .username("testUser3")
        .enc_password("ch33t@sRunFaSt")
        .date_created(Instant.now())
        .last_modified(Instant.now());

    assertThat(dataUser.username())
        .isEqualTo("testUser3");
    assertThat(dataUser.enc_password())
        .isEqualTo("ch33t@sRunFaSt");
    assertThat(dataUser.date_created())
        .isNotNull();
    assertThat(dataUser.last_modified())
        .isNotNull();

    return dataUser;
  }
}
