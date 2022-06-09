package multi.dokgi.bookhub.readinglog.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import multi.dokgi.bookhub.readinglog.dto.ReadingCalendarDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingReviewDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingStreakDTO;

/**
 * @author GhostFairy
 *
 */
public interface IReadingLogService {

	public JSONObject getBookInfo(String isbn);

	public List<ReadingLogDTO> getReadingLog(String userId, String bookISBN);

	public Integer getReadingLogSum(String userId, String bookISBN);

	public int writeReadingLog(String userId, String bookISBN, int readPage, String summary, String readDate,
			String readComplete);

	public boolean deleteReadingLog(String userId, String bookISBN, Integer num);

	public boolean deleteAllReadingLog(String userId, String bookISBN);

	public List<ReadingLogDTO> getRecentBook(String userId);

	public List<ReadingReviewDTO> getRecentReview(String userId);

	public List<ReadingCalendarDTO> getRecentCalendar(String userId);

	public List<ReadingLogDTO> getLibrary(String userId, Integer page);

	public List<ReadingLogDTO> searchLibrary(String userId);

	public Integer getAccReadPages(String userId);

	public Map<String, ReadingStreakDTO> getStreak(String userId);

}
