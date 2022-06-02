package multi.dokgi.bookhub.booklist;

import java.util.List;
import java.util.Map;

public interface BookListService {
	public Map<String,Object> itemList(String start); //상품 리스트 API
	public Map<String,Object> itemSearch(String searchType, String searchWord, String start); //상품 검색 API
	public BookListDTO itemLookUp(String isbn); //상품 조회 API
}
