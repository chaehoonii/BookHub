package multi.dokgi.bookhub.booklist;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import multi.dokgi.bookhub.booklist.dto.BookListDTO;
import multi.dokgi.bookhub.booklist.dto.BookListPageDTO;
import multi.dokgi.bookhub.booklist.dto.CategoryDTO;

@Controller
public class BookListController {

	@Autowired
	@Qualifier("booklistservice")
	BookListService service;

	//상품 리스트 API - 베스트셀러
	@SuppressWarnings("unchecked")
	@RequestMapping("/booklist")
	public ModelAndView itemList(@RequestParam(defaultValue = "국내도서") @Nullable String mall, @RequestParam(defaultValue = "Book") @Nullable String SearchTarget, 
			@RequestParam(defaultValue = "1") @Nullable String start, @RequestParam(defaultValue = "0") @Nullable String CategoryId){		
		
		Map<String,Object> result =  service.itemList(SearchTarget, start, CategoryId);
		
		List<BookListDTO> booklist = (List<BookListDTO>) result.get("booklist");		
		int totalResults = (int) result.get("totalResults");//총 결과 수
		
		System.out.println("totalResults ===>" + totalResults);		
		
		//검색대상(mall)별 카테고리 조회
		List<CategoryDTO> catagorylist = service.getCategoryList(mall);
		//CategoryId에 해당하는 카테고리 이름 조회
		String categoryName = "전체"; //default 0 = 전체
		for(int i = 0; i < catagorylist.size(); i++) {
			if(catagorylist.get(i).getCid() == Integer.parseInt(CategoryId)){
				categoryName = catagorylist.get(i).getCategoryName();
			}
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("categoryName", categoryName);
		mv.addObject("catagory", catagorylist);
		mv.addObject("booklist", booklist);
		mv.setViewName("booklist/booklist");
		return mv;
	}

	//상품 검색 API
	@SuppressWarnings("unchecked")
	@RequestMapping("/booksearch")
	public ModelAndView itemSearch(@RequestParam(defaultValue = "국내도서") @Nullable String mall, @RequestParam(defaultValue = "") @Nullable String searchWord,
			@RequestParam(defaultValue = "Keyword") @Nullable String queryType, @RequestParam(defaultValue = "Book") @Nullable String SearchTarget, 
			@RequestParam(defaultValue = "1") @Nullable String start, @RequestParam(defaultValue = "0") @Nullable String CategoryId){
		
		Map<String,Object> result =  service.itemSearch(searchWord, queryType, SearchTarget, start, CategoryId);
		
		List<BookListDTO> booklist = (List<BookListDTO>) result.get("booklist");		
		
		int totalResults = (int) result.get("totalResults");//총 결과 수
		System.out.println("totalResults ===>" + totalResults);			
		//페이징
		BookListPageDTO pagedto = new BookListPageDTO(Integer.parseInt(start), 10, 5, totalResults);
		
		//검색대상(mall)별 카테고리 조회
		List<CategoryDTO> catagorylist = service.getCategoryList(mall);
		//CategoryId에 해당하는 카테고리 이름 조회
		String categoryName = "전체"; //default 0 = 전체
		for(int i = 0; i < catagorylist.size(); i++) {
			if(catagorylist.get(i).getCid() == Integer.parseInt(CategoryId)){
				categoryName = catagorylist.get(i).getCategoryName();
			}
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("categoryName", categoryName);
		mv.addObject("catagory", catagorylist);
		mv.addObject("totalResults", totalResults);
		mv.addObject("booklist", booklist);
		mv.addObject("pagedto", pagedto);
		mv.setViewName("booklist/booksearch");
		return mv;
	}
	
	@RequestMapping("/bookdetail")
	public ModelAndView itemLookUp(String isbn){		
				
		BookListDTO bookdetail =  service.itemLookUp(isbn);
		System.out.println("endpage"+bookdetail.getBookEndpage());
		
		//리뷰 수 
		
		//책 리뷰 조회
		
		//로그인 아이디 책 등록 되어있는지 조회
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bookdetail", bookdetail);
		mv.setViewName("booklist/bookdetail");
		return mv;
	}

}

