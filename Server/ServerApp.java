package Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerApp {

	public static void main(String[] args) {
		try {
			// Processus d'enregistrement des associations adresse externe - Objet distant
			LocateRegistry.createRegistry(8090);
			ServerForumInterface serverForum = new ServerForum();
			Naming.bind("//localhost:8090/server", serverForum);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
