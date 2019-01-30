package com.steventimothy.timoovies.uas.controllers;

import com.steventimothy.timoovies.uas.services.UserAuthorizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>The UserAuthorizationController Class</h1>
 * <p>This class holds the endpoints used in the UAS.</p>
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ams")
@RestController
public class UserAuthorizationController {

  private UserAuthorizationService userAuthorizationService;
}
