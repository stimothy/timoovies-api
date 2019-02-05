package com.steventimothy.timoovies.schemas.users;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <h1>The User Class</h1>
 * <p>This class holds the data members associated with a user.</p>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class User {

//  /**
//   * The id of the user.
//   */
//  private UserId userId;
//  /**
//   * The username of the user.
//   */
//  private String username;
//  /**
//   * The encrypted password of the user.
//   */
//  private String password;
}
