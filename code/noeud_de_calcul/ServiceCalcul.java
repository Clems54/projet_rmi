import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface qui donne les méthodes utilisables à distance entre serveur
 * de noeud et noeud de calcul et représente le service du client
 */
public interface ServiceCalcul extends Remote{

  public int calculer(String ligne) throws RemoteException;
}
