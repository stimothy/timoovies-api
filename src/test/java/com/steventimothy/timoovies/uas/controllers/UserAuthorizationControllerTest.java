package com.steventimothy.timoovies.uas.controllers;

import com.steventimothy.timoovies.schemas.ids.SessionId;
import com.steventimothy.timoovies.schemas.ids.UserId;
import com.steventimothy.timoovies.schemas.users.User;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAuthorizationControllerTest extends ControllersBaseComponent {

//  /**
//   * Tests that a user can be logged into the system.
//   */
//  @Test
//  public void testLoginUser() {
//    User user = createLocalUser();
//    UserId userId = createUser(user);
//
//    SessionId sessionId = login(user);
//
//    assertThat(sessionId.getEncodedValue())
//        .isNotNull();
//  }
//
//  /**
//   * Tests that bad request is returned when user cannot log in.
//   */
//  @Test
//  public void testLoginUser_CannotLogIn() {
//    User user = createLocalUser();
//    UserId userId = createUser(user);
//
//    user.password(null);
//
//    ResponseEntity<SessionId> responseEntity = requestLogin(user);
//
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
}
