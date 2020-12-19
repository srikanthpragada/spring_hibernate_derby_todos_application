package todo;

import java.util.List;
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo  extends CrudRepository<Todo,Integer> {

	 @Query("SELECT t FROM Todo t WHERE t.username = ?1")
	 List<Todo> getTodosByUser(String username);
	 
	 @Query("SELECT t FROM Todo t WHERE t.username = ?1 order by id desc")
	 List<Todo> getRecentTodosByUser(String username);
	 
	 @Query("SELECT t FROM Todo t WHERE t.username = ?1 and upper(t.text) like ?2 order by id desc")
	 List<Todo> getTodosByText(String username, String text);

}
