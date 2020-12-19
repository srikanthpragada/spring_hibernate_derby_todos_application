package todo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TodoController {
	@Autowired
	private TodoRepo todoRepo;

	@Autowired
	private UserRepo userRepo;

	@RequestMapping(value = "add")
	public String add(ModelMap model) {
		Todo todo = new Todo();
		model.addAttribute("todo", todo);
		return "add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(ModelMap model, Todo todo, HttpSession session) {
		// Insert Todo details into table
		try {
			// Set user to current user
			String username = (String) session.getAttribute("username");
			todo.setUsername(username);
			todoRepo.save(todo);
			return "redirect:/home";
		} catch (Exception ex) {
			System.out.println(ex);
			model.addAttribute("todo", todo);
			model.addAttribute("message", "Sorry! Could not add todo entry. Please try again!");
			return "add";
		}
	}

	@RequestMapping(value = "list")
	public String list(ModelMap model, HttpSession session) {
		// get current user
		String username = (String) session.getAttribute("username");
		User user = userRepo.findById(username).get();

		List<Todo> todos = user.getTodos();
		model.addAttribute("todos", todos);
		return "list";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(ModelMap model, @PathVariable("id") int id) {
		Todo todo = todoRepo.findById(id).get();
		todoRepo.delete(todo);
		return "redirect:/home";
	}

	@RequestMapping(value = "edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") int id) {
		Todo todo = todoRepo.findById(id).get();
		model.addAttribute("todo", todo);
		return "edit";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	public String edit(ModelMap model, Todo todo, @PathVariable("id") int id) {
		// Update todo details into table
		try {
			Todo dbTodo = todoRepo.findById(id).get();
			dbTodo.setText(todo.getText());
			dbTodo.setCategory(todo.getCategory());
			todoRepo.save(dbTodo);
			return "redirect:/home";
		} catch (Exception ex) {
			System.out.println(ex);
			model.addAttribute("todo", todo);
			model.addAttribute("message", "Sorry! Could not update todo entry. Please try again!");
			return "edit";
		}
	}

	@RequestMapping(value = "searchform")
	public String search() {
		return "search";
	}

	@RequestMapping(value = "search")
	public String search(String text, HttpSession session, ModelMap model) {
		System.out.println(text);
		List<Todo> todos = todoRepo.getTodosByText(session.getAttribute("username").toString(),
				"%" + text.toUpperCase() + "%");
		if (todos.size() > 0)
			model.addAttribute("todos", todos);
		else
			model.addAttribute("message", "Sorry! No todos found!");
		return "search";
	}

}
