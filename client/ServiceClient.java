import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceClient extends Remote{

  /**
   * Methode qui permet de compter le nombre de mots d'un texte
   * @param texte fichier avec texte a compter
   * @return Nombre de mots present dans le texte donnee en parametre
   */
  public int compterMots(File texte) throws RemoteException;
}
