package com.steventimothy.timoovies.controllers;

import com.steventimothy.timoovies.BaseComponent;
import org.junit.Test;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class HealthCheckControllerTest extends BaseComponent {

  /**
   * This tests the health endpoint.
   */
  @Test
  public void testUPSHealth() {
    String string = this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString("/health")
        .build().toUri())
        .build(), String.class)
        .getBody();

    assertThat(string)
        .isEqualTo("Ok");
  }
}
