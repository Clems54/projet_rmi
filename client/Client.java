import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

  public static void main(String[] args) {

    // Adresse a changer en fonction de l'ip du serveur de noeud
    Registry reg = LocateRegistry.getRegistry("localhost",1099);
  }
}
