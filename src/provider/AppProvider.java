package provider;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import server.ServerForum;
import server.ServerForumInterface;

public class AppProvider {

	public static void main(String[] args) {
		// Permet de créer un fournisseur de sujet pour un sujet donné
		int port = 8091;
		String adr = "//localhost:" + String.valueOf(port) + "/provider";
		String subject = "sujet";
		try {
			//Registrating provider
			LocateRegistry.createRegistry(port);
			ProviderInterface prov = new Provider(subject,adr);
			Naming.bind(adr,prov);
			
			ServerForumInterface forumServer = (ServerForumInterface) Naming.lookup("//localhost:8090/server");
			forumServer.registerProvider(adr);
			System.out.println("Provider created with subject : " + subject);
			
		}
		catch(Exception e) {
			System.out.println("Error during Server creation");
			e.printStackTrace();
		}
	}
}
