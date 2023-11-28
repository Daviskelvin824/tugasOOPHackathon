import java.util.ArrayList;

public class Team extends Model {
	ArrayList<User> users;

	public Team(String name, int id) {
		super(name, id);
		users = new ArrayList<>();
	}

}
