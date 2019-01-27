package com.steventimothy.timoovies.schemas.ids;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class SessionIdTest extends IdsBaseComponent {

  /**
   * Tests that the encoded id value is correct.
   */
  @Test
  public void testGetEncodedValue() {
    SessionId sessionId = createSessionId();

    assertThat(sessionId.getEncodedValue())
        .isEqualTo("session." + sessionId.rawId());
  }

  /**
   * Tests that a sessionId can be set using an encodedValue.
   */
  @Test
  public void testSetEncodedValue() {
    SessionId sessionId = createSessionId();

    //Set the encodedValue.
    UUID uuid2 = UUID.randomUUID();
    sessionId.setEncodedValue("session." + uuid2);

    assertThat(sessionId.getEncodedValue())
        .isEqualTo("session." + uuid2);
    assertThat(sessionId.rawId())
        .isEqualTo(uuid2);
  }

  /**
   * Tests that a sessionId cannot be set using a userId encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_WrongType() {
    SessionId sessionId = createSessionId();

    //Change the encoded value.
    sessionId.setEncodedValue("user." + UUID.randomUUID());

    fail();
  }

  /**
   * Tests that a sessionId cannot be set using a bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_BadType() {
    SessionId sessionId = createSessionId();

    //Change the encoded value.
    sessionId.setEncodedValue("session.");

    fail();
  }

  /**
   * Tests that a sessionId cannot be set using a bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_BadType_DoubleDot() {
    SessionId sessionId = createSessionId();

    //Change the encoded value.
    sessionId.setEncodedValue("session.." + UUID.randomUUID());

    fail();
  }

  /**
   * Tests that a sessionId cannot be set using a bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_BadType_NoIdentifier() {
    SessionId sessionId = createSessionId();

    //Change the encoded value.
    sessionId.setEncodedValue("." + UUID.randomUUID());

    fail();
  }

  /**
   * Tests that a sessionId cannot be set using a bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_BadType_NoDot() {
    SessionId sessionId = createSessionId();

    //Change the encoded value.
    sessionId.setEncodedValue("session" + UUID.randomUUID());

    fail();
  }

  /**
   * Tests that a sessionId cannot be set using a null encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetEncodedValue_Null() {
    SessionId sessionId = createSessionId();

    //Change the encoded value.
    sessionId.setEncodedValue(null);

    fail();
  }

  /**
   * Tests that the toString method returns correctly.
   */
  @Test
  public void testToString() {
    SessionId sessionId = createSessionId();

    assertThat(sessionId.toString())
        .isEqualTo("SessionId(id=" + sessionId.getEncodedValue() + ")");
  }

  /**
   * Tests that the encoded value constructor can set the fields correctly.
   */
  @Test
  public void testConstructorEncodedValue() {
    UUID uuid = UUID.randomUUID();
    SessionId sessionId = new SessionId("session." + uuid);

    assertThat(sessionId.getEncodedValue())
        .isEqualTo("session." + uuid);
    assertThat(sessionId.rawId())
        .isEqualTo(uuid);
  }

  /**
   * Tests that the encoded value constructor throws exception given bad encodedValue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorEncodedValue_BadEncodedValue() {
    new SessionId("user." + UUID.randomUUID());

    fail();
  }

  /**
   * Tests the lombok.
   */
  @Test
  public void testSessionId() {
    EqualsVerifier.forClass(SessionId.class)
        .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS)
        .verify();
  }
}
