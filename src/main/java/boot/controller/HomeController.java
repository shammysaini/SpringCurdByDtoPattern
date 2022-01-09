package boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import boot.controller.dto.UserRequest;
import boot.service.UserService;
import boot.util.ImageUtil;

@Controller

public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping("/signup")
	public String signup(Model model) {
		List<String> country = null;
		country = new ArrayList<>();
		country.add("india");
		country.add("srilanka");
		country.add("pakistan");

		// List<String> language = Arrays.asList("English","Hindi","punjabi");
		// model.addAttribute("languageList",language);
		model.addAttribute("countryList", country);
		model.addAttribute("userRequest", new UserRequest());

		return "views/signupForm";
	}

	@PostMapping("/saveUser")
	public String saveUser(Model model, UserRequest userRequest) {

		String response = userService.saveUser(userRequest);
		
		if (response == "data has been saved successfull") {
			model.addAttribute("message", "data has been saved successfull");
			return "views/signupForm";
		} else {
			model.addAttribute("message", "unable to saved Record");
			return "views/error";
		}

	}
	
	@GetMapping("/user")
	public String getAll(Model model,UserRequest userRequest)
	{
		List<UserRequest>userRequestList  = userService.getUser();
		model.addAttribute("userList", userRequestList);
		model.addAttribute("path",userRequest.getImage());
		return "views/user";
		
		
	}

}
