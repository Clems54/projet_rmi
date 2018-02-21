import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Central {

	public static void main (String[] args) throws RemoteException{

    // Gestion de la connexion d'un client souhaitant
    // compter le nombre de mots d'un texte avec ajout
    // du service dans l'annuaire
		GestionClient gs = new GestionClient();
		ServiceClient sd = (ServiceClient) UnicastRemoteObject.exportObject(gs,0);
		Registry annuaire = LocateRegistry.getRegistry();
		annuaire.rebind("compter",sd);
	}
}
