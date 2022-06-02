package multi.dokgi.bookhub.config.auth.dto;

import lombok.Getter;
import multi.dokgi.bookhub.user.dto.Role;
import multi.dokgi.bookhub.user.dto.UserDTO;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
	private String id;
	private String name;
	private String email;
	private String picture;
	private Role role;

	public SessionUser(UserDTO user) {
		this.id = user.getUserId();
		this.name = user.getUserNick();
		this.email = user.getUserEmail();
		this.picture = user.getUserThumbnail();
		this.role = user.getUserRole();
	}
}
