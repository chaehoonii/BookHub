package multi.dokgi.bookhub;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import multi.dokgi.bookhub.config.auth.LoginUser;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;

@Controller
public class HomeController {
	//메인페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(@LoginUser SessionUser user) {
        ModelAndView mav = new ModelAndView();
        //model.addAttribute("user", user);
        mav.setViewName("main");
        return mav;
    }
}