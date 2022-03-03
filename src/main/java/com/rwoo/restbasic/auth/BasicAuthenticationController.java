package com.rwoo.restbasic.auth;

import org.springframework.web.bind.annotation.*;

//Controller
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class BasicAuthenticationController {
    //GET
    @GetMapping(path = "/basicauth")
    public AuthenticationBean authenticationBeanBean() {
        return new AuthenticationBean("Your are authenticated");
    }

}