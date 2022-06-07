package multi.dokgi.bookhub.booklist;

import java.util.List;

import multi.dokgi.bookhub.booklist.dto.ReviewJoinDTO;

public interface ReviewService {

	//리뷰 내역
	public List<ReviewJoinDTO> getReviewList(String isbn);
	//로그인한 아이디 user_book 테이블에 책 등록 되어있는지 조회
	public int userbookExist(String loginId);
}
