package multi.dokgi.bookhub.profile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	// 아이디 없이 프로필 페이지 접속시
	// 로그인했으면 로그인한 회원의 프로필로 연결
	@RequestMapping("/profile")
	public ModelAndView profile(@LoginUser SessionUser user) {
		ModelAndView mv = new ModelAndView();

		if (user != null) {
			mv.setViewName("redirect:/profile/" + user.getUserId());
		} else {
			mv.setViewName("redirect:/oauth2/authorization/google");
		}

		return mv;
	}

	@RequestMapping(value = { "/profile/{userId}", "/profile/{userId}/summary" })
	public ModelAndView profileSummary(@Nullable @PathVariable("userId") String userId) {
		ModelAndView mv = new ModelAndView();

		List<ReadingLogDTO> recentLog = rlService.getRecentReadingLog(userId);
		Map<ReadingLogDTO, JSONObject> map = new HashMap<ReadingLogDTO, JSONObject>();

		for (ReadingLogDTO dto : recentLog) {
			JSONObject book = rlService.getBookInfo(dto.getBookISBN());
			map.put(dto, book);
		}

		mv.addObject("userId", userId);
		mv.addObject("recentLog", map);
		mv.setViewName("profile/summary");

		return mv;
	}

	@RequestMapping("/profile/{userId}/library")
	public ModelAndView profileLibrary(@Nullable @PathVariable("userId") String userId) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("userId", userId);
		mv.setViewName("profile/library");

		return mv;
	}

}
