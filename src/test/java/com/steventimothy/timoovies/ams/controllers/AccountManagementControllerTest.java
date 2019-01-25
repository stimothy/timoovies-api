package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.BaseComponent;
import com.steventimothy.timoovies.schema.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementControllerTest extends BaseComponent {

  /**
   * This tests the ams health endpoint.
   */
  @Test
  public void testCreateUser() {
    ResponseEntity<Integer> responseEntity = this.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getAmsPath())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new User().id(1).username("bob1234").password("myPassword")), Integer.class);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isEqualTo(1);
  }
}
