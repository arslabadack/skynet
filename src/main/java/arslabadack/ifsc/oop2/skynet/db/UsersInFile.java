package arslabadack.ifsc.oop2.skynet.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import arslabadack.ifsc.oop2.skynet.entities.User;

public class UsersInFile {

	public void check() {
		String fileLocation = "./local-user.json";
		List<String> fileLines = new ArrayList<>();
		try {
			File file = new File(fileLocation);
			if (file.exists()) {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine())
					fileLines.add(scanner.nextLine());
				scanner.close();
			} else {
				System.err.println("File \"" + fileLocation + "\" is missing.");
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error while opening file \"" + fileLocation + "\".");
		}

		List<User> userList = UtilDB.consumeAPI(fileLines);
		for (User u : userList) {
			User knownUser = new UserDAO().get(u.getUsername());
			if (knownUser != null) {
				if (!u.getUsername().contentEquals(knownUser.getUsername())) {
					new UserDAO().persist(u);
				}
			} else {
				new UserDAO().persist(u);
			}
		}
	}
	
}