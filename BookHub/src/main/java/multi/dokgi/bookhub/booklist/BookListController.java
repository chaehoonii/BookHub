package multi.dokgi.bookhub.booklist;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		ModelAndView mv = new ModelAndView();
		mv.addObject("booklist", booklist);
		mv.setViewName("booklist");
		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/booksearch")
	public ModelAndView itemSearch(){		
		
		Map<String,Object> result =  service.itemSearch("Title", "아몬드", "1");
		
		List<BookListDTO> booklist = (List<BookListDTO>) result.get("booklist");		
		int totalResults = (int) result.get("totalResults");//총 결과 수
		
		System.out.println("totalResults ===>" + totalResults);		
		ModelAndView mv = new ModelAndView();
		mv.addObject("booklist", booklist);
		mv.setViewName("booklist");
		return mv;
	}
	
	@RequestMapping("/bookdetail")
	public ModelAndView itemLookUp(){		
		
		BookListDTO bookdetail =  service.itemLookUp("9788936456788");
		System.out.println("endpage"+bookdetail.bookEndpage);
			
		ModelAndView mv = new ModelAndView();
		mv.addObject("bookdetail", bookdetail);
		mv.setViewName("bookdetail");
		return mv;
	}
	
	public CategoryDTO category() {
		CategoryDTO category = null;
		return category;
	}
}

