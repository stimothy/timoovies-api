package com.steventimothy.timoovies.controllers;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class HealthCheckControllerTest extends ControllersBaseComponent {

  /**
   * This tests the health endpoint.
   */
  @Test
  public void testHealth() {
    ResponseEntity<String> responseEntity = this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString("/health")
        .build().toUri())
        .build(), String.class);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isEqualTo("Ok");
  }
}
