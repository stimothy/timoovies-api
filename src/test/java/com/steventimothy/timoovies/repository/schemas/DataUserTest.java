package com.steventimothy.timoovies.repository.schemas;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class DataUserTest extends SchemasBaseComponent {

  /**
   * Tests the lombok.
   */
  @Test
  public void testDataUser() {
    EqualsVerifier.forClass(DataUser.class)
        .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS)
        .verify();
  }
}
