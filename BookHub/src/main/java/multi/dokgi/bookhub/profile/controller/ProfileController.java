package multi.dokgi.bookhub.profile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import multi.dokgi.bookhub.config.auth.LoginUser;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;
import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;
import multi.dokgi.bookhub.readinglog.service.IReadingLogService;

/**
 * @author GhostFairy
 *
 */
@Controller
public class ProfileController {

	private IReadingLogService rlService;

	public ProfileController(IReadingLogService rlService) {
		this.rlService = rlService;
	}

	// 프로필 페이지 접속
	@RequestMapping(value = { "/profile", "/profile/summary" })
	public ModelAndView profileSummary(@LoginUser SessionUser user) {
		ModelAndView mv = new ModelAndView();

		if (user != null) {
			// 로그인 유저인 경우
			
			// 최근에 읽은 책 (최대 3개)
			List<ReadingLogDTO> recentLog = rlService.getRecentReadingLog(user.getUserId());
			
			// 인터파크 도서 API - 도서 정보 조회
			Map<String, JSONObject> bookInfo = new HashMap<String, JSONObject>();
			for (ReadingLogDTO dto : recentLog) {
				JSONObject book = rlService.getBookInfo(dto.getBookISBN());
				bookInfo.put(dto.getBookISBN(), book);
			}
			
			mv.addObject("user", user);
			mv.addObject("recentLog", recentLog);
			mv.addObject("bookInfo", bookInfo);

			mv.setViewName("profile/summary");
		} else {
			mv.setViewName("redirect:/oauth2/authorization/google");
		}
		return mv;
	}

	// 내 서재 페이지 접속
	@RequestMapping("/profile/library")
	public ModelAndView profileLibrary(@LoginUser SessionUser user) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("user", user);
		mv.setViewName("profile/library");

		return mv;
	}

}
