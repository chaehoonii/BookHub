package multi.dokgi.bookhub.readinglog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import multi.dokgi.bookhub.config.auth.LoginUser;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;
import multi.dokgi.bookhub.readinglog.dto.ReadingCalendarDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingReviewDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingStreakDTO;
import multi.dokgi.bookhub.readinglog.service.IReadingLogService;

/**
 * @author GhostFairy
 *
 */
@Controller
public class ReadingLogController {

	private IReadingLogService rlService;

	// 생성자 주입
	public ReadingLogController(IReadingLogService rlService) {
		this.rlService = rlService;
	}

	// 활동 요약 페이지 접속
	@RequestMapping(value = { "/rlog", "/rlog/summary" })
	public ModelAndView profileSummary(@LoginUser SessionUser user) {
		ModelAndView mv = new ModelAndView();

		if (user != null) {
			// 로그인 유저인 경우
			String userId = user.getUserId();

			// 최근 읽은 책 (최대 3개)
			List<ReadingLogDTO> recentLog = rlService.getRecentBook(userId);

			// 최근 작성한 리뷰 (최대 3개)
			List<ReadingReviewDTO> recentReview = rlService.getRecentReview(userId);

			// 인터파크 도서 API - ISBN으로 도서 정보 검색
			Map<String, JSONObject> bookInfo = new HashMap<String, JSONObject>();
			for (ReadingLogDTO dto : recentLog) {
				JSONObject book = rlService.getBookInfo(dto.getBookISBN());
				bookInfo.put(dto.getBookISBN(), book);
			}
			for (ReadingReviewDTO dto : recentReview) {
				if (!bookInfo.containsKey(dto.getBookISBN())) {
					JSONObject book = rlService.getBookInfo(dto.getBookISBN());
					bookInfo.put(dto.getBookISBN(), book);
				}
			}

			// 누적 독서 페이지
			Integer accReadPages = rlService.getAccReadPages(userId);

			// 연속 독서일
			Map<String, ReadingStreakDTO> streak = rlService.getStreak(userId);

			mv.addObject("user", user);
			mv.addObject("recentLog", recentLog);
			mv.addObject("recentReview", recentReview);
			mv.addObject("bookInfo", bookInfo);
			mv.addObject("accReadPages", accReadPages);
			mv.addObject("streak", streak);

			mv.setViewName("rlog/summary");
		}
		return mv;
	}

	// API: 최근 독서활동
	@RequestMapping("/rlog/recentcalendar")
	@ResponseBody
	public String summaryCalendar(@LoginUser SessionUser user) {
		String userId = user.getUserId();

		// 최근 독서활동
		List<ReadingCalendarDTO> recentCalendar = rlService.getRecentCalendar(userId);
		JSONObject out = new JSONObject();

		out.put("recentCalendar", recentCalendar);

		return out.toString();
	}

	// 내 서재 페이지 접속
	@RequestMapping("/rlog/library")
	public ModelAndView profileLibrary(@LoginUser SessionUser user, String q, String read, Integer page) {
		ModelAndView mv = new ModelAndView();

		if (user != null) {
			// 로그인 유저인 경우
			String userId = user.getUserId();

			List<ReadingLogDTO> library = null;
			if (q == null || q.equals("")) {
				library = rlService.getLibrary(userId, page);
			} else {
				library = rlService.searchLibrary(userId);
			}

			// 인터파크 도서 API - ISBN으로 도서 정보 검색
			Map<String, JSONObject> bookInfo = new HashMap<String, JSONObject>();
			for (ReadingLogDTO dto : library) {
				JSONObject book = rlService.getBookInfo(dto.getBookISBN());
				bookInfo.put(dto.getBookISBN(), book);
			}

			if (q != null && !q.equals("")) {
				library.removeIf((dto) -> (!((String) bookInfo.get(dto.getBookISBN()).get("title")).contains(q)));
			}
			if (read != null) {
				if (read.equals("progress")) {
					library.removeIf((dto) -> (dto.isReadComplete()));
				} else if (read.equals("complete")) {
					library.removeIf((dto) -> (!dto.isReadComplete()));
				}
			}

			mv.addObject("user", user);
			mv.addObject("library", library);
			mv.addObject("bookInfo", bookInfo);

			mv.setViewName("rlog/library");
		}
		return mv;
	}

	// 독서활동 기록 페이지 접속
	@RequestMapping("/rlog/book")
	public ModelAndView readingLogBook(@LoginUser SessionUser user, String isbn) {
		ModelAndView mv = new ModelAndView();
		if (user != null) {
			// 로그인 유저인 경우
			String userId = user.getUserId();

			List<ReadingLogDTO> readingLog = rlService.getReadingLog(userId, isbn);
			Integer readingLogSum = rlService.getReadingLogSum(userId, isbn);
			JSONObject bookInfo = rlService.getBookInfo(isbn);

			mv.addObject("user", user);
			mv.addObject("readingLogSum", readingLogSum);
			mv.addObject("readingLog", readingLog);
			mv.addObject("bookInfo", bookInfo);

			mv.setViewName("rlog/book");
		}
		return mv;
	}

	// 독서활동 기록
	@PostMapping("/rlog/book/edit")
	public ModelAndView readingLogEdit(@LoginUser SessionUser user, String isbn, Integer readPage, String readDate,
			@Nullable String readComplete, @Nullable String summary) {
		ModelAndView mv = new ModelAndView();

		if (user != null) {
			// 로그인 유저인 경우
			String userId = user.getUserId();

			rlService.writeReadingLog(userId, isbn, readPage, summary, readDate, readComplete);

			mv.setViewName("redirect:/rlog/book?isbn=" + isbn);
		}
		return mv;
	}

	// 독서활동 삭제
	@PostMapping("/rlog/book/delete")
	@ResponseBody
	public String readingLogDelete(@LoginUser SessionUser user, @RequestBody String data) {
		JSONObject json = new JSONObject(data);
		String bookISBN = (String) json.get("bookISBN");
		Integer num = Integer.parseInt((String) json.get("num"));

		if (user != null) {
			// 로그인 유저인 경우
			String userId = user.getUserId();

			if (rlService.deleteReadingLog(userId, bookISBN, num)) {
				return "success";
			}
		}
		return "fail";
	}

	// 모든 독서활동 삭제
	@PostMapping("/rlog/book/alldelete")
	@ResponseBody
	public String readingLogAllDelete(@LoginUser SessionUser user, @RequestBody String data) {
		JSONObject json = new JSONObject(data);
		String bookISBN = (String) json.get("bookISBN");

		if (user != null) {
			// 로그인 유저인 경우
			String userId = user.getUserId();

			if (rlService.deleteAllReadingLog(userId, bookISBN)) {
				return "success";
			}
		}
		return "fail";
	}

}
