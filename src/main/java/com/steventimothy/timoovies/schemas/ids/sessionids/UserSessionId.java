package com.steventimothy.timoovies.schemas.ids.sessionids;

/**
 * <h1>The UserSessionId Class</h1>
 * <p>This class holds the id for a user Session.</p>
 */
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//@NoArgsConstructor
//@Accessors(fluent = true)
//@EqualsAndHashCode(callSuper = true)
//@Data
public class UserSessionId extends SessionId {

//  /**
//   * Gets the encoded value of the id.
//   *
//   * @return The encoded sessionId.
//   */
//  @Override
//  public String getEncodedValue() {
//    return (super.rawId() == null) ? null : "session.user." + super.rawId();
//  }
//
//  /**
//   * Sets the encoded value of the session.
//   *
//   * @param encodedValue The encoded value to set the rawId with.
//   * @throws IllegalArgumentException throws if the encoded value cannot be
//   *                                  converted into a user session id.
//   */
//  @Override
//  public void setEncodedValue(String encodedValue) throws IllegalArgumentException {
//    if (encodedValue != null) {
//      String[] strs = encodedValue.split("\\.");
//
//      if (strs.length == 3 && strs[0].equals("session") && strs[1].equals("user")) {
//        super.rawId(UUID.fromString(strs[2]));
//        return;
//      }
//    }
//
//    throw new IllegalArgumentException("The encoded value was not of the type userSessionId. encodedValue: " + encodedValue);
//  }
//
//  /**
//   * Gets a printable friendly string of the class.
//   *
//   * @return A string representation of the object.
//   */
//  @Override
//  public String toString() {
//    return "UserSessionId(id=" + getEncodedValue() + ")";
//  }
//
//  /**
//   * Constructor to construct a user session id from an encoded value.
//   *
//   * @param encodedValue The encoded value to construct the id from.
//   * @throws IllegalArgumentException throws if the id is not a user session id.
//   */
//  public UserSessionId(String encodedValue) throws IllegalArgumentException {
//    setEncodedValue(encodedValue);
//  }
}
