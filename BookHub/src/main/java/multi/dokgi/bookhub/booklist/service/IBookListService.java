package multi.dokgi.bookhub.booklist.service;

import java.util.List;
import java.util.Map;

import multi.dokgi.bookhub.booklist.dto.BookListDTO;
import multi.dokgi.bookhub.booklist.dto.CategoryDTO;

public interface IBookListService {
	//상품 리스트 API
	public Map<String,Object> itemList(String SearchTarget, String start, String CategoryId);
	//상품 검색 API
	public Map<String,Object> itemSearch(String searchWord, String queryType, String SearchTarget, String start, String CategoryId); 
	//상품 조회 API
	public BookListDTO itemLookUp(String isbn); 
	
	//검색대상(mall)별 카테고리 조회
	public List<CategoryDTO> getCategoryList(String mall);	

}
