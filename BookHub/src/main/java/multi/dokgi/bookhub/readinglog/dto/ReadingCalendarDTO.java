package multi.dokgi.bookhub.readinglog.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author GhostFairy
 *
 */

@Getter
@AllArgsConstructor
public class ReadingCalendarDTO {

	private final LocalDateTime readDate; // 독서활동일
	private final int readPage; // 독서 페이지

}
