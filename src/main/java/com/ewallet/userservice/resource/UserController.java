package com.ewallet.userservice.resource;

import com.ewallet.userservice.entities.PaymentUser;
import com.ewallet.userservice.manager.UserManager;
import com.ewallet.userservice.model.SignUpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserManager userManager;

    @PostMapping("/signUp")
    public void signUp(@RequestBody SignUpRequest signupRequest) throws JsonProcessingException {
        userManager.create(signupRequest);
    }

    @GetMapping("/user/{id}")
    public PaymentUser getUser(@PathVariable("id") String id){
        PaymentUser user  = userManager.getUser(id);

        return user;
    }


}
