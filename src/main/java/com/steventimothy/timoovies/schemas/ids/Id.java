package com.steventimothy.timoovies.schemas.ids;

/**
 * <h1>The Id Interface</h1>
 * <p>Holds the functionality of an id.</p>
 */
public interface Id {

  /**
   * Gets the encoded string of the id.
   *
   * @return The encoded string of the raw id.
   */
  default String getEncodedValue() {
    throw new UnsupportedOperationException("getEncodedValue has not been implemented for this class.");
  }

  /**
   * Sets the rawId of the object by the encodedValue.
   *
   * @param encodedValue The encoded value to set the rawId with.
   * @throws IllegalArgumentException throws if the encodedValue could not be converted into instance of the class.
   */
  default void setEncodedValue(String encodedValue) throws IllegalArgumentException {
    throw new UnsupportedOperationException("setEncodedValue has not been implemented for this class.");
  }
}
