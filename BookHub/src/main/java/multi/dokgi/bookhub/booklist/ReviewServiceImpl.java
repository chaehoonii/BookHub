package multi.dokgi.bookhub.booklist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import multi.dokgi.bookhub.booklist.dao.ReviewDAO;
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
	public int userbookExist(String loginId) {
		return dao.userbookExist(loginId);
	}

}
