import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

/**
 * Interface qui donne les méthodes utilisables à distance entre serveur
 * de noeud et noeud de calcul et représente le service du client
 */
public interface ServiceNoeud extends Remote{

  /**
   *
   */
  public void enregistrerNoeud(ServiceCalcul noeudCalcul) throws RemoteException, ServerNotActiveException;
}
