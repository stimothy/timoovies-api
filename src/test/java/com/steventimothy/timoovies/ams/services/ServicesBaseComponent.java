package com.steventimothy.timoovies.ams.services;

import com.steventimothy.timoovies.ams.AmsBaseComponent;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ServicesBaseComponent extends AmsBaseComponent {

  /**
   * The service class for ams.
   */
  @Autowired
  protected AccountManagementService accountManagementService;

  /**
   * Adds a user to the database
   *
   * @param user The user to create in the database.
   * @return The userId of the user created, or an empty userId if it wasn't.
   */
  @Override
  protected UserId createUser(User user) {
    cleanUpCache.add(user.userId());
    return accountManagementService.createUser(user);
  }
}
