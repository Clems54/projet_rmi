import java.rmi.Remote;
import java.rmi.RemoteException;

public class GestionCalcul implements ServiceCalcul{

  public int calculer(String ligne) throws RemoteException{
    System.out.println("Comptage du nombre de mot dans: \""+ligne+"\".");
    System.out.println("--> "+ligne.split(" ").length+" mots.");
    return ligne.split(" ").length;
  }
}
