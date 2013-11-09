package provider;

import java.rmi.Naming;
import java.rmi.RemoteException;

import server.ServerForumInterface;

public class AppProvider {

	public static void main(String[] args) {
		// Permet de créer un fournisseur de sujet pour un sujet donné
		String adr = "//localhost:8090/server";
		String subject = "sujet";
		try {
			ServerForumInterface forumServer = (ServerForumInterface) Naming.lookup(adr);
			Provider provider = new Provider(subject);
			forumServer.registerProvider(provider);
			
		}
		catch(Exception e) {
			
		}

	}

}
