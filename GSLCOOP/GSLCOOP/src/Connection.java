import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Connection {

	FileReader userFile;
	FileReader teamFile;
	Connection con;

	public ArrayList<User> readUserFile() {
		try {
			userFile = new FileReader("user.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner uf = new Scanner(userFile);
		String garbage = uf.nextLine();
		ArrayList<User> data = new ArrayList<>();
		while (uf.hasNextLine()) {
			String temp = uf.nextLine();
			String nim = temp.split(",")[0];
			String name = temp.split(",")[1];
			int id = Integer.parseInt(temp.split(",")[2]);
			data.add(new User(name, id, nim));
		}
		uf.close();
		return data;
	}

	public ArrayList<Team> readTeamFile(ArrayList<User> userList) {
		try {
			teamFile = new FileReader("teams.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner tf = new Scanner(teamFile);
		ArrayList<Team> data = new ArrayList<>();
		String garbage = tf.nextLine();
		while (tf.hasNextLine()) {
			String temp = tf.nextLine();
			int id = Integer.parseInt(temp.split(",")[0]);
			String name = temp.split(",")[1];
			data.add(new Team(name, id));
		}
		for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < data.size(); j++) {
				if (data.get(j).id == userList.get(i).id) {
					data.get(j).users.add(userList.get(i));
				}
			}
		}
		tf.close();
		return data;
	}

	public void appendTeam(Team team) {
		try {
			FileWriter tw = new FileWriter("teams.csv", true);
			tw.write(team.id + "," + team.name + "\n");
			tw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void appendUser(User user) {
		try {
			FileWriter uw = new FileWriter("user.csv", true);
			uw.write(String.format("%s,%s,%d\n", user.nim, user.name, user.id));
			uw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection() {
		// TODO Auto-generated constructor stub
	}

	public Connection getInstance() {
		if (con == null) {
			con = new Connection();
		}
		return con;
	}

}
