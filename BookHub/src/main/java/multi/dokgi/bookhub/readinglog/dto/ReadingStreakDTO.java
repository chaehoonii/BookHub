package multi.dokgi.bookhub.readinglog.dto;

import java.time.LocalDateTime;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */

@Getter
public class ReadingStreakDTO {

	private LocalDateTime streakStartDate;
	private LocalDateTime streakEndDate;
	private int streakCount;

}
