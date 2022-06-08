package multi.dokgi.bookhub.readinglog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.dokgi.bookhub.readinglog.dto.ReadingCalendarDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingReviewDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingStreakDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IReadingLogDAO {

	public List<ReadingLogDTO> getReadingLog(String userId, String bookISBN);
	
	public Integer getReadingLogSum(String userId, String bookISBN);

	public int writeReadingLog(ReadingLogDTO rlDTO);
	
	public int deleteReadingLog(String userId, int num);
	
	public int deleteAllReadingLog(String userId, String bookISBN);
	
	public int uncheckReadComplete(String userId, String bookISBN);

	public List<ReadingLogDTO> getRecentBook(String userId, int index, int limit);

	public List<ReadingReviewDTO> getRecentReview(String userId, int index, int limit);

	public List<ReadingCalendarDTO> getRecentCalendar(String userId);

	public Integer getAccReadPages(String userId);

	public List<ReadingStreakDTO> getStreak(String userid);

}