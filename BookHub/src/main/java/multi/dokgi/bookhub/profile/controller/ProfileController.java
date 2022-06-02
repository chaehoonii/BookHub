package multi.dokgi.bookhub.profile.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author GhostFairy
 *
 */
@Controller
public class ProfileController {

	@RequestMapping("/profile")
	public ModelAndView profile() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("profile/summary");

		return mv;
	}

	@RequestMapping(value = { "/profile/{username}", "/profile/{username}/summary" })
	public ModelAndView profileSummary(@Nullable @PathVariable("username") String username) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("username", username);
		mv.setViewName("profile/summary");

		return mv;
	}

	@RequestMapping("/profile/{username}/library")
	public ModelAndView profileLibrary(@Nullable @PathVariable("username") String username) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("username", username);
		mv.setViewName("profile/library");

		return mv;
	}

}
