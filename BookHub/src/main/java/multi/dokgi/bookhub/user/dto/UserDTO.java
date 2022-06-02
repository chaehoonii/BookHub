package multi.dokgi.bookhub.user.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Seongil, Yoon
 *
 */
@Data
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 전체 생성자
public class UserDTO {

	// 등록순서
	private Integer userNo;

	// 회원ID
	private String userId;

	// SNS사이트종류
	private String userProvider;

	// 비밀번호
	private String userPw;

	// 닉네임
	private String userNick;

	// 이메일
	private String userEmail;

	// 연락처
	private String userTel;

	// 1이면 캘린더공개
	private Boolean userPrivate;

	// 프로필사진
	private String userThumbnail;

	// 가입일
	private LocalDateTime userRegdate;

	// 회원권한
	private Role userRole;

	@Builder
	public UserDTO(String userId, String userProvider, String userNick, String userEmail, String userThumbnail,
			Role userRole) {
		super();
		this.userId = userId;
		this.userProvider = userProvider;
		this.userNick = userNick;
		this.userEmail = userEmail;
		this.userThumbnail = userThumbnail;
		this.userRole = userRole;
	}
	
	public String getRoleKey() {
		return this.userRole.getKey();
	}
}
