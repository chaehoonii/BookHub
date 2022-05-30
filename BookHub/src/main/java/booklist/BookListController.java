package booklist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BookListController {

	@RequestMapping("/booklist")
	public ModelAndView productlist() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("booklist");
		return mv;
	}
}
