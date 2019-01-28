package com.steventimothy.timoovies.schemas.ids;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * <h1>The SessionId Class</h1>
 * <p>This class holds the id for a Session.</p>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class SessionId implements Id {

  /**
   * The raw id of a session.
   */
  private UUID rawId;

  /**
   * Gets the encoded id value.
   * @return The encoded value of the raw id.
   */
  @Override
  public String getEncodedValue() {
    return (rawId == null) ? null : "session." + rawId;
  }

  /**
   * Sets the rawId of the class given an encoded value.
   * @param encodedValue The encoded value to set the rawId with.
   * @throws IllegalArgumentException throws if the encodedValue can not be changed into instance of this class.
   */
  @Override
  public void setEncodedValue(String encodedValue) throws IllegalArgumentException {
    if (encodedValue != null) {
      String[] strs = encodedValue.split("\\.");

      if (strs.length == 2 && strs[0].equals("session")) {
        rawId = UUID.fromString(strs[1]);
        return;
      }
    }

    throw new IllegalArgumentException("The encoded value was not of the type sessionId. encodedValue: " + encodedValue);
  }

  /**
   * Gets a printable friendly string of the class.
   * @return A string representation of the object.
   */
  @Override
  public String toString() {
    return "SessionId(id=" + getEncodedValue() + ")";
  }

  /**
   * Creates a sessionId by encodedValue.
   * @param encodedValue The encoded value of the id.
   * @throws IllegalArgumentException Throws if the encoded value cannot be converted into instance of this class.
   */
  public SessionId(String encodedValue) throws IllegalArgumentException {
    setEncodedValue(encodedValue);
  }
}
