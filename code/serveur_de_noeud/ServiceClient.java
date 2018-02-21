import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.File;

public interface ServiceClient extends Remote{

  /**
   * Methode qui permet de compter le nombre de mots d'un texte
   * @param texte fichier avec texte a compter
   * @return nombre de mots present dans le texte donnee en parametre
   */
  public int compterMots(ArrayList<String> listeLignes) throws RemoteException;
}