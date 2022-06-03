package multi.dokgi.bookhub.config.auth.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import multi.dokgi.bookhub.user.dto.Role;
import multi.dokgi.bookhub.user.dto.UserDTO;

/**
 * @author Seongil, Yoon
 *
 */
@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	private String nameAttributeKey;
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
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, Integer userNo, String userId,
			String userProvider, String userPw, String userNick, String userEmail, String userTel, Boolean userPrivate,
			String userThumbnail, LocalDateTime userRegdate, Role userRole) {
		super();
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.userNo = userNo;
		this.userId = userId;
		this.userProvider = userProvider;
		this.userPw = userPw;
		this.userNick = userNick;
		this.userEmail = userEmail;
		this.userTel = userTel;
		this.userPrivate = userPrivate;
		this.userThumbnail = userThumbnail;
		this.userRegdate = userRegdate;
		this.userRole = userRole;
	}

	public static OAuthAttributes of(String mbProvider, String userNameAttributeName, Map<String, Object> attributes) {
		if (mbProvider.equals("google")) {
			return ofGoogle(userNameAttributeName, attributes);
		} else if (mbProvider.equals("naver")) {
			return ofNaver(userNameAttributeName, attributes);
		} else if (mbProvider.equals("kakao")) {
			return ofKakao(userNameAttributeName, attributes);
		} else {
			System.out.println("지원하지 않는 OAuth provider입니다.");
			return null;
		}
	}
	
	 private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
	        return OAuthAttributes.builder()
	            	.userId ("google_"+(String) attributes.get("sub"))
	            	.userProvider ("google") 
	            	.userNick ((String) attributes.get("name"))
	            	.userEmail ((String) attributes.get("email")) 
	            	.userThumbnail((String) attributes.get("picture"))
	                .userRole(Role.UN_USER) 
	                .attributes(attributes)
	                .nameAttributeKey(userNameAttributeName)
	                .build();
	}
	 
	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder().build();
	}
	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder().build();
	}

	public UserDTO toEntity() {
		return UserDTO.builder()
				.userId(userId)
				.userProvider(userProvider)
				.userNick(userNick)
				.userEmail(userEmail)
				.userThumbnail(userThumbnail)
				.userRole(userRole)
				.build();
	}

}
