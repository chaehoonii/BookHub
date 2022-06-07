package multi.dokgi.bookhub.readinglog.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author GhostFairy
 *
 */

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReadingLogDTO {

	private int num; // 독서활동 기록번호
	private final String userId; // 회원 ID
	private final String bookISBN; // 책 ISBN
	private final int startPage; // 독서 종료페이지
	private final int endPage; // 독서 종료페이지
	private final String summary; // 독서활동 요약
	private final LocalDateTime readDate; // 독서활동일
	private LocalDateTime writeDate; // 독서활동 기록일
	private final boolean readComplete; // 완독여부

}
