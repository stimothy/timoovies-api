package com.steventimothy.timoovies.ams.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The AmsHealthCheckController Class</h1>
 * <p>This class holds the endpoint used in the ams health checks.</p>
 */
@Slf4j
@RequestMapping("/ams/health")
@RestController
public class AmsHealthCheckController {

  /**
   * The health check endpoint.
   * @return Ok if the endpoint is reached.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getAmsHealth() {
    return ResponseEntity.ok("Ok");
  }
}
