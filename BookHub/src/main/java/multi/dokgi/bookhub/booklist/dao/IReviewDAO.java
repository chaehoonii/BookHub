package multi.dokgi.bookhub.booklist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import multi.dokgi.bookhub.booklist.dto.ReviewDTO;
import multi.dokgi.bookhub.booklist.dto.ReviewJoinDTO;


@Mapper
@Repository("reviewdao")
public interface IReviewDAO {
	//리뷰 내역
	public List<ReviewJoinDTO> getReviewList(String isbn);
	
	//로그인한 아이디 reeadinglog에 책 등록 되어있는지 조회 
	public int userbookExist(String loginId, String isbn);
	//로그인한 아이디 독서 진행도
	public int getProgress(String loginId, String isbn);	
	//독서 완독했는지 조회
	public int readComplete(String loginId, String isbn);
	
	//로그인한 아이디 review 테이블에 리뷰 등록했는지 조회
	public int reviewExist(String loginId, String isbn);
	
	//리뷰 등록
	public int reviewInsert(ReviewDTO dto);
	//리뷰 수정
	public int reviewUpdate(ReviewDTO dto);
	//리뷰 삭제
	public int reviewDelete(int reviewNum);
}
