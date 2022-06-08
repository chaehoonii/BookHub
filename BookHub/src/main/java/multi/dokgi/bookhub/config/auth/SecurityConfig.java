package multi.dokgi.bookhub.config.auth;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import multi.dokgi.bookhub.user.dto.Role;

/**
 * @author Seongil, Yoon
 *
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터를 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig {
	
	private final CustomOAuth2UserService customOAuth2UserService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf: 페이지 위변조 방지. 유니크 키값 토큰넣어줌.
				.exceptionHandling().accessDeniedPage("/err/denied-page"); // 접근 불가 페이지

		// 화이트리스트
		http.authorizeRequests().antMatchers("/", "/login/**","/google-login/**", "/static/**", "/logout/**", "/err*").permitAll()
			.antMatchers("/book**").permitAll()
			.antMatchers("/register/**").permitAll()
			.antMatchers("/settings**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/rlog**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated();

		// 기본 로그인 해제
		http.httpBasic().disable();

		http.logout()
	        .clearAuthentication(true)
	        .invalidateHttpSession(true)
	        .deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/");

		http.oauth2Login().defaultSuccessUrl("/") // oauth2 로그인
				.userInfoEndpoint() // oauth2Login 성공 이후의 설정을 시작
				.userService(customOAuth2UserService);

		http.sessionManagement().invalidSessionUrl("/") // 유효하지 않은 세션 접근시 보낼 URL
				.maximumSessions(1) // 중복 로그인 방지
				.maxSessionsPreventsLogin(false);

		http.sessionManagement().sessionFixation().migrateSession(); // 인증이 됐을 때 새로운 세션을 생성한뒤, 기존 세션의 attribute들을 복사
		return http.build();
	}

	// 정적 파일 열기
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/favicon.ico", "/static/**", "/error", "/lib/**", "/img/**")
				.mvcMatchers("/static/**").requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	// 세션 변경
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
