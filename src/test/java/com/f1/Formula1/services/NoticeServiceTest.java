package com.f1.Formula1.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import com.f1.Formula1.entities.Notice;
import com.f1.Formula1.entities.User;
import com.f1.Formula1.repositories.INoticeRepository;

public class NoticeServiceTest {

	@Mock
	private INoticeRepository noticeRepository;

	@InjectMocks
	private NoticeService noticeService;

	private Notice noticeTest1;
	private Notice noticeTest2;
	private User userTest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		Date date1 = toDate(LocalDate.of(2025, 2, 25));
		Date date2 = toDate(LocalDate.of(2025, 2, 10));

		userTest = new User(1L, "John", "Doe", new Date(), false, "john@example.com", "USA", "johndoe");

		noticeTest1 = new Notice(1L, date1, "summary 1", "text 1", "title 1", null, userTest);
		noticeTest2 = new Notice(2L, date2, "summary 2", "text 2", "title 2", null, userTest);
	}

	private Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/*
	 * getEntityId
	 */
	@Test
	void getEntityId_returnsCorrectId() {
		Long id = noticeService.getEntityId(noticeTest1);

		assertThat(id).isEqualTo(1L);
	}

	/*
	 * getNoticesSortedByDate
	 */
	@Test
	void getNoticesSortedByDate_returnNoticesInAscOrder() {
		when(noticeRepository.findAll(Sort.by(Sort.Direction.ASC, "date")))
				.thenReturn(List.of(noticeTest2, noticeTest1));

		List<Notice> result = noticeService.getNoticesSortedByDate(Sort.Direction.ASC);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(2l);
		assertThat(result.get(1).getId()).isEqualTo(1l);

		verify(noticeRepository).findAll(Sort.by(Sort.Direction.ASC, "date"));
	}

	@Test
	void getNoticesSortedByDate_returnNoticesInDescOrder() {
		when(noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "date")))
				.thenReturn(List.of(noticeTest1, noticeTest2));

		List<Notice> result = noticeService.getNoticesSortedByDate(Sort.Direction.DESC);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1l);
		assertThat(result.get(1).getId()).isEqualTo(2l);

		verify(noticeRepository).findAll(Sort.by(Sort.Direction.DESC, "date"));
	}

	@Test
	void getNoticesSortedByDate_notFound() {
		when(noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "date"))).thenReturn(List.of());

		List<Notice> result = noticeService.getNoticesSortedByDate(Sort.Direction.DESC);

		assertThat(result).isEmpty();

		verify(noticeRepository).findAll(Sort.by(Sort.Direction.DESC, "date"));
	}

	/*
	 * getNoticesByUserId
	 */
	@Test
	void getNoticesByUserId_returnNotices() {
		when(noticeRepository.findByUserId(1L)).thenReturn(List.of(noticeTest1, noticeTest2));

		List<Notice> result = noticeService.getNoticesByUserId(1L);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1l);
		assertThat(result.get(1).getId()).isEqualTo(2l);

		verify(noticeRepository).findByUserId(1L);
	}

	@Test
	void getNoticesByUserId_notFound() {
		when(noticeRepository.findByUserId(3L)).thenReturn(List.of());

		List<Notice> result = noticeService.getNoticesByUserId(3L);

		assertThat(result).isEmpty();

		verify(noticeRepository).findByUserId(3L);
	}

	/*
	 * getNoticesByNumberOfComments
	 */
    @Test
    void getNoticesByNumberOfComments_returnsSortedAsc() {
        when(noticeRepository.findAllOrderByCommentCountAsc()).thenReturn(List.of(noticeTest1, noticeTest2));

        List<Notice> result = noticeService.getNoticesByNumberOfComments(Sort.Direction.ASC);

        assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1l);
		assertThat(result.get(1).getId()).isEqualTo(2l);
		
        verify(noticeRepository).findAllOrderByCommentCountAsc();
    }
    
    @Test
    void getNoticesByNumberOfComments_returnsSortedDesc() {
        when(noticeRepository.findAllOrderByCommentCountDesc()).thenReturn(List.of(noticeTest2, noticeTest1));

        List<Notice> result = noticeService.getNoticesByNumberOfComments(Sort.Direction.DESC);

        assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(2l);
		assertThat(result.get(1).getId()).isEqualTo(1l);
		
        verify(noticeRepository).findAllOrderByCommentCountDesc();
    }
    
	/*
	 * getNoticesByYear
	 */
	@Test
	void getNoticesByYear_returnNoticesInAscOrder() {
		when(noticeRepository.findByYear(2025)).thenReturn(List.of(noticeTest2, noticeTest1));

		List<Notice> result = noticeService.getNoticesByYear(2025);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(2l);
		assertThat(result.get(1).getId()).isEqualTo(1l);

		verify(noticeRepository).findByYear(2025);
	}

	@Test
	void getNoticesByYear_returnNoticesInDescOrder() {
		when(noticeRepository.findByYear(2025)).thenReturn(List.of(noticeTest1, noticeTest2));

		List<Notice> result = noticeService.getNoticesByYear(2025);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1l);
		assertThat(result.get(1).getId()).isEqualTo(2l);

		verify(noticeRepository).findByYear(2025);
	}

	@Test
	void getNoticesByYear_notFound() {
		when(noticeRepository.findByYear(2020)).thenReturn(List.of());

		List<Notice> result = noticeService.getNoticesByYear(2020);

		assertThat(result).isEmpty();

		verify(noticeRepository).findByYear(2020);
	}

	/*
	 * getNoticesByYearAndMonth
	 */
	@Test
	void getNoticesByYearAndMonth_returnNoticesInAscOrder() {
		when(noticeRepository.findByYearAndMonth(2025, 2)).thenReturn(List.of(noticeTest2, noticeTest1));

		List<Notice> result = noticeService.getNoticesByYearAndMonth(2025, 2);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(2l);
		assertThat(result.get(1).getId()).isEqualTo(1l);

		verify(noticeRepository).findByYearAndMonth(2025, 2);
	}

	@Test
	void getNoticesByYearAndMonth_returnNoticesInDescOrder() {
		when(noticeRepository.findByYearAndMonth(2025, 2)).thenReturn(List.of(noticeTest1, noticeTest2));

		List<Notice> result = noticeService.getNoticesByYearAndMonth(2025, 2);

		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(1l);
		assertThat(result.get(1).getId()).isEqualTo(2l);

		verify(noticeRepository).findByYearAndMonth(2025, 2);
	}

	@Test
	void getNoticesByYearAndMonth_notFound() {
		when(noticeRepository.findByYear(2020)).thenReturn(List.of());

		List<Notice> result = noticeService.getNoticesByYear(2020);

		assertThat(result).isEmpty();

		verify(noticeRepository).findByYear(2020);
	}
}
