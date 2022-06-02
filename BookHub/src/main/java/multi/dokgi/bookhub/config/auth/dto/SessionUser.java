package multi.dokgi.bookhub.config.auth.dto;

import lombok.Getter;
import multi.dokgi.bookhub.user.dto.Role;
import multi.dokgi.bookhub.user.dto.UserDTO;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
	private String userId;
	private String userNick;
	private String userEmail;
	private String userThumbnail;
	private Role userRole;

	public SessionUser(UserDTO user) {
		this.userId = user.getUserId();
		this.userNick = user.getUserNick();
		this.userEmail = user.getUserEmail();
		this.userThumbnail = user.getUserThumbnail();
		this.userRole = user.getUserRole();
	}
}
