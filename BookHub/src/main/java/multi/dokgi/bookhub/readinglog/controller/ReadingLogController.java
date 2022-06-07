package multi.dokgi.bookhub.readinglog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import multi.dokgi.bookhub.config.auth.LoginUser;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;
import multi.dokgi.bookhub.readinglog.dto.ReadingCalendarDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;
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

			// 최근 읽은 책 (최대 3개)
			List<ReadingLogDTO> recentLog = rlService.getRecentReadingLog(user.getUserId());

			// 인터파크 도서 API - ISBN으로 도서 정보 검색
			Map<String, JSONObject> bookInfo = new HashMap<String, JSONObject>();
			for (ReadingLogDTO dto : recentLog) {
				JSONObject book = rlService.getBookInfo(dto.getBookISBN());
				bookInfo.put(dto.getBookISBN(), book);
			}

			mv.addObject("user", user);
			mv.addObject("recentLog", recentLog);
			mv.addObject("bookInfo", bookInfo);

			mv.setViewName("rlog/summary");
		} else {
			mv.setViewName("redirect:/oauth2/authorization/google");
		}
		return mv;
	}

	// API: 최근 독서 활동
	@RequestMapping("/rlog/recentcalendar")
	@ResponseBody
	public String summaryCalendar(@LoginUser SessionUser user) {
		// 최근 독서 활동
		List<ReadingCalendarDTO> recentCalendar = rlService.getRecentReadingCalendar(user.getUserId());
		JSONObject out = new JSONObject();

		out.put("recentCalendar", recentCalendar);

		return out.toString();
	}

	// 내 서재 페이지 접속
	@RequestMapping("/rlog/library")
	public ModelAndView profileLibrary(@LoginUser SessionUser user) {
		ModelAndView mv = new ModelAndView();

		if (user != null) {
			// 로그인 유저인 경우

			mv.addObject("user", user);

			mv.setViewName("rlog/library");
		} else {
			mv.setViewName("redirect:/oauth2/authorization/google");
		}
		return mv;
	}

	// 독서 활동 기록 페이지 접속(임시)
	@GetMapping("/rlog/edit")
	public ModelAndView readingLogEdit(@LoginUser SessionUser user, String isbn) {
		ModelAndView mv = new ModelAndView();
		if (user != null) {
			// 로그인 유저인 경우

			JSONObject bookInfo = rlService.getBookInfo(isbn);

			mv.addObject("user", user);
			mv.addObject("bookInfo", bookInfo);

			mv.setViewName("rlog/edit");

		} else {
			mv.setViewName("redirect:/oauth2/authorization/google");
		}
		return mv;
	}

	// 독서 활동 기록 (임시)
	@PostMapping("/rlog/edit")
	public ModelAndView readingLogEditResult(@LoginUser SessionUser user, String isbn, Integer startPage,
			Integer endPage, String readDate, @Nullable String readComplete, @Nullable String summary) {
		ModelAndView mv = new ModelAndView();

		if (user != null) {
			// 로그인 유저인 경우

			String userId = user.getUserId();
			rlService.writeReadingLog(userId, isbn, startPage, endPage, summary, readDate, readComplete);

			mv.setViewName("redirect:/rlog/edit?isbn=" + isbn);

		} else {
			mv.setViewName("redirect:/oauth2/authorization/google");
		}
		return mv;
	}

}
