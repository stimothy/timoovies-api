package com.steventimothy.timoovies.utils.users;

import com.steventimothy.timoovies.repository.schemas.DataUser;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserMapper Class</h1>
 * <p>This class is responsible for mapping a user to dataUser and vice versa.</p>
 */
@Slf4j
@Component
public class UserMapper {

  /**
   * Maps a user to a dataUser.
   *
   * @param user the user to map.
   * @return the dataUser produced.
   */
  public DataUser map(User user) {
    if (user != null) {
      return new DataUser()
          .id(user.userId().rawId())
          .username(user.username())
          .enc_password(user.password());
    }
    else {
      return null;
    }
  }

  /**
   * Maps a dataUser to a user.
   *
   * @param dataUser the dataUser to map.
   * @return The user produced.
   */
  public User map(DataUser dataUser) {
    if (dataUser != null) {
      return new User()
          .userId(new UserId()
              .rawId(dataUser.id()))
          .username(dataUser.username())
          .password(dataUser.enc_password());
    }
    else {
      return null;
    }
  }
}
