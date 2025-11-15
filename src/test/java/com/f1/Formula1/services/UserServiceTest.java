package com.f1.Formula1.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.f1.Formula1.entities.User;
import com.f1.Formula1.repositories.IUserRepository;

public class UserServiceTest {

	@Mock // Mock the repository
	private IUserRepository userRepository;

	@InjectMocks // Inject the mock into the class we want to test
	private UserService userService;

	private User userTest;

	@BeforeEach // Configure the environment before each test
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize the mocks
		userTest = new User(1L, "John", "Doe", new Date(), false, "john@example.com", "USA", "johndoe");
	}
	
	/*
	 * getEntityId
	 */
    @Test
    void getEntityId_returnsCorrectId() {
        Long id = userService.getEntityId(userTest);

        assertThat(id).isEqualTo(1L);
    }
	
	/*
	 * getUserByUsername
	 */
	@Test
	void getUserByUsername_found() {
		// GIVEN - Mock behavior
		when(userRepository.findByUserName(userTest.getUserName())).thenReturn(userTest);

		// WHEN - Execute the method to be tested
		User result = userService.getUserByUsername(userTest.getUserName());

		// THEN - Check result
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1L);
		assertThat(result.getEmail()).isEqualTo("john@example.com");
		assertThat(result.getUserName()).isEqualTo("johndoe");

		// Verify that the mock was called correctly
		verify(userRepository).findByUserName(userTest.getUserName());
	}
	
    @Test
    void getUserByUsername_notFound() {
        when(userRepository.findByUserName("name")).thenReturn(null);

        User result = userService.getUserByUsername("name");

        assertThat(result).isNull();
        verify(userRepository).findByUserName("name");
    }

	/*
	 * getAllPaged
	 */
	@Test
	void getAllPaged_returnsPage() {
		// GIVEN
		Page<User> fakePage = new PageImpl<>(List.of(userTest));
		when(userRepository.findAll(any(PageRequest.class))).thenReturn(fakePage);

		// WHEN
		Page<User> result = userService.getAllPaged(0, 10);

		// THEN
		assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
		assertThat(result.getContent().get(0).getUserName()).isEqualTo("johndoe");

		verify(userRepository).findAll(any(PageRequest.class));
	}
	
	
    @Test
    void getAllPaged_returnsEmptyPage() {
        // GIVEN
        Page<User> emptyPage = Page.empty();
        when(userRepository.findAll(any(PageRequest.class))).thenReturn(emptyPage);

        // WHEN
        Page<User> result = userService.getAllPaged(0, 10);

        // THEN
        assertThat(result).isEmpty();
        verify(userRepository).findAll(any(PageRequest.class));
    }
}
