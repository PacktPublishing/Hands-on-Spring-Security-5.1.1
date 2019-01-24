package com.tomekl007.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
public class ReactiveController {

  @Autowired
  private UserService userService;

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value = "/all-users", produces = "application/json")
  public Mono<Collection<String>> getAllUsers() {
    return Mono.just(userService.getAllUsers());
  }

  @GetMapping(value = "/user/{userId}", produces = "application/json")
  public Mono<String> getSpecificUser(@PathVariable("userId") Integer userId,
                                      HttpServletRequest httpServletRequest) {
    if (httpServletRequest.isUserInRole("ROLE_USER")) {
      return Mono.justOrEmpty(userService.getById(userId));
    } else {
      return Mono.error(new Exception("user unauthorized"));
    }
  }
}
