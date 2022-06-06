package multi.dokgi.bookhub.readinglog.service;

import java.util.List;

import org.json.JSONObject;

import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;

/**
 * @author GhostFairy
 *
 */
public interface IReadingLogService {

	public JSONObject getBookInfo(String isbn);

	public int writeReadingLog(String userId, String bookISBN, int endPage, String summary,
			String readDate, String readComplete);

	public List<ReadingLogDTO> getRecentReadingLog(String userId);

}
