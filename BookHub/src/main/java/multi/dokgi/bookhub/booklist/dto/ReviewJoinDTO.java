package multi.dokgi.bookhub.booklist.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewJoinDTO {
	private int reviewNum;
	private String userId;
	private String bookIsbn;
	private String reviewContent;
	private Date reviewWritedate;
	
	//user 정보
	private String userThumbnail;
	private String userNick;
}
