package multi.dokgi.bookhub.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.dokgi.bookhub.common.code.ErrorCode;
import multi.dokgi.bookhub.common.exception.hadler.UnauthorizedException;
import multi.dokgi.bookhub.config.auth.LoginUser;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;
import multi.dokgi.bookhub.user.dao.IUserDAO;
import multi.dokgi.bookhub.user.dto.Role;
import multi.dokgi.bookhub.user.dto.UserDTO;
import multi.dokgi.bookhub.user.service.IUserService;

/**
 * @author Seongil, Yoon
 */
@Controller
public class UserController {

	@Autowired
	IUserDAO userDao;

	@Autowired
	IUserService userService;

	// 헤더의 로그인버튼
//	@GetMapping("/google-login")
//	public String home(Model model, @LoginUser SessionUser user) {
//		// Session user = (user) httpSession.getAttribute("user")
//		// Authentication auth , parseService.parseUserId
//		// ==> @LoginUser SessionUser user(개선된 코드)
//		if (user != null) {
//			if (user.getUserRole() == Role.UN_USER) {
//				model.addAttribute("user", user);
//				return "register";
//			} else if (user.getUserRole() == Role.USER) {
//				return "redirect:/main";
//			}
//		} else {
//			// 세션자체가 null이므로 구글로그인폼으로 이동
//			return "redirect:/oauth2/authorization/google";
//		}
//		return "index";
//	}

	@ResponseBody
	@GetMapping("/register/vali/{userId}")
	public Map<String, String> nickCheck(@PathVariable(required = true) String userId,
			@RequestParam("reqNick") String reqNick, @LoginUser SessionUser user) {
		Map<String, String> map = new HashMap<String, String>();

		// 비 로그인
		if (user == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}

		if (reqNick.trim().isEmpty()) { // 입력된 문자가 없으면(입력된 값 전체가 공백이면)
			map.put("result", "blank");
		} else if (userDao.findByMbNick(reqNick) == null || userDao.findByMbNick(reqNick).getUserId().equals(userId)) {

			map.put("result", "ok");
			map.put("nickcheck", reqNick);
		} else {
			map.put("result", "duplicated");
		}
		return map;
	}

	// 회원가입
	@PostMapping("/register/confirm")
	public String registersns(Model model, @LoginUser SessionUser user, String userId, String userRole, String userNick,
			HttpServletRequest req) {

		// 비 로그인
		if (user == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}

		// 유저 정보 업데이트
		UserDTO userDto = userDao.findByUserId(userId);
		userDto.setUserNick(userNick);
		userDto.setUserRole(Role.USER);

		// DB에 UPDATE
		userDao.updateRegister(userDto);

		// 세션 수정
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userDto.getRoleKey()));
		// 세션에 변경사항 저장
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(new UsernamePasswordAuthenticationToken(userDto.getUserId(), null, authorities));
		HttpSession session = req.getSession(true);
		// 위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

		model.addAttribute("user", userDto);
		System.out.println("사용자닉 :" + user.getUserNick() + ", 현재권한 :" + user.getUserRole());

		// 브라우저 쿠키까지 삭제해야 되지만 어차피 초기화면으로 이동할꺼라 /logout으로 처리(로그아웃하면 쿠키(세션값) 초기화)
		return "redirect:/logout";
	}

	@GetMapping("/err/denied-page")
	public String accessDenied() {
		throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
	}

	@GetMapping("/settings")
	public String userSettingsView(@LoginUser SessionUser user, Model model) {
		if (user == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		return "/user/settings-modifyuser";
	}

	@GetMapping("/settings-withdraw")
	public String withdrawView(@LoginUser SessionUser user, Model model) {
		if (user == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		return "/user/settings-withdraw";
	}

	@PostMapping("/settings-withdraw")
	public String withdraw(@LoginUser SessionUser user, Model model) {
		if (user == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		userService.withdraw(user.getUserId());

		return "redirect:/logout";
	}

}
