import java.rmi.Remote;
import java.rmi.RemoteException;

public class GestionCalcul implements ServiceCalcul{

  /**
   * Methode qui permet au noeud de calcul d'aider au comptage du texte
   * @param noeud_de_calcul le noeud de calcul qui souhaite aider au comptage du texte
   */
  public int calculer(String ligne) throws RemoteException{
    System.out.println("Comptage du nombre de mot dans: \""+ligne+"\".");
    System.out.println("--> "+ligne.split(" ").length+" mots.");
    return ligne.split(" ").length;
  }
}
