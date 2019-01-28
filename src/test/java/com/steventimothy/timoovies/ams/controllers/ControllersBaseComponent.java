package com.steventimothy.timoovies.ams.controllers;

import com.steventimothy.timoovies.ams.AmsBaseComponent;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ControllersBaseComponent extends AmsBaseComponent {

  /**
   * Creates a user in the database and returns its id.
   *
   * @param user The user to create in the database.
   * @return The id of the user created.
   */
  protected UserId createUser(User user) {
    ResponseEntity<UserId> responseEntity = requestCreateUser(user);

    assertStatus(responseEntity, HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isNotNull();
    assertThat(responseEntity.getBody().getEncodedValue())
        .isNotNull();

    return responseEntity.getBody();
  }

  /**
   * Creates a user in the database.
   *
   * @param user The user to create in the database.
   * @return The response from the rest call to create a user.
   */
  protected ResponseEntity<UserId> requestCreateUser(User user) {
    return this.restTemplate.exchange(RequestEntity.post(UriComponentsBuilder.fromUriString(getAmsPath())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(user), UserId.class);
  }

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
   * Deletes a user by id.
   *
   * @param userId The id of the user to delete.
   */
  protected void deleteUserById(UserId userId) {
    ResponseEntity responseEntity = requestDeleteUserById(userId);
    assertStatus(responseEntity, HttpStatus.OK);
  }

  /**
   * Deletes a user by id.
   *
   * @param userId The id of the user to delete.
   * @return The response of the rest call to delete a user by id.
   */
  protected ResponseEntity requestDeleteUserById(UserId userId) {
    return this.restTemplate.exchange(RequestEntity.delete(UriComponentsBuilder.fromUriString(getAmsPath() + "/id/" + userId.getEncodedValue())
        .build().toUri())
        .accept(MediaType.APPLICATION_JSON)
        .build(), String.class);
  }
}
