package com.steventimothy.timoovies.repository.schema;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * <h1>The DataUser Class</h1>
 * <p>This class is the data object for the users table.</p>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class DataUser {

  /**
   * The id of the user.
   */
  private Integer id;
  /**
   * The username of the user.
   */
  private String username;
  /**
   * The encrypted password of the user.
   */
  private String enc_password;
  /**
   * The date the user was created.
   */
  private Instant date_created;
  /**
   * The date the last time this user was modified.
   */
  private Instant last_modified;
}
