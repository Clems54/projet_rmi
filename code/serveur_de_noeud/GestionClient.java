import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ConcurrentModificationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.*;

/**
* Classe partagé pour le comptage des mots d'un texte
*/
public class GestionClient implements ServiceClient{

  // Recuperation de la liste des noeuds dispo
  public GestionNoeud listeNoeuds;

  /**
   * Constructeur qui import la liste des noeuds
   */
  public GestionClient(GestionNoeud listeNoeuds){
    this.listeNoeuds = listeNoeuds;
  }

  /**
   * Methode qui permet de partager le calcul à tout les noeuds disponible
   * @param listeLignes liste contenant toutes les lignes d'un texte
   * @return entier representant le nombre de mots contenu dans le texte en parametre
   */
  public int compterMots(ArrayList<String> listeLignes) throws RemoteException, ServerNotActiveException, InterruptedException{
    // Affichage sur le serveur de la connexion d'un client
    System.out.println("Nouveau client a l'adresse "+RemoteServer.getClientHost()+"\nTraitement en cours...");

    // Affichage du texte dans la console du serveur
    System.out.println("\nTexte:\n--------------------");
    for (String ligne : listeLignes) {
      System.out.println(ligne);
    }
    System.out.println("--------------------\n");


    boolean fini = false; // Booléen qui represente le traitement fini ou non
    int nbMots = 0; // Entier qui représente le nombre de mots
    Iterator<ServiceCalcul> noeudDeCalcul = this.listeNoeuds.noeudsCalcul.iterator(); // Itérateur représentant les noeuds de calcul disponible
    Iterator<String> iterateurLigne = listeLignes.iterator(); // Itérateur pour le parcourt de ligne du texte à compter
    ServiceCalcul noeudCourant; // Recuperation du noeud a utiliser

    // Boucle pour le comptage de mots
    while(!fini){
      // Verifie si il y a des lignes disponibles
      if(iterateurLigne.hasNext()){

        // Boucle qui permet d'attendre la connexion d'un noeud sans trop pousser le proco
        while(this.listeNoeuds.noeudsCalcul.size() == 0)
          Thread.sleep(500);

        // Parcourt des noeuds de calculs pour le partage
        if(noeudDeCalcul.hasNext()){
          // Obligation de faire cela pour eviter l'acces concurrent a la liste
          try{
            Thread.sleep(500);
            noeudCourant = noeudDeCalcul.next();
            nbMots += noeudCourant.calculer(iterateurLigne.next());
          }catch(ConcurrentModificationException e){
            noeudDeCalcul = this.listeNoeuds.noeudsCalcul.iterator(); // Raffraichissement de la liste des noeuds après co d'un nouveau
          }

        }else
        noeudDeCalcul = this.listeNoeuds.noeudsCalcul.iterator(); // Permet de reparcourir les noeuds depuis le debut de la liste
      }else{
        fini = true;
      }
    }
    System.out.println("Fin du traitement.");
    return nbMots;
  }
}
