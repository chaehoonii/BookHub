package multi.dokgi.bookhub.readinglog.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import multi.dokgi.bookhub.readinglog.dao.IReadingLogDAO;
import multi.dokgi.bookhub.readinglog.dto.ReadingLogDTO;

/**
 * @author GhostFairy
 *
 */
@Service("ReadingLogService")
public class ReadingLogServiceImpl implements IReadingLogService {

	IReadingLogDAO rlDAO;

	// 생성자 주입
	public ReadingLogServiceImpl(IReadingLogDAO rlDAO) {
		this.rlDAO = rlDAO;
	}

	// 인터파크 도서 API - ISBN으로 도서 정보 검색
	@Override
	public JSONObject getBookInfo(String isbn) {
		String key = "CF7658C8375A9D83BC630FF2ED6A7BF592604291F66C55444E6C0402DF766DA8";
		String result = null;

		try {
			String params = "key=" + key + "&query=" + isbn + "&queryType=isbn&output=json";
			URL api = new URL("http://book.interpark.com/api/search.api?" + params);
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
					result = sb.toString();
				}
			} else {
				System.out.println("[ReadingLogService]: 인터파크 도서 API가 오류코드를 반환함");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result != null) {
			JSONObject out = new JSONObject(((JSONArray) new JSONObject(result).get("item")).get(0).toString());
			return out;
		}

		return null;
	}

	// 독서활동 기록하기
	@Override
	public int writeReadingLog(String userId, String bookISBN, int startPage, int endPage, String summary,
			String readDate, String readComplete) {
		// 입력된 연-월-일 문자열을 parse
		LocalDateTime readDateParsed = LocalDate.parse(readDate).atStartOfDay();
		// 완독여부 체크박스가 체크되지 않았으면 null이므로 false, 체크되어 null이 아니면 true
		boolean readCompleteParsed = false;
		if (readComplete != null) {
			readCompleteParsed = true;
		}

		// DTO 생성
		ReadingLogDTO rlDTO = new ReadingLogDTO(userId, bookISBN, startPage, endPage, summary, readDateParsed,
				readCompleteParsed);
		// DB에 입력
		int result = rlDAO.writeReadingLog(rlDTO);

		// 입력 결과 반환(1행 입력이므로 정상 결과값 1)
		return result;
	}

	// 최근 독서활동 조회
	@Override
	public List<ReadingLogDTO> getRecentReadingLog(String userId) {
		return rlDAO.getRecentReadingLog(userId);
	}

}
