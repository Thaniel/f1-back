package com.f1.Formula1.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
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
	 * Verify that the service returns the correct user name.
	 */
	@Test
	void getUserByUsername_returnUser() {
		// GIVEN - Mock behavior
		when(userRepository.findByUserName("johndoe")).thenReturn(userTest);

		// WHEN - Execute the method to be tested
		User result = userService.getUserByUsername("johndoe");

		// THEN - Check result
		assertThat(result).isNotNull();
		assertThat(result.getEmail()).isEqualTo(userTest.getEmail());
		assertThat(result.getFirstName()).isEqualTo(userTest.getFirstName());
		assertThat(result.getLastName()).isEqualTo(userTest.getLastName());
		assertThat(result.getBirthDate()).isEqualTo(userTest.getBirthDate());
		assertThat(result.isAdmin()).isEqualTo(userTest.isAdmin());
		assertThat(result.getCountry()).isEqualTo(userTest.getCountry());
		assertThat(result.getUserName()).isEqualTo(userTest.getUserName());

		// Verify that the mock was called correctly
		verify(userRepository).findByUserName("johndoe");
	}

	/*
	 * Verify the pagination
	 */
	@Test
	void getAllPaged_returnUsersPaginated() {
		// GIVEN
		Page<User> fakePage = new PageImpl<>(List.of(userTest));
		when(userRepository.findAll(any(PageRequest.class))).thenReturn(fakePage);

		// WHEN
		Page<User> result = userService.getAllPaged(0, 10);

		// THEN
		assertThat(result).isNotEmpty();
		assertThat(result.getContent().get(0).getUserName()).isEqualTo(userTest.getUserName());

		verify(userRepository).findAll(any(PageRequest.class));
	}
	
	// Verify when there are no results
    @Test
    void getAllPaged_withoutUsersReturnsEmptyPage() {
        // GIVEN
        Page<User> emptyPage = new PageImpl<>(Collections.emptyList());
        when(userRepository.findAll(any(PageRequest.class))).thenReturn(emptyPage);

        // WHEN
        Page<User> result = userService.getAllPaged(0, 10);

        // THEN
        assertThat(result).isEmpty();
        verify(userRepository).findAll(any(PageRequest.class));
    }
}
