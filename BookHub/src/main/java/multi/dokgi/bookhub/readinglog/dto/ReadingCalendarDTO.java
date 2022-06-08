package multi.dokgi.bookhub.readinglog.dto;

import java.time.LocalDateTime;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */

@Getter
public class ReadingCalendarDTO {

	private LocalDateTime readDate; // 독서활동일
	private int readPage; // 독서 페이지

}
