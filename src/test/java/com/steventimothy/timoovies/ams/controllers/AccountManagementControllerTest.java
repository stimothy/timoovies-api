package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.BaseComponent;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementControllerTest extends BaseComponent {

  /**
   * This tests that a user can be created.
   */
  @Test
  public void testCreateUser() {
    ResponseEntity<Integer> responseEntity = createUser(1, "bob1234", "myPassword");

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isEqualTo(1);
  }
}
