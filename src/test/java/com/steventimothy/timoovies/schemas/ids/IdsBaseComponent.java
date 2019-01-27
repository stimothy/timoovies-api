package com.steventimothy.timoovies.schemas.ids;

import com.steventimothy.timoovies.schemas.SchemasBaseComponent;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class IdsBaseComponent extends SchemasBaseComponent {

  /**
   * Creates a universally unique sessionId.
   * @return The sessionId created.
   */
  protected SessionId createSessionId() {
    //Create a sessionId.
    UUID uuid = UUID.randomUUID();
    SessionId sessionId = new SessionId()
        .rawId(uuid);

    assertThat(sessionId.rawId())
        .isEqualTo(uuid);

    return sessionId;
  }
}
