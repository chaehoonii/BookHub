package multi.dokgi.bookhub.readinglog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.dokgi.bookhub.readinglog.dto.ReadingCalendarDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IReadingLogDAO {

	public int writeReadingLog(ReadingLogDTO rlDTO);

	public List<ReadingLogDTO> getRecentReadingLog(String userId);

	public List<ReadingCalendarDTO> getRecentReadingCalendar(String userId);

}