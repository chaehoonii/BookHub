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
	private final int readPage; // 읽은 페이지
	private final String summary; // 독서활동 요약
	private final LocalDateTime readDate; // 읽은 날짜
	private LocalDateTime writeDate; // 기록한 날짜
	private boolean readComplete; // 완독여부

}
