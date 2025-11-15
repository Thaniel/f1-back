package com.f1.Formula1.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.f1.Formula1.dtos.NoticeDTO;
import com.f1.Formula1.dtos.TopicDTO;
import com.f1.Formula1.entities.Notice;
import com.f1.Formula1.entities.Topic;
import com.f1.Formula1.entities.User;
import com.f1.Formula1.mappers.NoticeMapper;
import com.f1.Formula1.mappers.TopicMapper;
import com.f1.Formula1.services.NoticeService;
import com.f1.Formula1.services.TopicService;
import com.f1.Formula1.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

	@Autowired
	private MockMvc mockMvc; // simulates HTTP request

	@MockBean
	private UserService userService; // simulates business logic

	@MockBean
	private NoticeService noticeService;

	@MockBean
	private TopicService topicService;

	@Autowired
	private ObjectMapper objectMapper; // converts objects to JSON

	private User userTest;
	private Notice noticeTest;
	private Topic topicTest;

	@BeforeEach
	void setUp() {
		userTest = new User(1L, "John", "Doe", new Date(), false, "john@example.com", "USA", "johndoe");
		noticeTest = new Notice(1L, new Date(), "Summary", "Text", "Notice title", null, userTest);
		topicTest = new Topic(1L, new Date(), "Topic title", userTest);
	}

	/*
	 * GET /users - Users list
	 */
	@Test
	void getAllUsers_returnsOk() throws Exception {
		when(userService.getAll()).thenReturn(List.of(userTest));

		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].userName").value("johndoe"))
				.andExpect(jsonPath("$[0].email").value("john@example.com"));
		
		verify(userService).getAll();
	}

	/*
	 * Not found
	 */
	@Test
	void getAllUsers_noContent() throws Exception {
		when(userService.getAll()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/users"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No users found"));
		
        verify(userService).getAll();
	}

	/*
	 * GET /users/{id}
	 */
	@Test
	void getUserById_found() throws Exception {
		when(userService.getById(1L)).thenReturn(userTest);

		mockMvc.perform(get("/users/1"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.userName").value("johndoe"))
				.andExpect(jsonPath("$.email").value("john@example.com"));
		
        verify(userService).getById(1L);
	}

	/*
	 * Not found
	 */
	@Test
	void getUserById_notFound() throws Exception {
		when(userService.getById(1L)).thenReturn(null);

		mockMvc.perform(get("/users/1"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "User not found with id: 1"));
		
		verify(userService).getById(1L);
	}

	/*
	 * POST /users
	 */
	@Test
	void saveUser_createsUser() throws Exception {
		when(userService.create(any(User.class))).thenReturn(userTest);

		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userTest)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "/users/1"))
				.andExpect(jsonPath("$.email").value("john@example.com"));
		
		verify(userService).create(any(User.class));
	}

	/*
	 * PUT /users/{id}
	 */
	@Test
	void updateUser_ok() throws Exception {
		when(userService.update(any(User.class))).thenReturn(userTest);

		mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userTest)))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.firstName").value("John"));
		
		verify(userService).update(any(User.class));
	}

	/*
	 * Not found
	 */
	@Test
	void updateUser_notFound() throws Exception {
		when(userService.update(any(User.class))).thenReturn(null);

		mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userTest)))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "User not found with id: 1"));
		
		verify(userService).update(any(User.class));
	}

	/*
	 * DELETE /users/{id}
	 */
	@Test
	void deleteUser_found() throws Exception {
		when(userService.delete(1L)).thenReturn(userTest);

		mockMvc.perform(delete("/users/1"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.userName").value("johndoe"));
		
		verify(userService).delete(1L);
	}

	/*
	 * Not found
	 */
	@Test
	void deleteUser_notFound() throws Exception {
		when(userService.delete(1L)).thenReturn(null);

		mockMvc.perform(delete("/users/1"))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "User not found with id: 1"));
		
		verify(userService).delete(1L);
	}

	/*
	 * GET /users/search?username=johndoe
	 */
	@Test
	void getUserByUsername_found() throws Exception {
		when(userService.getUserByUsername(userTest.getUserName())).thenReturn(userTest);

		mockMvc.perform(get("/users/search").param("username", userTest.getUserName()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userName").value("johndoe"))
				.andExpect(jsonPath("$.email").value("john@example.com"));
		
		verify(userService).getUserByUsername(userTest.getUserName());
	}

	/*
	 * Not found
	 */
	@Test
	void getUserByUsername_notFound() throws Exception {
		when(userService.getUserByUsername(userTest.getUserName())).thenReturn(null);

		mockMvc.perform(get("/users/search").param("username", userTest.getUserName()))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "User not found with username: johndoe"));
		
		verify(userService).getUserByUsername(userTest.getUserName());
	}

	/*
	 * GET /users/paged?page=x&size=y
	 */
	@Test
	void getUsersPaged_found() throws Exception {
		Page<User> page = new PageImpl<>(List.of(userTest));

		when(userService.getAllPaged(anyInt(), anyInt())).thenReturn(page);

		mockMvc.perform(get("/users/paged").param("page", "0").param("size", "10").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].id").value(1L))
				.andExpect(jsonPath("$.content[0].userName").value("johndoe"));
		
		verify(userService).getAllPaged(anyInt(), anyInt());
	}

	/*
	 * Not found
	 */
	@Test
	void getUsersPaged_notFound() throws Exception {
		Page<User> emptyPage = new PageImpl<>(Collections.emptyList());

		when(userService.getAllPaged(anyInt(), anyInt())).thenReturn(emptyPage);

		mockMvc.perform(get("/users/paged").param("page", "0").param("size", "10").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No users found"));
		
		verify(userService).getAllPaged(anyInt(), anyInt());
	}

	/*
	 * GET /users/{userId}/notices
	 */
	@Test
	void getNoticesByUserId_found() throws Exception {
		when(noticeService.getNoticesByUserId(1L)).thenReturn(List.of(noticeTest));

		// Mock static mapper (if it's static, you can use mockito-inline)
		Mockito.mockStatic(NoticeMapper.class).when(() -> NoticeMapper.toDTOList(anyList()))
				.thenReturn(List.of(new NoticeDTO(noticeTest)));

		mockMvc.perform(get("/users/1/notices").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].title").value("Notice title"));
		
		verify(noticeService).getNoticesByUserId(1L);
	}

	/*
	 * Not found
	 */
	@Test
	void getNoticesByUserId_notFound() throws Exception {
		when(noticeService.getNoticesByUserId(1L)).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/users/1/notices").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No notices found for user id: 1"));
		
		verify(noticeService).getNoticesByUserId(1L);
	}

	/*
	 * GET /users/{userId}/topics
	 */
	@Test
	void getTopicsByUserId_found() throws Exception {
		when(topicService.getTopicsByUserId(1L)).thenReturn(List.of(topicTest));

		Mockito.mockStatic(TopicMapper.class).when(() -> TopicMapper.toDTOList(anyList()))
				.thenReturn(List.of(new TopicDTO(topicTest)));

		mockMvc.perform(get("/users/1/topics").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].title").value("Topic title"));
		
		verify(topicService).getTopicsByUserId(1L);
	}

	/*
	 * Not found
	 */
	@Test
	void getTopicsByUserId_notFound() throws Exception {
		when(topicService.getTopicsByUserId(1L)).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/users/1/topics").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No topics found for user id: 1"));
		
		verify(topicService).getTopicsByUserId(1L);
	}
}
