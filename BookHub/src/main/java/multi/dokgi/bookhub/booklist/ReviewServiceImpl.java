package multi.dokgi.bookhub.booklist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import multi.dokgi.bookhub.booklist.dao.ReviewDAO;
import multi.dokgi.bookhub.booklist.dto.ReviewDTO;
import multi.dokgi.bookhub.booklist.dto.ReviewJoinDTO;

@Service("reviewservice")
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	@Qualifier("reviewdao")
	ReviewDAO dao;

	@Override
	public List<ReviewJoinDTO> getReviewList(String isbn) {
		return dao.getReviewList(isbn);
	}

	@Override
	public int userbookExist(String loginId, String isbn) {
		return dao.userbookExist(loginId, isbn);
	}

	@Override
	public int getProgress(String loginId, String isbn, int bookEndpage) {
		int progress;
		//현재 읽은 제일 마지막 페이지
		int nowPage = dao.getProgress(loginId, isbn);
		//독서 진행도 계산
		progress =(int)((double)nowPage / (double)bookEndpage * 100);		
		//System.out.println(" sevice progress===" + progress);
		return progress;
	}
	
	@Override
	public int readComplete(String loginId, String isbn) {
		return dao.readComplete(loginId, isbn);
	}

	@Override
	public int reviewExist(String loginId, String isbn) {
		return dao.reviewExist(loginId, isbn);
	}

	@Override
	public int reviewInsert(ReviewDTO dto) {
		return dao.reviewInsert(dto);
	}

	@Override
	public int reviewUpdate(ReviewDTO dto) {
		return dao.reviewUpdate(dto);
	}

	@Override
	public int reviewDelete(int reviewNum) {
		return dao.reviewDelete(reviewNum);
	}

}
