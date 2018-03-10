import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.ServerNotActiveException;

public class Noeud{

  public static void main(String[] args){

    try{
      Registry reg = LocateRegistry.getRegistry("localhost",1099);
      ServiceNoeud sn = (ServiceNoeud) reg.lookup("enregistrer");

      GestionCalcul calcul = new GestionCalcul();
      ServiceCalcul sc = (ServiceCalcul) UnicastRemoteObject.exportObject(calcul,0);
      sn.enregistrerNoeud(sc);
      System.out.println("En attente de calcul...");
    }
    catch(NotBoundException e){
      System.out.println("Serveur introuvable!\nArret du programme.");
    }
    catch(RemoteException e){
      System.out.println("Serveur deconnecte\nArret du programme.");
    }
    catch(ServerNotActiveException e){
      System.out.println("Recuperation du serveur impossible!\nArret du programme.");
    }
  }
}
