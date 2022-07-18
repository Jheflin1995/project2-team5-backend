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
import com.revature.exceptions.UserNotFoundException;
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
		dummyUser = new User(1, "test","test","test","test","test@mail.com", new ArrayList<ThrowUsage>());
		
		creds = new Credentials("test", "test");
		
		when(mockRepo.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword())).thenReturn(Optional.of(dummyUser));
		
		//assertAll(() -> userv.authenticate(creds));
		
		User expectedUser = dummyUser;
		User actualUser = userv.authenticate(creds);
		
		assertEquals(expectedUser, actualUser);
	}
	
	//UserService Authenticate Test Failure
	
	@Test
	public void testFailedAuthentication() {
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
	@Test
	public void testSuccessfulGetByUsername() {
		dummyUser = new User(1, "test","test","test","test","test@mail.com", new ArrayList<ThrowUsage>());
		String username = "test";
		
		when(mockRepo.findByUsername(username)).thenReturn(Optional.of(dummyUser));
		
		User expectedUser = dummyUser;
		User actualUser = userv.getByUsername(username);
		
		assertEquals(expectedUser, actualUser);
	}
	//UserService GetByUserName Fail
	@Test
	public void testFailedGetByUsername() {
		String username = "CleverGuy";
		
		when(mockRepo.findByUsername(username)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> userv.getByUsername(username));
	}
	//UserService GetById Succeed
	@Test
	public void testSuccessfulGetById() {
		dummyUser = new User(1, "test","test","test","test","test@mail.com", new ArrayList<ThrowUsage>());
		int id = 1;
		
		when(mockRepo.findById(id)).thenReturn(Optional.of(dummyUser));
		
		User expectedUser = dummyUser;
		User actualUser = userv.getById(id);
		
		assertEquals(expectedUser, actualUser);
	}
	//UserService GetByID Fail
	@Test
	public void testFailedGetById() {
		int id = 2;
		
		when(mockRepo.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> userv.getById(id));
	}
	
	//UserService GetByID OOB
	@Test
	public void testOobGetById() {
		int id = 0;
		
		assertNull(userv.getById(id));
	}
}
