package com.steventimothy.timoovies.utils.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserMapper Class</h1>
 * <p>This class is responsible for mapping a user to dataUser and vice versa.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class UserMapper {

//  private IdMapper idMapper;
//
//  /**
//   * Maps a user to a dataUser.
//   *
//   * @param user the user to map.
//   * @return the dataUser produced.
//   */
//  public DataUser map(User user) {
//    if (user != null) {
//      return new DataUser()
//          .id(Long.parseLong(idMapper.mapEncodedValueToRawString(user.userId().getEncodedValue())))
//          .username(user.username())
//          .enc_password(user.password());
//    }
//    else {
//      return null;
//    }
//  }
//
//  /**
//   * Maps a dataUser to a user.
//   *
//   * @param dataUser the dataUser to map.
//   * @return The user produced.
//   */
//  public User map(DataUser dataUser) {
//    if (dataUser != null) {
//      return new User()
//          .userId(new UserId(dataUser.id()))
//          .username(dataUser.username())
//          .password(dataUser.enc_password());
//    }
//    else {
//      return null;
//    }
//  }
}
