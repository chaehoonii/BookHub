package multi.dokgi.bookhub.readinglog.service;

import java.util.List;

import org.json.JSONObject;

import multi.dokgi.bookhub.readinglog.dto.ReadingCalendarDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;

/**
 * @author GhostFairy
 *
 */
public interface IReadingLogService {

	public JSONObject getBookInfo(String isbn);

	public int writeReadingLog(String userId, String bookISBN, int readPage, String summary, String readDate,
			String readComplete);

	public List<ReadingLogDTO> getRecentBook(String userId);

	public List<ReadingCalendarDTO> getRecentCalendar(String userId);

	public List<ReadingLogDTO> getLibrary(String userId, Integer page);

	public List<ReadingLogDTO> searchLibrary(String userId);
	
	public int getAccReadPages(String userId);

}
