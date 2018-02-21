import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

/**
 * Classe partagé pour le comptage des mots d'un texte
 */
public class GestionClient implements ServiceClient{

  public int compterMots(ArrayList<String> listeLignes) throws RemoteException, ServerNotActiveException{
    System.out.println("Nouveau noeud a l'adresse "+RemoteServer.getClientHost()+"\nTraitement en cours...");
    System.out.println("\nTexte:\n--------------------");
    for (String ligne : listeLignes) {
      System.out.println(ligne);
    }
    System.out.println("--------------------\n");
    return -1;
  }
}
