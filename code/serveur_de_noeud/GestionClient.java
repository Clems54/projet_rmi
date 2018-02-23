import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.*;

/**
* Classe partagé pour le comptage des mots d'un texte
*/
public class GestionClient implements ServiceClient{

  public GestionNoeud listeNoeuds;

  public GestionClient(GestionNoeud listeNoeuds){
    this.listeNoeuds = listeNoeuds;
  }

  public int compterMots(ArrayList<String> listeLignes) throws RemoteException, ServerNotActiveException, InterruptedException{
    System.out.println("Nouveau client a l'adresse "+RemoteServer.getClientHost()+"\nTraitement en cours...");
    System.out.println("\nTexte:\n--------------------");
    for (String ligne : listeLignes) {
      System.out.println(ligne);
    }
    System.out.println("--------------------\n");

    boolean fini = false;
    int nbMots = 0;
    Iterator iterateurLigne = listeLignes.iterator();
    while(!fini){
      if(iterateurLigne.hasNext()){
        Thread.sleep(500);
        if(this.listeNoeuds.noeudsCalcul.size() != 0){
          nbMots += this.listeNoeuds.noeudsCalcul.get(0).calculer((String)iterateurLigne.next());
        }
      }else{
        fini = true;
    }
  }
    System.out.println("Fin du traitement.");
    return nbMots;
  }
}
