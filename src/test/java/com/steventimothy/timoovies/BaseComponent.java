package com.steventimothy.timoovies;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseComponent {

  /**
   * The restTemplate used for testing endpoints.
   */
  @Autowired
  protected TestRestTemplate restTemplate;

  /**
   * Asserts that the response status on the response is equal to the httpStatus provided.
   * @param responseEntity The response of the request.
   * @param status The status expected.
   */
  protected void assertStatus(ResponseEntity responseEntity, HttpStatus status) {
    assertThat(responseEntity.getStatusCode())
        .isEqualTo(status);
  }

  /**
   * Returns the ams base path.
   * @return The ams path.
   */
  protected String getAmsPath() {
    return "/ams";
  }
}
