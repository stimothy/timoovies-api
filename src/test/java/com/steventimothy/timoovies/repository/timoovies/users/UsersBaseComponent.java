package com.steventimothy.timoovies.repository.timoovies.users;

import com.steventimothy.timoovies.repository.schemas.DataUser;
import com.steventimothy.timoovies.repository.timoovies.TimooviesBaseComponent;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UsersBaseComponent extends TimooviesBaseComponent {

  /**
   * The data service used to talk to the db layer.
   */
  @Autowired
  protected UsersDataService usersDataService;
  /**
   * The db layer used to talk to the database.
   */
  @Autowired
  protected UsersDbService usersDbService;

  /**
   * Creates a user in the database.
   *
   * @param user The user to create in the database.
   * @return The userId of the user if it was created, and empty id if not.
   */
  @Override
  protected UserId createUser(User user) {
    cleanUpCache.add(user.userId());
    return usersDataService.createUser(user);
  }

  /**
   * Creates a dataUser in the database.
   *
   * @param dataUser The dataUser to create in the database.
   * @return The Id of the user if it was created, null otherwise.
   */
  protected Long insert(DataUser dataUser) {
    cleanUpCache.add(new UserId().rawId(dataUser.id()));
    return usersDbService.insert(dataUser);
  }
}
