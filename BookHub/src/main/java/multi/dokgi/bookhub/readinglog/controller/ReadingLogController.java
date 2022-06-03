package multi.dokgi.bookhub.readinglog.controller;

import org.json.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import multi.dokgi.bookhub.config.auth.LoginUser;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;
import multi.dokgi.bookhub.readinglog.service.IReadingLogService;

/**
 * @author GhostFairy
 *
 */
@Controller
public class ReadingLogController {

	private IReadingLogService rlService;

	public ReadingLogController(IReadingLogService rlService) {
		this.rlService = rlService;
	}

	@GetMapping("/readinglog/edit")
	public ModelAndView readingLogEdit(@LoginUser SessionUser user, String isbn) {
		ModelAndView mv = new ModelAndView();
		JSONObject book = rlService.getBookInfo(isbn);

		mv.addObject("bookInfo", book);
		mv.addObject("userInfo", user);
		mv.setViewName("readinglog/edit");

		return mv;
	}

	@PostMapping("/readinglog/edit")
	public ModelAndView readingLogEditResult(@LoginUser SessionUser user, String isbn, Integer startPage,
			Integer endPage, String readDate, @Nullable String readComplete, @Nullable String summary) {
		ModelAndView mv = new ModelAndView();
		String userId = user.getUserId();

		rlService.writeReadingLog(userId, isbn, startPage, endPage, summary, readDate, readComplete);

		mv.setViewName("redirect:/readinglog/edit?isbn=" + isbn);

		return mv;
	}

}
