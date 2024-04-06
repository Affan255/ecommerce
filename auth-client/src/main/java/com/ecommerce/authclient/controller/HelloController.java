package com.ecommerce.authclient.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/api/client")
  public String hitClient(Principal principal){
    return "Hello " + principal.getName() + " ! The client got hit successfully!";
  }

}
