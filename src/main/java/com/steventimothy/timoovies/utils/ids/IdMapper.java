package com.steventimothy.timoovies.utils.ids;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The UserMapper Class</h1>
 * <p>This class is responsible for mapping a user to dataUser and vice versa.</p>
 */
@Slf4j
@Component
public class IdMapper {

//  public Id mapEncodedValueToId(String encodedValue) throws IllegalArgumentException {
//    if (encodedValue != null) {
//      String[] strs = encodedValue.split("\\.");
//
//      if (strs.length == 3 && strs[0].equals("session")) {
//        return mapDecodedValueToId(strs[1], UUID.fromString(strs[2]));
//      }
//      else if (strs.length == 2 && strs[0].equals("user")) {
//        return new UserId(Long.parseLong(strs[1]));
//      }
//    }
//
//    throw new IllegalArgumentException();
//  }
//
//  public String mapEncodedValueToRawString(String encodedValue) throws IllegalArgumentException {
//    if (encodedValue != null) {
//      String[] strs = encodedValue.split("\\.");
//
//      if (strs.length == 3 && strs[0].equals("session")) {
//        return strs[2];
//      }
//      else if (strs.length == 2 && strs[0].equals("user")) {
//        return strs[1];
//      }
//    }
//
//    throw new IllegalArgumentException();
//  }
//
//  private SessionId mapDecodedValueToId(String type, UUID rawId) throws IllegalArgumentException {
//    switch (type) {
////      case "admin":
////        return new AdminSessionId().rawId(rawId);
////      case "user":
////        return new UserSessionId().rawId(rawId);
//      case "general":
//        return new GeneralSessionId(rawId);
//      default:
//        throw new IllegalArgumentException();
//    }
//  }
}
