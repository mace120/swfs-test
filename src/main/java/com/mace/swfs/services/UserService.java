package com.mace.swfs.services;

import com.mace.swfs.commands.user.LoginCommand;
import com.mace.swfs.commands.user.UserCreateCommand;
import com.mace.swfs.commands.user.UserUpdateCommand;
import com.mace.swfs.configs.security.JwtTokenProvider;
import com.mace.swfs.dtos.user.UserDto;
import com.mace.swfs.enums.RoleEnum;
import com.mace.swfs.exceptions.NotFoundException;
import com.mace.swfs.exceptions.UnauthorizedException;
import com.mace.swfs.persistance.entities.User;
import com.mace.swfs.persistance.repositories.UserRepository;
import com.mace.swfs.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder bCryptPasswordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userList = new ArrayList<>();
        users.forEach(user -> userList.add(UserDto.builder().uuid(user.getUuid()).fullName(user.getFullName())
                .fatherName(user.getFatherName()).email(user.getEmail()).phone(user.getPhone())
                .address(user.getAddress()).roles(user.getRoles()).build()));
        return userList;
    }

    public UserDto getUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + uuid));
        return UserDto.builder().uuid(user.getUuid()).fullName(user.getFullName()).fatherName(user.getFatherName())
                .email(user.getEmail()).phone(user.getPhone()).address(user.getAddress()).roles(user.getRoles()).build();
    }

    public Long saveUser(UserCreateCommand user) {
        User userObject = new User();
        userObject.setFullName(user.getFullName());
        userObject.setFatherName(user.getFatherName());
        userObject.setPhone(user.getPhone());
        userObject.setEmail(user.getEmail());
        userObject.setAddress(user.getAddress());
        userObject.setRoles(Arrays.asList(roleService.getRoleByAuthority(RoleEnum.ROLE_CUSTOMER)));

        return userRepository.save(userObject).getId();
    }

    public Long updateById(Long id, UserUpdateCommand user) {
        User userObject = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        userObject.setFullName(user.getFullName());
        userObject.setFatherName(user.getFatherName());
        userObject.setPhone(user.getPhone());
        userObject.setEmail(user.getEmail());
        userObject.setAddress(user.getAddress());
        return userRepository.save(userObject).getId();
    }

    public Long deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return id;
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

    public Map<String, String> login(LoginCommand loginCommand) {
        Optional<User> user = userRepository.findByEmailAndIsDeleted(loginCommand.getEmail(), Constants.NOT_DELETED);

        if (!user.isPresent() || !verifyPassword(loginCommand.getPassword(), user.get().getPassword())) {
            throw new UnauthorizedException("Incorrect email or password");
        }

        Map<Object, Object> jwtResponse = jwtTokenProvider.createToken(loginCommand.getEmail());
        return Collections.singletonMap("jwt", jwtResponse.get("jwtToken").toString());
    }
}
