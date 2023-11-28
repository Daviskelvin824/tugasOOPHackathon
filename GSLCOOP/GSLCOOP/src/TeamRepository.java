import java.util.ArrayList;

public class TeamRepository extends Repository {

	public TeamRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void find(String filter, String operator, String input, boolean join, Connection con) {
		// TODO Auto-generated method stub
		ArrayList<User> userList;
		ArrayList<Team> teamList;
		con.getInstance();
		userList = con.readUserFile();
		teamList = con.readTeamFile(userList);
		if (filter == null || filter.equals("null")) {
			if (!join) {
				System.out.printf("|%-50s|%-3s|\n", "Name", "ID");
				for (int i = 0; i < teamList.size(); i++) {
					System.out.printf("|%-50s|%-3s|\n", teamList.get(i).name, teamList.get(i).id);
				}
			} else {
				System.out.printf("|%-15s|%-50s|%-20s|%-3s|\n", "NIM", "Name", "Table Name", "ID");
				for (int i = 0; i < userList.size(); i++) {
					for (int j = 0; j < teamList.size(); j++) {
						if (teamList.get(j).id == userList.get(i).id) {
							System.out.printf("|%-15s|%-50s|%-20s|%-3d|\n", userList.get(i).nim, userList.get(i).name,
									teamList.get(j).name, userList.get(i).id);
						}
					}
				}
			}
			return;
		}
		if (!filter.equalsIgnoreCase("name") && !filter.equalsIgnoreCase("id")) {
			System.out.println("Error: Filter wrong input");
			return;
		}
		if (filter.equalsIgnoreCase("name")) {
			if (operator == null || input == null) {
				System.out.println("Error: There are no operator or input to be filtered");
				return;
			}
			if (!join) {
				System.out.printf("|%-50s|%-3s|\n", "Name", "ID");
				for (int i = 0; i < teamList.size(); i++) {
					if (operator.equals("=")) {
						if (teamList.get(i).name.equals(input)) {
							System.out.printf("|%-50s|%-3d|\n", teamList.get(i).name, teamList.get(i).id);
						}
					} else {
						if (!userList.get(i).name.equals(input)) {
							System.out.printf("|%-50s|%-3d|\n", teamList.get(i).name, teamList.get(i).id);
						}
					}
				}
				return;
			} else {
				System.out.printf("|%-15s|%-50s|%-20s|%-3s|\n", "NIM", "Name", "Table Name", "ID");
				for (int i = 0; i < teamList.size(); i++) {
					if (operator.equals("=")) {
						if (teamList.get(i).name.equals(input)) {
							for (int j = 0; j < userList.size(); j++) {
								if (teamList.get(i).id == userList.get(j).id) {
									System.out.printf("|%-15s|%-50s|%-20s|%-3d|\n", userList.get(i).nim,
											userList.get(i).name, teamList.get(j).name, userList.get(i).id);
								}
							}
						}
					} else {
						if (!teamList.get(i).name.equals(input)) {
							for (int j = 0; j < userList.size(); j++) {
								if (teamList.get(i).id == userList.get(j).id) {
									System.out.printf("|%-15s|%-50s|%-20s|%-3d|\n", userList.get(i).nim,
											userList.get(i).name, teamList.get(j).name, userList.get(i).id);
								}
							}
						}
					}
				}
			}
			return;
		} else if (filter.equalsIgnoreCase("id")) {
			if (operator == null || input == null) {
				System.out.println("Error: There are no operator or input to be filtered");
				return;
			}
			if (!join) {
				System.out.printf("|%-50s|%-3s|\n", "Name", "ID");
				for (int i = 0; i < teamList.size(); i++) {
					if (operator.equals("=")) {
						if (teamList.get(i).id == Integer.parseInt(input)) {
							System.out.printf("|%-50s|%-3d|\n", userList.get(i).name, teamList.get(i).id);
						}
					} else if (operator.equals("!=")) {
						if (teamList.get(i).id != Integer.parseInt(input)) {
							System.out.printf("|%-50s|%-3d|\n", teamList.get(i).name, teamList.get(i).id);
						}
					} else if (operator.equals("<")) {
						if (teamList.get(i).id < Integer.parseInt(input)) {
							System.out.printf("|%-50s|%-3d|\n", teamList.get(i).name, teamList.get(i).id);
						}
					} else if (operator.equals(">")) {
						if (teamList.get(i).id > Integer.parseInt(input)) {
							System.out.printf("|%-50s|%-3d|\n", teamList.get(i).name, teamList.get(i).id);
						}
					} else if (operator.equals(">=")) {
						if (teamList.get(i).id >= Integer.parseInt(input)) {
							System.out.printf("|%-50s|%-3d|\n", teamList.get(i).name, teamList.get(i).id);
						}
					} else {
						if (teamList.get(i).id <= Integer.parseInt(input)) {
							System.out.printf("|%-50s|%-3d|\n", teamList.get(i).name, teamList.get(i).id);
						}
					}
				}
				return;
			} else {
				System.out.printf("|%-15s|%-50s|%-20s|%-3s|\n", "NIM", "Name", "Table Name", "ID");
				for (int i = 0; i < teamList.size(); i++) {
					if (operator.equals("=")) {
						if (teamList.get(i).id == Integer.parseInt(input)) {
							for (int j = 0; j < userList.size(); j++) {
								if (teamList.get(i).id == userList.get(j).id) {
									System.out.printf("|%-15s|%-50s|%-20s|%-3d|\n", userList.get(j).nim,
											userList.get(j).name, teamList.get(i).name, teamList.get(i).id);

								}
							}
						}
					} else if (operator.equals("!=")) {
						if (teamList.get(i).id != Integer.parseInt(input)) {
							for (int j = 0; j < userList.size(); j++) {
								if (teamList.get(i).id == userList.get(j).id) {
									System.out.printf("|%-15s|%-50s|%-20s|%-3d|\n", userList.get(j).nim,
											userList.get(j).name, teamList.get(i).name, teamList.get(i).id);
								}
							}
						}
					} else if (operator.equals("<")) {
						if (teamList.get(i).id < Integer.parseInt(input)) {
							for (int j = 0; j < userList.size(); j++) {
								if (teamList.get(i).id == userList.get(j).id) {
									System.out.printf("|%-15s|%-50s|%-20s|%-3d|\n", userList.get(j).nim,
											userList.get(j).name, teamList.get(i).name, teamList.get(i).id);
								}
							}
						}
					} else if (operator.equals(">")) {
						if (teamList.get(i).id > Integer.parseInt(input)) {
							for (int j = 0; j < userList.size(); j++) {
								if (teamList.get(i).id == userList.get(j).id) {
									System.out.printf("|%-15s|%-50s|%-20s|%-3d|\n", userList.get(j).nim,
											userList.get(j).name, teamList.get(i).name, teamList.get(i).id);
								}
							}
						}
					} else if (operator.equals(">=")) {
						if (teamList.get(i).id >= Integer.parseInt(input)) {
							for (int j = 0; j < userList.size(); j++) {
								if (teamList.get(i).id == userList.get(j).id) {
									System.out.printf("|%-15s|%-50s|%-20s|%-3d|\n", userList.get(j).nim,
											userList.get(j).name, teamList.get(i).name, teamList.get(i).id);
								}
							}
						}
					} else {
						if (teamList.get(i).id <= Integer.parseInt(input)) {
							for (int j = 0; j < userList.size(); j++) {
								if (teamList.get(i).id == userList.get(j).id) {
									System.out.printf("|%-15s|%-50s|%-20s|%-3d|\n", userList.get(j).nim,
											userList.get(j).name, teamList.get(i).name, teamList.get(i).id);
								}
							}
						}
					}
				}
				return;
			}
		}
	}

}
