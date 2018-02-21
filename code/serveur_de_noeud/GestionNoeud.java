import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

/**
 * Classe partagé pour le comptage des mots d'un texte
 */
public class GestionNoeud implements ServiceNoeud{

  public ArrayList<ServiceCalcul> noeudsCalcul = new ArrayList<ServiceCalcul>();

  public void enregistrerNoeud(ServiceCalcul noeudCalcul) throws RemoteException, ServerNotActiveException{
    noeudsCalcul.add(noeudCalcul);
    System.out.println("Nouveau noeud a l'adresse "+RemoteServer.getClientHost());
  }
}
