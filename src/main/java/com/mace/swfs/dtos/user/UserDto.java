package com.mace.swfs.dtos.user;

import java.util.List;

import com.mace.swfs.persistance.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

	private String uuid;
	private String fullName;
	private String fatherName;
	private String email;
	private String phone;
	private String address;
	private List<Role> roles;

}
