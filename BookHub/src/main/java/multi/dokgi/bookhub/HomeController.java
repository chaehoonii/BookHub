package multi.dokgi.bookhub;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import multi.dokgi.bookhub.config.auth.LoginUser;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;
import multi.dokgi.bookhub.user.dto.Role;

@Controller
public class HomeController {
	// 메인페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(@LoginUser SessionUser user) {
		ModelAndView mav = new ModelAndView();
		if(user != null) {
			System.out.println("사용자닉 :" + user.getUserNick() + ", 현재권한 :" + user.getUserRole());
			if (user.getUserRole() == Role.UN_USER) {
				// 회원가입 절차중에 있는 사용자는 UN_USER
				mav.addObject("user", user);
				mav.setViewName("register");
				return mav;
			}
		}
		mav.setViewName("main");
		return mav;
	}
}