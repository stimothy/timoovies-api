package com.steventimothy.timoovies.schemas.ids.sessionids;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <h1>The AdminSessionId Class</h1>
 * <p>This class holds the id for an admin Session.</p>
 */
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//@Accessors(fluent = true)
//@EqualsAndHashCode(callSuper = true)
//@Data
public class AdminSessionId extends SessionId {

//  /**
//   * Gets the encoded value of the id.
//   *
//   * @return The encoded sessionId.
//   */
//  @Override
//  public String getEncodedValue() {
//    return (super.rawId() == null) ? null : "session.admin." + super.rawId();
//  }
//
//  /**
//   * Sets the encoded value of the session.
//   *
//   * @param encodedValue The encoded value to set the rawId with.
//   * @throws IllegalArgumentException throws if the encoded value cannot be
//   *                                  converted into an admin session id.
//   */
//  @Override
//  public void setEncodedValue(String encodedValue) throws IllegalArgumentException {
//    if (encodedValue != null) {
//      String[] strs = encodedValue.split("\\.");
//
//      if (strs.length == 3 && strs[0].equals("session") && strs[1].equals("admin")) {
//        super.rawId(UUID.fromString(strs[2]));
//        return;
//      }
//    }
//
//    throw new IllegalArgumentException("The encoded value was not of the type adminSessionId. encodedValue: " + encodedValue);
//  }
//
//  /**
//   * Gets a printable friendly string of the class.
//   *
//   * @return A string representation of the object.
//   */
//  @Override
//  public String toString() {
//    return "AdminSessionId(id=" + getEncodedValue() + ")";
//  }
//
//  /**
//   * Constructor to construct an admin session id from an encoded value.
//   *
//   * @param encodedValue The encoded value to construct the id from.
//   * @throws IllegalArgumentException throws if the id is not an admin session id.
//   */
//  public AdminSessionId(String encodedValue) throws IllegalArgumentException {
//    setEncodedValue(encodedValue);
//  }
}
