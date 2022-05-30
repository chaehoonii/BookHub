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

	// 등록 순서
	private Integer mbNo;

	// 가입일자
	private LocalDateTime mbJoinDate;



}
