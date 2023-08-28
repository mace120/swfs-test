package com.mace.swfs.commands.user;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserUpdateCommand {

	@NotEmpty(message = "Full name can not be empty")
	private String fullName;
	private String fatherName;
	private String phone;
	@NotEmpty(message = "Email can not be empty")
	private String email;
	private String address;
	private String password;

}
