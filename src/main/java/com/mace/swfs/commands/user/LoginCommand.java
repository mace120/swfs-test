package com.mace.swfs.commands.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Setter
public class LoginCommand {
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @NotEmpty(message = "Password can not be empty")
    private String password;
}
