package com.example.revature;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import com.revature.data.UserRepository;
import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.models.ThrowUsage;
import com.revature.models.User;
import com.revature.service.UserService;

@ExtendWith(MockitoExtension.class)
class RpsUltimateShowdownApiApplicationTests {
	
	private User dummyUser;
	
	@Mock
	private UserRepository mockRepo;
	
	private Credentials creds;
	
	@InjectMocks
	private UserService userv;
	
	//UserService Authenticate Test Succeed
	
	@Test
	public void testSuccessfulAuthentication() {
		dummyUser = new User("test","test","test","test","test@mail.com");
		
		creds = new Credentials("CleverGuy", "swordfish");
		
		when(mockRepo.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword())).thenReturn(Optional.of(dummyUser));
		
		assertAll(() -> userv.authenticate(creds));
	}
	
	//UserService Authenticate Test Failure
	
	@Test
	public void testFailedAuthentication() {
		dummyUser = new User("test","test","test","test","test@mail.com");
		
		creds = new Credentials("CleverGuy", "swordfish");
		
		when(mockRepo.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword())).thenReturn(Optional.empty());
		
		assertThrows(AuthenticationException.class, () -> userv.authenticate(creds));
	}
	
	//UserService Add (registration) Test Succeed
	@Test
	public void testSuccessfulAdd() {
		dummyUser = new User("test","test","test","test","test@mail.com");
		User dummyUserTwo = new User(1, "test","test","test","test","test@mail.com", new ArrayList<ThrowUsage>());
		
		when(mockRepo.save(dummyUser)).thenReturn(dummyUserTwo);
		
		User expectedUser = dummyUserTwo;
		User actualUser = userv.add(dummyUser);
		
		assertEquals(expectedUser, actualUser);
	}
	
	//UserService GetByUserName Succeed
	public void testSuccessfulGetByUsername() {
		
	}
	//UserService GetByUserName Fail
	//UserService GetById Succeed
	//UserService GetByID Fail
	//AuthController login success
	//AuthController login failure
	//UserController add test succeed
	//UserController add test failure
	//UserController add findByUserId Success
	//UserController findByUserID Failure
}
