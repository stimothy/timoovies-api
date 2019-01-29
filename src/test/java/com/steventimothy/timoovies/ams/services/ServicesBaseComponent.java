package com.steventimothy.timoovies.ams.services;

import com.steventimothy.timoovies.ams.AmsBaseComponent;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ServicesBaseComponent extends AmsBaseComponent {

  @Autowired
  protected AccountManagementService accountManagementService;

  @Override
  protected UserId createUser(User user) {
    cleanUpCache.add(user.userId());
    return accountManagementService.createUser(user);
  }
}
