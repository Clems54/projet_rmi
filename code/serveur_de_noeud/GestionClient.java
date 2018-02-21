import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Classe partagé pour le comptage des mots d'un texte
 */
public class GestionClient implements ServiceClient{

  public int compterMots(ArrayList<String> listeLignes) throws RemoteException{
    for (String ligne : listeLignes) {
      System.out.println(ligne);
    }
    return -1;
  }
}
