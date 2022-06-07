package multi.dokgi.bookhub.booklist.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {
	private int reviewNum;
	private String userId;
	private String bookIsbn;
	private String reviewContent;
	private Date reviewWritedate;
}
