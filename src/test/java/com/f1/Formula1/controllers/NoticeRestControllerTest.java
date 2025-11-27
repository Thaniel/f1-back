package com.f1.Formula1.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.f1.Formula1.entities.Notice;
import com.f1.Formula1.entities.User;
import com.f1.Formula1.services.NoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(NoticeRestController.class)
class NoticeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticeService noticeService;

    @Autowired
    private ObjectMapper objectMapper;
    
    private Notice noticeTest1;
    private Notice noticeTest2;
    private Notice noticeTest1Update;
    private User userTest;
    
	@BeforeEach
	void setUp() {
		Date date1 = toDate(LocalDate.of(2025, 2, 25));
		Date date2 = toDate(LocalDate.of(2025, 2, 10));

		userTest = new User(1L, "John", "Doe", new Date(), false, "john@example.com", "USA", "johndoe");

		noticeTest1 = new Notice(1L, date1, "summary 1", "text 1", "title 1", null, userTest);
		noticeTest2 = new Notice(2L, date2, "summary 2", "text 2", "title 2", null, userTest);

		noticeTest1Update = new Notice(1L, date1, "summary 1 updated", "text 1 updated", "title 1 updated", null, userTest);
	}
	
	private Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/*
	 * GET /notices - Notices list
	 */
	@Test
	void getAllNoticess_returnsOk() throws Exception {
		when(noticeService.getAll()).thenReturn(List.of(noticeTest1, noticeTest2));

		mockMvc.perform(get("/notices"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[1].id").value(2L));
		
		verify(noticeService).getAll();
	}
	
	/*
	 * Not found
	 */
    @Test
    void getAllNotices_noContent() throws Exception {
        when(noticeService.getAll()).thenReturn(List.of());
        
        mockMvc.perform(get("/notices"))
                .andExpect(status().isNoContent())
                .andExpect(header().string("message", "No notices found"));

        verify(noticeService).getAll();
    }
    
	/*
	 * GET /notices/{id}
	 */
	@Test
	void getNoticeById_found() throws Exception {
		when(noticeService.getById(1L)).thenReturn(noticeTest1);

		mockMvc.perform(get("/notices/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L));
		
		verify(noticeService).getById(1L);
	}
	
	/*
	 * Not found
	 */
	@Test
	void getNoticeById_notFound() throws Exception {
		when(noticeService.getById(3L)).thenReturn(null);

		mockMvc.perform(get("/notices/3"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "Notice not found with id: 3"));
		
		verify(noticeService).getById(3L);
	}
	
	/*
	 * POST /notices
	 */
	@Test
	void saveNotice_createsNotice() throws Exception {
		when(noticeService.create(any(Notice.class))).thenReturn(noticeTest1);

		mockMvc.perform(post("/notices").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(noticeTest1)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "/notices/1"))
				.andExpect(jsonPath("$.id").value(1L));
		
		verify(noticeService).create(any(Notice.class));
	}
	
	/*
	 * PUT /notices/{id}
	 */
	@Test
	void updateNotice_ok() throws Exception {
		when(noticeService.update(any(Notice.class))).thenReturn(noticeTest1Update);

		mockMvc.perform(put("/notices/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(noticeTest1Update)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("title 1 updated"))
				.andExpect(jsonPath("$.text").value("text 1 updated"));
		
		verify(noticeService).update(any(Notice.class));
	}
	
	
	/*
	 * Not found
	 */
	@Test
	void updateNotice_notFound() throws Exception {
		when(noticeService.update(any(Notice.class))).thenReturn(null);

		mockMvc.perform(put("/notices/3").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(noticeTest1)))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "Notice not found with id: 3"));
		
		verify(noticeService).update(any(Notice.class));
	}
	
	/*
	 * DELETE /notices/{id}
	 */
	@Test
	void deleteNotice_found() throws Exception {
		when(noticeService.delete(1L)).thenReturn(noticeTest1);

		mockMvc.perform(delete("/notices/1"))
        		.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
		
		verify(noticeService).delete(1L);
	}
	
	/*
	 * Not found
	 */
	@Test
	void deleteNotice_notFound() throws Exception {
		when(noticeService.delete(3L)).thenReturn(null);

		mockMvc.perform(delete("/notices/3"))
				.andExpect(status().isNotFound())
				.andExpect(header().string("message", "Notice not found with id: 3"));
		
		verify(noticeService).delete(3L);
	}
	
	/*
	 * GET /notices/sortedByDate?order=asc
	 */
	@Test
	void sortAscByDate_found() throws Exception {
		when(noticeService.getNoticesSortedByDate(Sort.Direction.ASC)).thenReturn(List.of(noticeTest2, noticeTest1));

		mockMvc.perform(get("/notices/sortedByDate").param("order", "asc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(2L))
				.andExpect(jsonPath("$[1].id").value(1L));
		
		verify(noticeService).getNoticesSortedByDate(Sort.Direction.ASC);
	}
	
	/*
	 * GET /notices/sortedByDate?order=desc
	 */
	@Test
	void sortDescByDate_found() throws Exception {
		when(noticeService.getNoticesSortedByDate(Sort.Direction.DESC)).thenReturn(List.of(noticeTest1, noticeTest2));

		mockMvc.perform(get("/notices/sortedByDate").param("order", "desc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[1].id").value(2L));
		
		verify(noticeService).getNoticesSortedByDate(Sort.Direction.DESC);
	}
	
	/*
	 * Not found
	 */
	@Test
	void sortDescByDate_notFound() throws Exception {
		when(noticeService.getNoticesSortedByDate(Sort.Direction.ASC)).thenReturn(List.of());

		mockMvc.perform(get("/notices/sortedByDate").param("order", "asc"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No notices found"));

		verify(noticeService).getNoticesSortedByDate(Sort.Direction.ASC);

	}
	
	/*
	 * GET /notices/sortedByComments?order=asc
	 */
	@Test
	void sortAscByComments_found() throws Exception {
		when(noticeService.getNoticesByNumberOfComments(Sort.Direction.ASC)).thenReturn(List.of(noticeTest2, noticeTest1));

		mockMvc.perform(get("/notices/sortedByComments").param("order", "asc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(2L))
				.andExpect(jsonPath("$[1].id").value(1L));
		
		verify(noticeService).getNoticesByNumberOfComments(Sort.Direction.ASC);
	}
	
	/*
	 * GET /notices/sortedByComments?order=desc
	 */
	@Test
	void sortDescByComments_found() throws Exception {
		when(noticeService.getNoticesByNumberOfComments(Sort.Direction.DESC)).thenReturn(List.of(noticeTest1, noticeTest2));

		mockMvc.perform(get("/notices/sortedByComments").param("order", "desc"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[1].id").value(2L));
		
		verify(noticeService).getNoticesByNumberOfComments(Sort.Direction.DESC);
	}
	
	/*
	 * Not found
	 */
	@Test
	void sortDescByComments_notFound() throws Exception {
		when(noticeService.getNoticesByNumberOfComments(Sort.Direction.ASC)).thenReturn(List.of());

		mockMvc.perform(get("/notices/sortedByComments").param("order", "asc"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No notices found"));

		verify(noticeService).getNoticesByNumberOfComments(Sort.Direction.ASC);

	}
	
	/*
	 * GET /notices/year/{year}
	 */
	@Test
	void getNoticesByYear_found() throws Exception {
		when(noticeService.getNoticesByYear(2025)).thenReturn(List.of(noticeTest2, noticeTest1));

		mockMvc.perform(get("/notices/year/2025"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(2L))
				.andExpect(jsonPath("$[1].id").value(1L));
		
		verify(noticeService).getNoticesByYear(2025);
	}
	
	/*
	 * Not found
	 */
	@Test
	void getNoticesByYear_notFound() throws Exception {
		when(noticeService.getNoticesByYear(2020)).thenReturn(List.of());

		mockMvc.perform(get("/notices/year/2020"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No notices found for year: 2020"));

		verify(noticeService).getNoticesByYear(2020);

	}
	
	/*
	 * Invalid
	 */
	@Test
	void getNoticesByYear_invalidYear() throws Exception {
	    mockMvc.perform(get("/notices/year/1700"))
	            .andExpect(status().isBadRequest())
	            .andExpect(header().string("message", "Invalid year: 1700"));
	}
	
	/*
	 * GET /notices/year/{year}/month/{month}
	 */
	@Test
	void getNoticesByYearAndMonth_found() throws Exception {
		when(noticeService.getNoticesByYearAndMonth(2025, 2)).thenReturn(List.of(noticeTest2, noticeTest1));

		mockMvc.perform(get("/notices/year/2025/month/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(2L))
				.andExpect(jsonPath("$[1].id").value(1L));
		
		verify(noticeService).getNoticesByYearAndMonth(2025, 2);
	}
	
	/*
	 * Not found
	 */
	@Test
	void sgetNoticesByYearAndMonth_notFound() throws Exception {
		when(noticeService.getNoticesByYearAndMonth(2020, 4)).thenReturn(List.of());

		mockMvc.perform(get("/notices/year/2020/month/4"))
				.andExpect(status().isNoContent())
				.andExpect(header().string("message", "No notices found for year: 2020 and month: 4"));

		verify(noticeService).getNoticesByYearAndMonth(2020, 4);

	}
	
	/*
	 * Invalid
	 */
    @Test
    void getNoticesByYearAndMonth_invalidMonth() throws Exception {
        mockMvc.perform(get("/notices/year/2024/month/20"))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("message", "Invalid month: 20"));
    }
}
