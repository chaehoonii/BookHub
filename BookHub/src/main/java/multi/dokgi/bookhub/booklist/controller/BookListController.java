package multi.dokgi.bookhub.booklist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import multi.dokgi.bookhub.booklist.dto.BookListDTO;
import multi.dokgi.bookhub.booklist.dto.BookListPageDTO;
import multi.dokgi.bookhub.booklist.dto.CategoryDTO;
import multi.dokgi.bookhub.booklist.dto.ReviewDTO;
import multi.dokgi.bookhub.booklist.dto.ReviewJoinDTO;
import multi.dokgi.bookhub.booklist.service.IBookListService;
import multi.dokgi.bookhub.booklist.service.IReviewService;
import multi.dokgi.bookhub.config.auth.LoginUser;
import multi.dokgi.bookhub.config.auth.dto.SessionUser;

@Controller
public class BookListController {

	@Autowired
	@Qualifier("booklistservice")
	IBookListService bookservice;
	
	@Autowired
	@Qualifier("reviewservice")
	IReviewService reviewservice;

	//상품 리스트 API - 베스트셀러
	@SuppressWarnings("unchecked")
	@RequestMapping("/booklist")
	public ModelAndView itemList(@RequestParam(defaultValue = "국내도서") @Nullable String mall, @RequestParam(defaultValue = "Book") @Nullable String SearchTarget, 
			@RequestParam(defaultValue = "1") @Nullable String start, @RequestParam(defaultValue = "0") @Nullable String CategoryId){		
		
		Map<String,Object> result =  bookservice.itemList(SearchTarget, start, CategoryId);
		
		List<BookListDTO> booklist = (List<BookListDTO>) result.get("booklist");		
		int totalResults = (int) result.get("totalResults");//총 결과 수
		
		System.out.println("totalResults ===>" + totalResults);		
		
		//검색대상(mall)별 카테고리 조회
		List<CategoryDTO> catagorylist = bookservice.getCategoryList(mall);
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
		
		Map<String,Object> result =  bookservice.itemSearch(searchWord, queryType, SearchTarget, start, CategoryId);
		
		List<BookListDTO> booklist = (List<BookListDTO>) result.get("booklist");		
		
		int totalResults = (int) result.get("totalResults");//총 결과 수
		System.out.println("totalResults ===>" + totalResults);			
		//페이징
		BookListPageDTO pagedto = new BookListPageDTO(Integer.parseInt(start), 10, 5, totalResults);
		
		//검색대상(mall)별 카테고리 조회
		List<CategoryDTO> catagorylist = bookservice.getCategoryList(mall);
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
	public ModelAndView itemLookUp(String isbn, @LoginUser SessionUser user){	
		ModelAndView mv = new ModelAndView();
		//책 상세정보		
		BookListDTO bookdetail =  bookservice.itemLookUp(isbn);
		
		//책 리뷰 조회
		List<ReviewJoinDTO> reviewlist = reviewservice.getReviewList(isbn);
		//리뷰 수 
		int reviewCount = reviewlist.size();
		
		int userbookExist = -1; //로그인 안된 경우
		int progress = 0; //독서 진행도
		int reviewExist = -1; //로그인 안된 경우
				
		//로그인한 아이디 reeadinglog에 책 등록 되어있는지 조회 
		if (user != null) {
			
			//userbookExist = 0 => 책 등록 안된 경우
			userbookExist = reviewservice.userbookExist(user.getUserId(), isbn);
			
			//등록되어있으면 진행도 확인, 완독이면 리뷰 등록했는지 확인 후 리뷰 등록
			//read_complete = 1이면 완독
			if(userbookExist != 0) {				
				progress = reviewservice.getProgress(user.getUserId(), isbn, bookdetail.getBookEndpage());
				//책 완독했는지 조회
				int readComplete = reviewservice.readComplete(user.getUserId(), isbn);
				
				if(readComplete >= 1) {
					reviewExist = reviewservice.reviewExist(user.getUserId(), isbn); //0이면 리뷰 없음		 			
				}
			}			
		}
		
		mv.addObject("bookdetail", bookdetail);
		mv.addObject("reviewCount", reviewCount);
		mv.addObject("reviewlist", reviewlist);
		mv.addObject("userbookExist", userbookExist);
		mv.addObject("progress", progress);
		mv.addObject("reviewExist", reviewExist);
		mv.setViewName("booklist/bookdetail");
		return mv;
	}
	
	//리뷰 등록하기
	@ResponseBody
	@PostMapping("/reviewinsert")
	public int bookReviewInsert(ReviewDTO dto) {
		int result = reviewservice.reviewInsert(dto);
		return result;
	}
	//리뷰 수정하기
	@ResponseBody
	@PostMapping("/reviewedit")
	public int bookReviewEdit(ReviewDTO dto) {
		int result = reviewservice.reviewUpdate(dto);
		return result;
	}
	//리뷰 삭제하기
	@ResponseBody
	@PostMapping("/reviewdelete")
	public int bookReviewDelete(int reviewNum) {
		int result = reviewservice.reviewDelete(reviewNum);		
		return result;
	}

}

