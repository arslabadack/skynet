package arslabadack.ifsc.oop2.skynet;

import arslabadack.ifsc.oop2.skynet.db.UsersInFile;

public class FileThread implements Runnable {

	@Override
	public void run() {
		UsersInFile usersInFile = new UsersInFile();
		try {
			while (true) {
				usersInFile.check();
				Thread.sleep(4200);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}