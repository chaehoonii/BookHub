package multi.dokgi.bookhub.readinglog.dto;

import java.time.LocalDateTime;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */

@Getter
public class ReadingReviewDTO {

	private int reviewNum;
	private String userId;
	private String bookISBN;
	private String reviewContent;
	private LocalDateTime reviewWritedate;

}
