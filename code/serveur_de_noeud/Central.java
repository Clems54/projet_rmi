import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
* Classe qui représente le serveur de noeud
*/
public class Central {

	public static void main (String[] args) throws RemoteException{

		GestionClient gs = new GestionClient(); // Classe partagé
		ServiceClient sd = (ServiceClient) UnicastRemoteObject.exportObject(gs,0); // Permet d'exporter la référence de l'instance créé
		Registry annuaire = LocateRegistry.getRegistry(); // Accès au registre du serveur de noeud
		annuaire.rebind("compter",sd); // Ajout de la référence de l'objet partagé dans l'annuaire rmiregistry
	}
}
