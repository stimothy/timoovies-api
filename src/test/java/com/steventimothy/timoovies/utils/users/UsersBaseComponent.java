package com.steventimothy.timoovies.utils.users;

import com.steventimothy.timoovies.utils.UtilsBaseComponent;
import org.springframework.beans.factory.annotation.Autowired;

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
}
