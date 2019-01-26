package com.steventimothy.timoovies;

import com.steventimothy.timoovies.schema.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

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
   * Creates a user in the database.
   * @param id The id of the user.
   * @param username The username of the user.
   * @param password The password of the user.
   * @return The response of the post to the create user endpoint.
   */
  protected ResponseEntity<Integer> createUser(Integer id, String username, String password) {
    return this.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getAmsPath())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new User().id(id).username(username).password(password)), Integer.class);
  }

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
