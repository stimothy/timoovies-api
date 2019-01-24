package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.BaseComponent;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class AmsHealthCheckControllerTest extends BaseComponent {

  /**
   * This tests the ams health endpoint.
   */
  @Test
  public void testAmsHealth() {
    ResponseEntity<String> responseEntity = this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/health")
        .build().toUri())
        .build(), String.class);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isEqualTo("Ok");
  }
}
