package com.tomekl007.authprovider;

import org.springframework.stereotype.Component;

@Component
public class ThirdPartyAuthProviderClient {

  public boolean shouldAuthenticate(String username, Object password){
    //on production it can be external call to 3rd party system
    return username.length() == 4;
  }
}
