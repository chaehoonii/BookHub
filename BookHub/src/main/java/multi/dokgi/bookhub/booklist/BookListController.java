package multi.dokgi.bookhub.booklist;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import multi.dokgi.bookhub.booklist.dto.BookListDTO;
import multi.dokgi.bookhub.booklist.dto.CategoryDTO;

@Controller
public class BookListController {

	@Autowired
	@Qualifier("booklistservice")
	BookListService service;

	
	@SuppressWarnings("unchecked")
	@RequestMapping("/booklist")
	public ModelAndView itemList(){		
		
		Map<String,Object> result =  service.itemList("1");
		
		List<BookListDTO> booklist = (List<BookListDTO>) result.get("booklist");		
		int totalResults = (int) result.get("totalResults");//총 결과 수
		
		System.out.println("totalResults ===>" + totalResults);		
		
		//검색대상(mall)별 카테고리 조회 default "국내도서"
		List<CategoryDTO> catagorylist = service.getCategoryList("국내도서");
		for(int i = 0; i < catagorylist.size(); i++) {
			System.out.println("cid : " + catagorylist.get(i).getCid() + " /// categoryname : "+ catagorylist.get(i).getCategoryName());	
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("booklist", booklist);
		mv.addObject("catagory", catagorylist);
		mv.setViewName("booklist/booklist");
		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/booksearch")
	public ModelAndView itemSearch(){		
		
		Map<String,Object> result =  service.itemSearch("Keyword", "아몬드", "1");
		
		List<BookListDTO> booklist = (List<BookListDTO>) result.get("booklist");		
		int totalResults = (int) result.get("totalResults");//총 결과 수
		
		System.out.println("totalResults ===>" + totalResults);		
		ModelAndView mv = new ModelAndView();
		mv.addObject("booklist", booklist);
		mv.setViewName("booklist/booklist");
		return mv;
	}
	
	@RequestMapping("/bookdetail")
	public ModelAndView itemLookUp(){		
		
		BookListDTO bookdetail =  service.itemLookUp("9788936456788");
		System.out.println("endpage"+bookdetail.getBookEndpage());
			
		ModelAndView mv = new ModelAndView();
		mv.addObject("bookdetail", bookdetail);
		mv.setViewName("booklist/bookdetail");
		return mv;
	}
	
	@PostMapping("/getcategory")
	@ResponseBody
	public List<CategoryDTO> category(String mall) {
		//검색대상(mall)별 카테고리 조회
		List<CategoryDTO> catagorylist = service.getCategoryList(mall);
		for(int i = 0; i < catagorylist.size(); i++) {
			System.out.println("cid : " + catagorylist.get(i).getCid() + " /// categoryname : "+ catagorylist.get(i).getCategoryName());	
		}		
		
		return catagorylist;
	}
}

