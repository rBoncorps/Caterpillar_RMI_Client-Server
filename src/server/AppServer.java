package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AppServer {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(8090);
			ServerForumInterface server = new ServerForum();
			Naming.bind(
			"//localhost:8090/server",server);
		}
		catch(Exception e) {
			System.out.println("Error during Server creation");
			e.printStackTrace();
		}
	}
}
