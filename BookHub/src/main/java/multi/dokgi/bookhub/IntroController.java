package multi.dokgi.bookhub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class IntroController {
	@GetMapping("introduction")
	public ModelAndView studyRegisterWrite0() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("introduction");
		
		return mav;
	}
}
