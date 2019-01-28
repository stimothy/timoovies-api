package com.steventimothy.timoovies.schemas.ids;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <h1>The UserId Class</h1>
 * <p>This class holds id for a user.</p>
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@Accessors(fluent = true)
@Data
public class UserId implements Id {

  /**
   * The raw id of the user.
   */
  private Long rawId;

  /**
   * Gets the encoded id of a user.
   *
   * @return The encoded userId.
   */
  @Override
  public String getEncodedValue() {
    return (rawId == null) ? null : "user." + rawId;
  }

  /**
   * Sets the rawId of the class given an encoded value.
   *
   * @param encodedValue The encoded value to set the rawId with.
   * @throws IllegalArgumentException throws if the encodedValue can not be changed into instance of this class.
   */
  @Override
  public void setEncodedValue(String encodedValue) throws IllegalArgumentException {
    if (encodedValue != null) {
      String[] strs = encodedValue.split("\\.");

      if (strs.length == 2 && strs[0].equals("user")) {
        rawId = Long.parseLong(strs[1]);
        return;
      }
    }

    throw new IllegalArgumentException("The encoded value was not of the type userId. encodedValue: " + encodedValue);
  }

  /**
   * Gets a friendly printable string representing the class.
   *
   * @return A printer friendly string of the class.
   */
  @Override
  public String toString() {
    return "UserId(id=" + getEncodedValue() + ")";
  }

  /**
   * Creates an instance of this class using the encodedValue.
   *
   * @param encodedValue The encoded value of the object.
   * @throws IllegalArgumentException Throws if the encoded value cannot be converted into instance of this class.
   */
  public UserId(String encodedValue) throws IllegalArgumentException {
    setEncodedValue(encodedValue);
  }
}
