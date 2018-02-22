import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
* Classe qui représente le serveur de noeud
*/
public class Central {

	public static void main (String[] args) throws RemoteException{
		// Accès au registre du serveur de noeud (rmiregistry)
		Registry annuaire = LocateRegistry.getRegistry();

		// Gestion noeud
		GestionNoeud gn = new GestionNoeud(); // Classe partagé
		ServiceNoeud sn = (ServiceNoeud) UnicastRemoteObject.exportObject(gn,0); // Permet d'exporter la référence de l'instance créé avec un port donné
		annuaire.rebind("enregistrer",sn); // Ajout de la référence de l'objet partagé dans l'annuaire rmiregistry

		// Gestion client
		GestionClient gc = new GestionClient(gn); // Classe partagé
		ServiceClient sc = (ServiceClient) UnicastRemoteObject.exportObject(gc,0); // Permet d'exporter la référence de l'instance créé avec un port donné
		annuaire.rebind("compter",sc); // Ajout de la référence de l'objet partagé dans l'annuaire rmiregistry
	}
}
