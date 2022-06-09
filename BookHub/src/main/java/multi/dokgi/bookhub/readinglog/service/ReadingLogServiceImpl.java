package multi.dokgi.bookhub.readinglog.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import multi.dokgi.bookhub.readinglog.dao.IReadingLogDAO;
import multi.dokgi.bookhub.readinglog.dto.ReadingCalendarDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingReviewDTO;
import multi.dokgi.bookhub.readinglog.dto.ReadingStreakDTO;

/**
 * @author GhostFairy
 *
 */
@Service("ReadingLogService")
public class ReadingLogServiceImpl implements IReadingLogService {

	@Value("${ttbkey.GhostFairy}")
	private String TTBKey;

	private IReadingLogDAO rlDAO;

	// 생성자 주입
	public ReadingLogServiceImpl(IReadingLogDAO rlDAO) {
		this.rlDAO = rlDAO;
	}

	// 인터파크 도서 API - ISBN으로 도서 정보 검색
	@Override
	public JSONObject getBookInfo(String isbn) {
		JSONObject out = null;

		try {
			String params = "TTBKey=" + TTBKey + "&ItemId=" + isbn
					+ "&ItemIdType=ISBN13&Cover=Big&Output=JS&Version=20131101";
			URL api = new URL("http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?" + params);
			HttpURLConnection conn = (HttpURLConnection) api.openConnection();
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				if (br != null) {
					StringBuffer sb = new StringBuffer();
					String newLine = null;
					while ((newLine = br.readLine()) != null) {
						sb.append(newLine);
					}
					out = new JSONObject(sb.toString()).getJSONArray("item").getJSONObject(0);
				}
			} else {
				System.out.println("[ReadingLogService]: 알라딘 도서 API가 오류코드를 반환함");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return out;
	}

	// 독서기록 조회
	@Override
	public List<ReadingLogDTO> getReadingLog(String userId, String bookISBN) {
		return rlDAO.getReadingLog(userId, bookISBN);
	}

	// 읽은 페이지 합계 조회
	@Override
	public Integer getReadingLogSum(String userId, String bookISBN) {
		return rlDAO.getReadingLogSum(userId, bookISBN);
	}

	// 독서활동 기록
	@Override
	public int writeReadingLog(String userId, String bookISBN, int readPage, String summary, String readDate,
			String readComplete) {
		// 입력된 연-월-일 문자열을 parse
		LocalDateTime readDateParsed = LocalDate.parse(readDate).atStartOfDay();

		// DTO 생성
		ReadingLogDTO rlDTO = new ReadingLogDTO(userId, bookISBN, readPage, summary, readDateParsed);
		// DB에 입력
		int result = rlDAO.writeReadingLog(rlDTO);

		if (readComplete.equals("true")) {
			rlDAO.checkReadComplete(userId, bookISBN);
		}

		// 입력 결과 반환(1행 입력이므로 정상 결과값 1)
		return result;
	}

	@Override
	public boolean deleteReadingLog(String userId, String bookISBN, Integer num) {
		if (num == null || bookISBN == null || bookISBN.equals("")) {
			return false;
		} else {
			rlDAO.deleteReadingLog(userId, num);
			rlDAO.uncheckReadComplete(userId, bookISBN);
			return true;
		}
	}

	@Override
	public boolean deleteAllReadingLog(String userId, String bookISBN) {
		if (bookISBN == null || bookISBN.equals("")) {
			return false;
		} else {
			rlDAO.deleteAllReadingLog(userId, bookISBN);
			return true;
		}

	}

	// 최근에 읽은 책 조회
	@Override
	public List<ReadingLogDTO> getRecentBook(String userId) {
		// index 0부터 3개까지 조회
		return rlDAO.getRecentBook(userId, 0, 3);
	}

	// 최근 작성한 리뷰 조회
	@Override
	public List<ReadingReviewDTO> getRecentReview(String userId) {
		return rlDAO.getRecentReview(userId, 0, 3);
	}

	// 최근 독서활동 조회
	@Override
	public List<ReadingCalendarDTO> getRecentCalendar(String userId) {
		return rlDAO.getRecentCalendar(userId);
	}

	// 누적 독서 페이지 조회
	@Override
	public Integer getAccReadPages(String userId) {
		return rlDAO.getAccReadPages(userId);
	}

	// 연속 독서일 조회
	@Override
	public Map<String, ReadingStreakDTO> getStreak(String userId) {
		List<ReadingStreakDTO> streak = rlDAO.getStreak(userId);
		Map<String, ReadingStreakDTO> streakMap = new HashMap<String, ReadingStreakDTO>();

		ReadingStreakDTO max = null;
		ReadingStreakDTO current = null;

		for (ReadingStreakDTO dto : streak) {
			if (max == null) {
				max = dto;
			} else if (dto.getStreakCount() > max.getStreakCount()) {
				max = dto;
			}

			if (LocalDate.now().compareTo(dto.getStreakEndDate().toLocalDate()) <= 0
					&& LocalDate.now().compareTo(dto.getStreakStartDate().toLocalDate()) >= 0) {
				current = dto;
			}
		}

		streakMap.put("max", max);
		streakMap.put("current", current);

		return streakMap;
	}

	// 내 서재 조회
	@Override
	public List<ReadingLogDTO> getLibrary(String userId, Integer page) {
		// 페이지 당 20개씩 조회
		if (page == null || page < 1) {
			page = 1;
		}
		return rlDAO.getRecentBook(userId, (page - 1) * 20, 20);
	}

	// 내 서재 검색
	@Override
	public List<ReadingLogDTO> searchLibrary(String userId) {
		return rlDAO.getRecentBook(userId, 0, 1000);
	}

}
