
public abstract class Repository {
	abstract void find(String filter, String operator, String input, boolean join, Connection con);
}
