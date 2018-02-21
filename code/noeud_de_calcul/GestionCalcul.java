import java.rmi.Remote;
import java.rmi.RemoteException;

public class GestionCalcul implements ServiceCalcul{

  public int calculer(String ligne) throws RemoteException{
    return ligne.split(" ").length;
  }
}
