package multi.dokgi.bookhub;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.dokgi.bookhub.common.code.ErrorCode;
import multi.dokgi.bookhub.common.exception.hadler.UnauthorizedException;
import multi.dokgi.bookhub.config.auth.LoginUser;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;
import multi.dokgi.bookhub.user.dto.Role;

/**
 * @author Seongil, Yoon
 */
@Controller
public class IndexController {

	// 로그인
	@GetMapping({ "", "/" })
	public String home(Model model, @LoginUser SessionUser user) {
		// Session user = (user) httpSession.getAttribute("user")
		// ==> @LoginUser SessionUser user(개선된 코드)
		if (user != null) {
			if (user.getRole() == Role.UN_USER) {
				System.out.println(user.getRole());
				return "register";
			} else if (user.getRole() == Role.USER) {
				return "redirect:/main";
			}
		}
		return "index";
	}

	@ResponseBody
	@GetMapping("/register/vali/{userNick}")
	public Map<String, String> nickCheck(@PathVariable(required = true) String userNick,
			@RequestParam("reqNick") String reqNick, @LoginUser SessionUser user) {
		Map<String, String> map = new HashMap<String, String>();
		return map;
	}

	@GetMapping("/main")
	public String goMain() {
		return "main";
	}

	@GetMapping("/err/denied-page")
	public String accessDenied() {
		throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
	}

}
