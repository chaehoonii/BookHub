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

	public int writeReadingLog(ReadingLogDTO rlDTO);

	public List<ReadingLogDTO> getRecentBook(String userId, int index, int limit);
	
	public List<ReadingReviewDTO> getRecentReview(String userId, int index, int limit);

	public List<ReadingCalendarDTO> getRecentCalendar(String userId);

	public Integer getAccReadPages(String userId);

	public List<ReadingStreakDTO> getStreak(String userid);

}