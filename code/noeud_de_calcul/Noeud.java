import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Noeud{

  public static void main(String[] args) throws RemoteException, NotBoundException{
    Registry reg = LocateRegistry.getRegistry("localhost",1099);
    ServiceNoeud sn = (ServiceNoeud) reg.lookup("enregistrer");

    GestionCalcul calcul = new GestionCalcul();
    ServiceCalcul sc = (ServiceCalcul) UnicastRemoteObject.exportObject(calcul,0);
    try{
      System.out.println("En attente de calcul...");
      sn.enregistrerNoeud(sc);
    }catch(Exception e){}
    }
  }
