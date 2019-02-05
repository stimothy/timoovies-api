package com.steventimothy.timoovies.schemas.ids.sessionids;

import lombok.EqualsAndHashCode;

/**
 * <h1>The GeneralSessionId Class</h1>
 * <p>This class holds the id for a general Session.</p>
 */
@EqualsAndHashCode(callSuper = false)
public class GeneralSessionId extends SessionId {

//  /**
//   * Gets the encoded value of the id.
//   *
//   * @return The encoded sessionId.
//   */
//  @Override
//  public String getEncodedValue() {
//    return (super.rawId == null) ? null : "session.general." + super.rawId;
//  }
//
//  /**
//   * Gets a printable friendly string of the class.
//   *
//   * @return A string representation of the object.
//   */
//  @Override
//  public String toString() {
//    return "GeneralSessionId(id=" + getEncodedValue() + ")";
//  }
//
//
//  /**
//   * The constructor for setting the raw id.
//   * @param rawId The raw id of the session.
//   */
//  public GeneralSessionId(UUID rawId) {
//    super(rawId);
//  }
}
