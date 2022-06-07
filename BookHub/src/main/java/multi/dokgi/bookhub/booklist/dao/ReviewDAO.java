package multi.dokgi.bookhub.booklist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import multi.dokgi.bookhub.booklist.dto.ReviewJoinDTO;


@Mapper
@Repository("reviewdao")
public interface ReviewDAO {
	//리뷰 내역
	public List<ReviewJoinDTO> getReviewList(String isbn);
	//로그인한 아이디 user_book 테이블에 책 등록 되어있는지 조회
	public int userbookExist(String loginId);
}
