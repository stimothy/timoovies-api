package com.steventimothy.timoovies.utils.users;

import com.steventimothy.timoovies.repository.schemas.DataUser;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest extends UsersBaseComponent {

  /**
   * Tests that a user can be mapped to a dataUser.
   */
  @Test
  public void testMapUserToDataUser() {
    User user = createUser();

    //Map the user.
    DataUser dataUser = userMapper.map(user);

    assertThat(dataUser.id())
        .isEqualTo(user.userId().rawId());
    assertThat(dataUser.username())
        .isEqualTo(user.username());
    assertThat(dataUser.enc_password())
        .isEqualTo(user.password());
    assertThat(dataUser.date_created())
        .isNull();
    assertThat(dataUser.last_modified())
        .isNull();
  }

  /**
   * Tests that a dataUser can be mapped to a user.
   */
  @Test
  public void testMapDataUserToUser() {
    DataUser dataUser = createDataUser();

    //Map the dataUser.
    User user = userMapper.map(dataUser);

    assertThat(user.userId().rawId())
        .isEqualTo(dataUser.id());
    assertThat(user.username())
        .isEqualTo(dataUser.username());
    assertThat(user.password())
        .isEqualTo(dataUser.enc_password());
  }
}
