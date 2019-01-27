package com.steventimothy.timoovies.ams;

import com.steventimothy.timoovies.BaseComponent;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountManagementServiceSystemTest extends AmsBaseComponent {

//  /**
//   * Tests the functionality of ams using userIds.
//   */
//  @Test
//  public void testAmsSystemFunctionalitiesUsingIds() {
//    //Create a user.
//    User user = new User()
//        .id(3)
//        .username("bob1234")
//        .password("myPassword");
//    ResponseEntity<Integer> createResponseEntity = requestCreateUser(user);
//    assertStatus(createResponseEntity, HttpStatus.OK);
//    assertThat(createResponseEntity.getBody())
//        .isNotNull();
//    assertThat(createResponseEntity.getBody())
//        .isEqualTo(user.id());
//
//    //Get the user.
//    ResponseEntity<User> getResponseEntity = requestGetUserById(user.id());
//    assertStatus(getResponseEntity, HttpStatus.OK);
//    assertThat(getResponseEntity.getBody())
//        .isNotNull();
//    assertThat(getResponseEntity.getBody())
//        .isEqualTo(user);
//
//    //Update the user.
//    User user2 = getResponseEntity.getBody();
//    user2.username("bob4321");
//    user2.password("mySecondPassword");
//    ResponseEntity updateResponseEntity = requestUpdateUser(user2);
//    assertStatus(updateResponseEntity, HttpStatus.OK);
//
//    //Get the user.
//    getResponseEntity = requestGetUserById(user.id());
//    assertStatus(getResponseEntity, HttpStatus.OK);
//    assertThat(getResponseEntity.getBody())
//        .isNotNull();
//    assertThat(getResponseEntity.getBody())
//        .isEqualTo(user2);
//
//    //Delete the user.
//    ResponseEntity deleteResponseEntity = requestDeleteUserById(user.id());
//    assertStatus(deleteResponseEntity, HttpStatus.OK);
//
//    //Get the user.
//    getResponseEntity = requestGetUserById(user.id());
//    assertStatus(getResponseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests the functionality of ams using usernames.
//   */
//  @Test
//  public void testAmsSystemFunctionalitiesUsingUsernames() {
//    //Create a user.
//    User user = new User()
//        .id(3)
//        .username("bob1234")
//        .password("myPassword");
//    ResponseEntity<Integer> createResponseEntity = requestCreateUser(user);
//    assertStatus(createResponseEntity, HttpStatus.OK);
//    assertThat(createResponseEntity.getBody())
//        .isNotNull();
//    assertThat(createResponseEntity.getBody())
//        .isEqualTo(user.id());
//
//    //Get the user.
//    ResponseEntity<User> getResponseEntity = requestGetUserByUsername(user.username());
//    assertStatus(getResponseEntity, HttpStatus.OK);
//    assertThat(getResponseEntity.getBody())
//        .isNotNull();
//    assertThat(getResponseEntity.getBody())
//        .isEqualTo(user);
//
//    //Update the user.
//    User user2 = getResponseEntity.getBody();
//    user2.username("bob4321");
//    user2.password("mySecondPassword");
//    ResponseEntity updateResponseEntity = requestUpdateUser(user2);
//    assertStatus(updateResponseEntity, HttpStatus.OK);
//
//    //Get the user.
//    getResponseEntity = requestGetUserByUsername(user2.username());
//    assertStatus(getResponseEntity, HttpStatus.OK);
//    assertThat(getResponseEntity.getBody())
//        .isNotNull();
//    assertThat(getResponseEntity.getBody())
//        .isEqualTo(user2);
//
//    //Delete the user.
//    ResponseEntity deleteResponseEntity = requestDeleteUserByUsername(user2.username());
//    assertStatus(deleteResponseEntity, HttpStatus.OK);
//
//    //Get the user.
//    getResponseEntity = requestGetUserByUsername(user2.username());
//    assertStatus(getResponseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests the functionality of ams using userIds and userNames.
//   */
//  @Test
//  public void testAmsSystemFunctionalitiesUsingIdsAndUsernames() {
//    //Create a user.
//    User user = new User()
//        .id(3)
//        .username("bob1234")
//        .password("myPassword");
//    ResponseEntity<Integer> createResponseEntity = requestCreateUser(user);
//    assertStatus(createResponseEntity, HttpStatus.OK);
//    assertThat(createResponseEntity.getBody())
//        .isNotNull();
//    assertThat(createResponseEntity.getBody())
//        .isEqualTo(user.id());
//
//    //Get the user.
//    ResponseEntity<User> getResponseEntity = requestGetUserById(user.id());
//    assertStatus(getResponseEntity, HttpStatus.OK);
//    assertThat(getResponseEntity.getBody())
//        .isNotNull();
//    assertThat(getResponseEntity.getBody())
//        .isEqualTo(user);
//    User user2 = getResponseEntity.getBody();
//    //Get the user.
//    getResponseEntity = requestGetUserByUsername(user.username());
//    assertStatus(getResponseEntity, HttpStatus.OK);
//    assertThat(getResponseEntity.getBody())
//        .isNotNull();
//    assertThat(getResponseEntity.getBody())
//        .isEqualTo(user);
//    User user3 = getResponseEntity.getBody();
//    assertThat(user2)
//        .isEqualTo(user3);
//
//    //Update the user.
//    user2.username("bob4321");
//    user2.password("mySecondPassword");
//    ResponseEntity updateResponseEntity = requestUpdateUser(user2);
//    assertStatus(updateResponseEntity, HttpStatus.OK);
//
//    //Get the user.
//    getResponseEntity = requestGetUserById(user.id());
//    assertStatus(getResponseEntity, HttpStatus.OK);
//    assertThat(getResponseEntity.getBody())
//        .isNotNull();
//    assertThat(getResponseEntity.getBody())
//        .isEqualTo(user2);
//    User user4 = getResponseEntity.getBody();
//    //Get the user.
//    getResponseEntity = requestGetUserByUsername(user2.username());
//    assertStatus(getResponseEntity, HttpStatus.OK);
//    assertThat(getResponseEntity.getBody())
//        .isNotNull();
//    assertThat(getResponseEntity.getBody())
//        .isEqualTo(user2);
//    User user5 = getResponseEntity.getBody();
//    assertThat(user4)
//        .isEqualTo(user5);
//
//    //Delete the user.
//    ResponseEntity deleteResponseEntity = requestDeleteUserById(user.id());
//    assertStatus(deleteResponseEntity, HttpStatus.OK);
//
//    createResponseEntity = requestCreateUser(user2);
//    assertStatus(createResponseEntity, HttpStatus.OK);
//
//    deleteResponseEntity = requestDeleteUserByUsername(user2.username());
//    assertStatus(deleteResponseEntity, HttpStatus.OK);
//
//    //Get the user.
//    getResponseEntity = requestGetUserById(user2.id());
//    assertStatus(getResponseEntity, HttpStatus.BAD_REQUEST);
//    //Get the user.
//    getResponseEntity = requestGetUserByUsername(user2.username());
//    assertStatus(getResponseEntity, HttpStatus.BAD_REQUEST);
//  }
}
