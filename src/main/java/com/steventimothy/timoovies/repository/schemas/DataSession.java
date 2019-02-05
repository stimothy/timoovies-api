package com.steventimothy.timoovies.repository.schemas;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <h1>The DataSession Class</h1>
 * <p>This class is the data object for the sessions table.</p>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class DataSession {

//  /**
//   * The sessionId.
//   */
//  private String id;
//  /**
//   * The userId.
//   */
//  private Long user_id;
//  /**
//   * The time the session expires.
//   */
//  private Instant expiration;
//  /**
//   * The time the session was created.
//   */
//  private Instant date_created;
//  /**
//   * The time the session was last modified.
//   */
//  private Instant last_modified;
}
