
public class User extends Model {
	String nim;

	public User(String name, int id, String nim) {
		super(name, id);
		this.nim = nim;
	}

}
