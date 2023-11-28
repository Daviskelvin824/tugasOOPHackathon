import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	ArrayList<Team> teamList = new ArrayList<>();
	ArrayList<User> userList = new ArrayList<>();
	UserRepository ur = new UserRepository();
	TeamRepository tr = new TeamRepository();
	Connection con = new Connection();
	Scanner sc = new Scanner(System.in);

	public void cls() {
		for (int i = 0; i < 50; i++) {
			System.out.println("");
		}
	}

	public void insert() {
		cls();
		System.out.println("Which table to insert? 1. User 2. Team");
		int t;
		while (true) {
			t = sc.nextInt();
			sc.nextLine();
			if (t == 1 || t == 2) {
				break;
			}
		}
		String name;
		String team;
		String nim;
		int id = 0;
		if (t == 1) {
			System.out.println("add name: ");
			name = sc.nextLine();
			System.out.println("add nim: ");
			nim = sc.nextLine();
			System.out.println("which team?: ");
			team = sc.nextLine();
			for (int i = 0; i < teamList.size(); i++) {
				if (teamList.get(i).name.equals(team)) {
					if (teamList.get(i).users.size() >= 3) {
						System.out.println("Error: Team is Full\nPress Enter to continue..");
						sc.nextLine();
						menu();
					} else {
						id = teamList.get(i).id;
						break;
					}
				}
				if (i + 1 == teamList.size()) {
					System.out.println("Error: Team not Found\nPress Enter to continue..");
					sc.nextLine();
					menu();
				}
			}
			System.out.println("User Created!");
			userList.add(new User(name, id, nim));
			con.appendUser(new User(name, id, nim));
			System.out.println("Press Enter to continue..");
			sc.nextLine();
			menu();
		} else {
			System.out.println("add name: ");
			name = sc.nextLine();
			System.out.println("add id: ");
			id = sc.nextInt();
			sc.nextLine();
			teamList.add(new Team(name, id));
			con.appendTeam(new Team(name, id));
			System.out.println("Team Created!\nPress Enter to continue..");
			sc.nextLine();
			menu();
		}
	}

	public void show() {
		cls();
		System.out.println("Which table to show? 1. User 2. Team");
		int t = sc.nextInt();
		sc.nextLine();
		System.out.println("Want to filter by condition? 1. Yes 2. No");
		int ask = sc.nextInt();
		sc.nextLine();
		if (ask == 2) {
			if (t == 1) {
				ur.find(null, null, null, false, con);
			} else {
				tr.find(null, null, null, false, con);
			}
			System.out.println("Press Enter to continue..");
			sc.nextLine();
			menu();
		} else {
			System.out.println("Add condition, separate conditions with semicolon. (Example: name;=;Joel. "
					+ "\n\nWrite it in this order: filter; operator; what entity to be filtered; true/false for join table; "
					+ "\n\ntable name if true. Write null if you don't want to insert anything on that. )");
			String input = sc.nextLine();
			if (t == 1) {
				ur.find(input.split(";")[0], input.split(";")[1], input.split(";")[2],
						Boolean.parseBoolean(input.split(";")[3]), con);
			} else {
				tr.find(input.split(";")[0], input.split(";")[1], input.split(";")[2],
						Boolean.parseBoolean(input.split(";")[3]), con);
			}
			System.out.println("Press Enter to continue..");
			sc.nextLine();
			menu();
		}
	}

	public void menu() {
		cls();
		System.out.println("Welcome\n1. Insert\n2. Show");
		int t = 0;
		try {
			t = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		switch (t) {
		case 1:
			insert();
			break;
		case 2:
			show();
			break;
		default:
			menu();
		}

	}

	public Main() {
		// TODO Auto-generated constructor stub
		userList = con.readUserFile();
		teamList = con.readTeamFile(userList);
		menu();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
