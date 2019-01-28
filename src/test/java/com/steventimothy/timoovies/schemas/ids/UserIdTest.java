package com.steventimothy.timoovies.schemas.ids;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class UserIdTest extends IdsBaseComponent {

  /**
   * Tests that the encoded id value is correct.
   */
  @Test
  public void testGetEncodedValue() {
    UserId userId = createUserId();

    assertThat(userId.getEncodedValue())
        .isEqualTo("user." + userId.rawId());
  }

  /**
   * Tests that a userId can be set using an encodedValue.
   */
  @Test
  public void testSetEncodedValue() {
    UserId userId = createUserId();

    //Set the encodedValue.
    Long id2 = 5L;
    userId.setEncodedValue("user." + id2);

    assertThat(userId.getEncodedValue())
        .isEqualTo("user." + id2);
    assertThat(userId.rawId())
        .isEqualTo(id2);
  }

  /**
   * Tests that a userId cannot be set using a sessionId encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_WrongType() {
    UserId userId = createUserId();

    //Change the encoded value.
    userId.setEncodedValue("session." + 5L);

    fail();
  }

  /**
   * Tests that a userId cannot be set using a bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_BadType() {
    UserId userId = createUserId();

    //Change the encoded value.
    userId.setEncodedValue("user.");

    fail();
  }

  /**
   * Tests that a userId cannot be set using a bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_BadType_DoubleDot() {
    UserId userId = createUserId();

    //Change the encoded value.
    userId.setEncodedValue("user.." + 5L);

    fail();
  }

  /**
   * Tests that a userId cannot be set using a bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_BadType_NoIdentifier() {
    UserId userId = createUserId();

    //Change the encoded value.
    userId.setEncodedValue("." + 5L);

    fail();
  }

  /**
   * Tests that a userId cannot be set using a bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_BadType_NoDot() {
    UserId userId = createUserId();

    //Change the encoded value.
    userId.setEncodedValue("user" + 5L);

    fail();
  }

  /**
   * Tests that a userId cannot be set using a null encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_Null() {
    UserId userId = createUserId();

    //Change the encoded value.
    userId.setEncodedValue(null);

    fail();
  }

  /**
   * Tests that the toString method returns correctly.
   */
  @Test
  public void testToString() {
    UserId userId = createUserId();

    assertThat(userId.toString())
        .isEqualTo("UserId(id=" + userId.getEncodedValue() + ")");
  }

  /**
   * Tests that the encoded value constructor can set the fields correctly.
   */
  @Test
  public void testConstructorEncodedValue() {
    Long id = 5L;
    UserId userId = new UserId("user." + id);

    assertThat(userId.getEncodedValue())
        .isEqualTo("user." + id);
    assertThat(userId.rawId())
        .isEqualTo(id);
  }

  /**
   * Tests that the encoded value constructor throws exception given bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorEncodedValue_BadEncodedValue() {
    new UserId("session." + 5L);

    fail();
  }

  /**
   * Tests the lombok.
   */
  @Test
  public void testUserId() {
    EqualsVerifier.forClass(UserId.class)
        .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS)
        .verify();
  }
}
