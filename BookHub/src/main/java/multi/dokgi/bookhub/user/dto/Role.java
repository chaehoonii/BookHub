package multi.dokgi.bookhub.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Seongil, Yoon
 *
 */
@Getter
@RequiredArgsConstructor
public enum Role {

	UN_USER("ROLE_uncerti_USER", "미인증 사용자"), 
	USER("ROLE_USER", "사용자"),

	// 관리자
	ADMIN("ROLE_ADMIN", "관리자");

	private final String key;
	private final String title;
}