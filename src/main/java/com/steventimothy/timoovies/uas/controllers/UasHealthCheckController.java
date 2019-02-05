package com.steventimothy.timoovies.uas.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The UasHealthCheckController Class</h1>
 * <p>This class holds the endpoint used in the uas health checks.</p>
 */
@Slf4j
@RequestMapping("/uas/health")
@RestController
public class UasHealthCheckController {

//  /**
//   * The health check endpoint.
//   *
//   * @return Ok if the endpoint is reached.
//   */
//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity getUasHealth() {
//    log.info("[200] GET: /uas/health - Response: body={}", "Ok");
//    return ResponseEntity.ok("Ok");
//  }
}
