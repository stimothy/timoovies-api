package com.steventimothy.timoovies.ams;

import com.steventimothy.timoovies.BaseComponent;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AmsBaseComponent extends BaseComponent {

  /**
   * Gets the user based on an id. The user will not contain a password.
   *
   * @param userId The id of the user to get.
   * @return The user retrieved from the database.
   */
  protected User getUserById(UserId userId) {
    ResponseEntity<User> responseEntity = requestGetUserById(userId);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody().userId())
        .isEqualTo(userId);
    assertThat(responseEntity.getBody().username())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Gets the user based on an id. It will not contain the password stored in the database.
   *
   * @param userId The id of the user to get.
   * @return The response of the rest call to get a user by id.
   */
  protected ResponseEntity<User> requestGetUserById(UserId userId) {
    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/user/" + userId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), User.class);
  }

  /**
   * Gets the username based on an id.
   *
   * @param userId The id of the username to get.
   * @return The username retrieved from the database.
   */
  protected String getUsername(UserId userId) {
    ResponseEntity<String> responseEntity = requestGetUsername(userId);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Gets the userId based on a username.
   *
   * @param username The username of the userId to get.
   * @return The userId retrieved from the database.
   */
  protected UserId getUserId(String username) {
    ResponseEntity<UserId> responseEntity = requestGetUserId(username);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody().getEncodedValue())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Gets the userId based on a username.
   *
   * @param username The username of the userId to get.
   * @return The response of the rest call to get a userId by username.
   */
  protected ResponseEntity<UserId> requestGetUserId(String username) {
    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/id/" + username)
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), UserId.class);
  }

  /**
   * Gets the username based on an id.
   *
   * @param userId The id of the username to get.
   * @return The response of the rest call to get a username by id.
   */
  protected ResponseEntity<String> requestGetUsername(UserId userId) {
    return this.restTemplate.exchange(RequestEntity.get(UriComponentsBuilder.fromUriString(getAmsPath() + "/username/" + userId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), String.class);
  }

  /**
   * Updates a user in the database.
   *
   * @param user The updated user.
   */
  protected void updateUser(User user) {
    ResponseEntity responseEntity = requestUpdateUser(user);
    assertStatus(responseEntity, HttpStatus.OK);
  }

  /**
   * Updates a user in the database.
   *
   * @param user The updated user.
   * @return The response of the rest call to update a user.
   */
  protected ResponseEntity requestUpdateUser(User user) {
    return this.restTemplate.exchange(RequestEntity.put(UriComponentsBuilder.fromUriString(getAmsPath())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(user), String.class);
  }

  /**
   * Deletes a user by username.
   *
   * @param username The username of the user to delete.
   */
  protected void deleteByUsername(String username) {
    ResponseEntity responseEntity = requestDeleteByUsername(username);
    assertStatus(responseEntity, HttpStatus.OK);
  }

  /**
   * Deletes a user by username.
   *
   * @param username The username of the user to delete.
   * @return The response of the rest call to delete a user by username.
   */
  protected ResponseEntity requestDeleteByUsername(String username) {
    return this.restTemplate.exchange(RequestEntity.delete(UriComponentsBuilder.fromUriString(getAmsPath() + "/username/" + username)
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), String.class);
  }
}
