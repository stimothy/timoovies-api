package com.steventimothy.timoovies.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The BaseClient Abstract Class</h1>
 * <p>This class holds the base logic for calling other system.</p>
 */
@Slf4j
@Component
public abstract class BaseClient {

//  /**
//   * The environment of the server.
//   */
//  private Environment environment;
//  /**
//   * The rest caller.
//   */
//  protected RestTemplate restTemplate;
//
//  /**
//   * Gets the path to the ams system.
//   *
//   * @return The ams system path.
//   */
//  protected String getAmsPath() {
//    return getBaseUrl() + "/ams";
//  }
//
//  /**
//   * Gets the base url.
//   *
//   * @return The base url.
//   */
//  private String getBaseUrl() {
//    return getProtocol() + getAddress() + getPort();
//  }
//
//  private String getPort() {
//    return ":" + environment.getProperty("local.server.port");
//  }
//
//  private String getAddress() {
//    return environment.getProperty("server.address");
//  }
//
//  private String getProtocol() {
//    return "http://";
//  }
//
//
//  /**
//   * The constructor.
//   *
//   * @param restTemplate The rest caller.
//   */
//  public BaseClient(Environment environment, RestTemplate restTemplate) {
//    this.environment = environment;
//    this.restTemplate = restTemplate;
//  }
}
