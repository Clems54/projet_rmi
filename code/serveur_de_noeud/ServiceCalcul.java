import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface qui donne les méthodes utilisables à distance entre serveur
 * de noeud et noeud de calcul et représente le service du client
 */
public interface ServiceCalcul extends Remote{

  /**
   * Methode qui permet au noeud de calcul d'aider au comptage du texte
   * @param noeud_de_calcul le noeud de calcul qui souhaite aider au comptage du texte
   */
  public int calculer(String ligne) throws RemoteException;
}
