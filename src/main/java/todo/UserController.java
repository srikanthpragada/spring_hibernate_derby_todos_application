package todo;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private TodoRepo todoRepo;

	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

	@RequestMapping(value = "login")
	public String login(ModelMap model) {
		User u = new User();
		model.addAttribute("user", u);
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(ModelMap model, User u, HttpSession session) {
		// check whether user is present in Users table
		Optional<User> user = userRepo.findById(u.getUsername());
		if (user.isPresent() && user.get().getPassword().equals(u.getPassword())) {
			session.setAttribute("username", u.getUsername());
			return "redirect:home";

		} else {

			model.addAttribute("user", u);
			model.addAttribute("message", "Login Failed!");
			return "login";
		}
	}

	@RequestMapping(value = "register")
	public String register(ModelMap model) {
		User u = new User();
		model.addAttribute("user", u);
		return "register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(ModelMap model, User u) {
		// Insert user details into table
		try {
			userRepo.save(u);
			return "redirect:login";
		} catch (Exception ex) {
			System.out.println(ex);
			model.addAttribute("user", u);
			model.addAttribute("message", "Sorry! Registration Failed. Please try again!");
			return "register";
		}
	}

	@RequestMapping(value = "home")
	public String home(ModelMap model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		List<Todo> todos = todoRepo.getRecentTodosByUser(username);
		// Take only first 5 todos from the list
		model.addAttribute("todos", todos.stream().limit(5).toArray());
		return "home";
	}

}
