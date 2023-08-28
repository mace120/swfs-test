package com.mace.swfs.controllers;

import com.mace.swfs.commands.user.LoginCommand;
import com.mace.swfs.controllers.response.ResponseWrapper;
import com.mace.swfs.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<ResponseWrapper> saveUser(@RequestBody @Valid LoginCommand loginCommand) {
        return ResponseEntity.ok(ResponseWrapper.builder().data(userService.login(loginCommand)).build());
    }

}
