package com.mace.swfs;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.mace.swfs.commands.user.UserCreateCommand;
import com.mace.swfs.controllers.UserController;
import com.mace.swfs.persistance.repositories.UserRepository;
import com.mace.swfs.services.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Test
	public void testCreateUser() {
		UserCreateCommand userCreateCommand = new UserCreateCommand("testuser", "fatherName", "phone",
				"test@example.com", "address", "password");
		Long userId = userService.saveUser(userCreateCommand);
		assertNotNull(userId);
	}

}
