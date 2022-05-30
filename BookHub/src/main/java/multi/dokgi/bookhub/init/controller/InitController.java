package multi.dokgi.bookhub.init.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Seongil, Yoon
 */
@Controller
public class InitController {

	@GetMapping
	public String home() {
		return "home";
	}
}
