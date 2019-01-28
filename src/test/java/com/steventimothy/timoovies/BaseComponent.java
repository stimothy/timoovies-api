package com.steventimothy.timoovies;

import com.steventimothy.timoovies.schemas.users.User;
import com.steventimothy.timoovies.schemas.ids.UserId;
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
   * Creates a userId.
   * @return The userId created.
   */
  protected UserId createUserId() {
    return createUserId(3L);
  }

  /**
   * Creates a userId.
   * @param id The id to create.
   * @return The userId created.
   */
  protected UserId createUserId(Long id) {

    //Create a userId.
    UserId userId = new UserId()
        .rawId(id);

    assertThat(userId.rawId())
        .isEqualTo(id);

    return userId;
  }

  /**
   * Creates a userId.
   * @return The userId created.
   */
  protected UserId createAltUserId() {
    return createUserId(4L);
  }

  /**
   * Creates a local user.
   * @return The local created user.
   */
  protected User createLocalUser() {
    return createLocalUser(createUserId(), "testUser3", "ch33t@sRunFaSt");
  }

  /**
   * Creates a local user.
   * @param userId The userId of the user.
   * @param username The username of the user.
   * @param password The password of the user.
   * @return the local created user.
   */
  protected User createLocalUser(UserId userId, String username, String password) {

    //Create the user.
    User user = new User()
        .userId(userId)
        .username(username)
        .password(password);

    assertThat(user.userId())
        .isEqualTo(userId);
    assertThat(user.username())
        .isEqualTo(username);
    assertThat(user.password())
        .isEqualTo(password);

    return user;
  }

  /**
   * Create an alternate local user.
   * @return The created alternate local user.
   */
  protected User createAltLocalUser() {
    return createLocalUser(createAltUserId(), "testUser4", "anTsW@lk!na1ine");
  }

//  /**
//   * The test user 1 id.
//   */
//  private Integer userId;
//  /**
//   * The test user 2 id.
//   */
//  private Integer altUserId;
//
//  /**
//   * Clean up the state before starting the test.
//   */
//  @Before
//  public void setup() {
//    //cleanUp();
//  }
//
//  /**
//   * Clean up the state before finishing the test.
//   */
//  @After
//  public void tearDown() {
//    //cleanUp();
//  }

//  /**
//   * Gets the existing id of test user 1, or creates a user and returns that id.
//   *
//   * @return the id of the test user 1.
//   */
//  protected Integer getOrCreateUserId() {
//    if (userId != null) {
//      return userId;
//    }
//    else {
//      return createUser(new User()
//          .userId(new UserId().rawId(1L))
//          .username("testUser1")
//          .password("hiPPos3atGr@ss"));
//    }
//  }
//
//  /**
//   * Gets the existing id of test user 2, or creates a user and returns that id.
//   *
//   * @return the id of the test user 2.
//   */
//  protected Integer getOrCreateAltUserId() {
//    if (altUserId != null) {
//      return altUserId;
//    }
//    else {
//      return createUser(new User()
//          .userId(new UserId().rawId(2L))
//          .username("testUser2")
//          .password("w0rmSEatD!rt"));
//    }
//  }




//
//  /**
//   * Gets the user based on a username.
//   *
//   * @param username The username of the user to get.
//   * @return The user retrieved from the database.
//   */
//  protected User getUser(String username) {
//    ResponseEntity<User> responseEntity = requestGetUserByUsername(username);
//    assertStatus(responseEntity, HttpStatus.OK);
//
//    return responseEntity.getBody();
//  }
//

//
//  /**
//   * Gets the user based on a username.
//   *
//   * @param username The username of the user to get.
//   * @return The response of the rest call to get a user by username.
//   */
//  protected ResponseEntity<User> requestGetUserByUsername(String username) {
//    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/username/" + username)
//        .build().toUri())
//        .accept(MediaType.APPLICATION_JSON)
//        .build(), User.class);
//  }
//
//  /**
//   * Updates the user in the database.
//   *
//   * @param user The updated user.
//   */
//  protected void updateUser(User user) {
//    ResponseEntity responseEntity = requestUpdateUser(user);
//    assertStatus(responseEntity, HttpStatus.OK);
//  }
//
//  /**
//   * Updates the user in the database.
//   *
//   * @param user The updated user.
//   * @return The response of the rest call.
//   */
//  protected ResponseEntity requestUpdateUser(User user) {
//    return this.restTemplate.exchange(RequestEntity.put(UriComponentsBuilder.fromUriString(getAmsPath())
//        .build().toUri())
//        .accept(MediaType.APPLICATION_JSON)
//        .contentType(MediaType.APPLICATION_JSON)
//        .body(user), String.class);
//  }
//
//  /**
//   * Deletes a user by id.
//   *
//   * @param id The id of the user to delete.
//   */
//  protected void deleteUser(Integer id) {
//    ResponseEntity responseEntity = requestDeleteUserById(id);
//    assertStatus(responseEntity, HttpStatus.OK);
//  }
//
//  /**
//   * Deletes a user by username.
//   *
//   * @param username The username of the user to delete.
//   */
//  protected void deleteUser(String username) {
//    ResponseEntity responseEntity = requestDeleteUserByUsername(username);
//    assertStatus(responseEntity, HttpStatus.OK);
//  }
//
//  /**
//   * Deletes a user by id.
//   *
//   * @param id The id of the user to delete.
//   * @return The response of the rest call to delete a user by id.
//   */
//  protected ResponseEntity requestDeleteUserById(Integer id) {
//    return this.restTemplate.exchange(RequestEntity.delete(UriComponentsBuilder.fromUriString(getAmsPath() + "/id/" + id)
//        .build().toUri())
//        .accept(MediaType.APPLICATION_JSON)
//        .build(), String.class);
//  }
//
//  /**
//   * Deletes a user by username.
//   *
//   * @param username The username of the user to delete.
//   * @return The response of the rest call to delete a user by username.
//   */
//  protected ResponseEntity requestDeleteUserByUsername(String username) {
//    return this.restTemplate.exchange(RequestEntity.delete(UriComponentsBuilder.fromUriString(getAmsPath() + "/username/" + username)
//        .build().toUri())
//        .accept(MediaType.APPLICATION_JSON)
//        .build(), String.class);
//  }
//
//  /**
//   * Cleans up the users in the database and sets their ids to null.
//   */
//  private void cleanUp() {
//    userId = null;
//    altUserId = null;
//    try {
//      requestDeleteUserById(1);
//      requestDeleteUserById(2);
//    }
//    catch (Exception ex) {
//    }
//  }

  /**
   * Asserts that the response status on the response is equal to the httpStatus provided.
   *
   * @param responseEntity The response of the request.
   * @param status         The status expected.
   */
  protected void assertStatus(ResponseEntity responseEntity, HttpStatus status) {
    assertThat(responseEntity.getStatusCode())
        .isEqualTo(status);
  }

  /**
   * Returns the ams base path.
   *
   * @return The ams path.
   */
  protected String getAmsPath() {
    return "/ams";
  }
}
