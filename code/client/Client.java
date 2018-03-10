import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.rmi.UnmarshalException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.rmi.server.ServerNotActiveException;

/**
* Classe qui doit être lancé par le Client
* souhaitant répartir le comptage d'un fichier texte
*/
public class Client {

  public static void main(String[] args){

    // Lecture du dossier pour trouver par la suite un fichier texte
    File dossier = new File("."); // Permet de récuperer tout les fichiers du repertoire client
    File texte = new File(""); // Fichier texte
    for(File f : dossier.listFiles()){
      // Conditionnelle qui verifie si l'élément trouvé est un fichier et si le nom se termine par .txt
      if(f.getName().substring(f.getName().length()-4,f.getName().length()).equals(".txt") && f.isFile()){
        texte = f;
        System.out.println("Le fichier texte \""+texte.getName()+"\" a bien ete trouve.");
        break; // Sort de la boucle for quand fichier texte trouvé
      }
    }

    // Vérifie si un fichier texte a été trouvé
    if(texte.exists()){
      ArrayList<String> listeLignes = new ArrayList<String>(); // Arraylist qui va être transmise au serveur de noeud pour le traitement

      // Lecture du fichier texte avec ajout de chaque ligne dans ArrayList
      try{
        BufferedReader br = new BufferedReader(new FileReader(texte));
        String ligne;
        try{
          while((ligne = br.readLine()) != null){
            listeLignes.add(ligne);
          }
        }
        catch(IOException e){
          System.out.println("Erreur de lecture du fichier");
        }
      }
      catch(FileNotFoundException e){
        System.out.println("Fichier non trouve");
      }

      // Utilisation : java ClientCalcul [hôte] [port]
      // Valeurs par défaut : hôte = "localhost", port = 1099
      // adresse & port du serveur de calcul par défaut
      String serveur="localhost";
      int port=1099;

      // Si adresse fournie en ligne de commande
      if( args.length > 0 )
      serveur=args[0];
      if( args.length > 1 )
      port=Integer.parseInt(args[1]);

      // Gestion des exceptions
      try{
      // récupérer l'annuaire sur serveur et port
      Registry reg = LocateRegistry.getRegistry(serveur, port);

      // Appel de la méthode distante
      ServiceClient sc = (ServiceClient) reg.lookup("compter");

      System.out.println("Calcul en cours...");

      System.out.println("Le texte contient "+sc.compterMots(listeLignes)+" mots.");
      System.out.println("Fin du programme.");
      }
      // Deconnection d'un serveur pendant calcul
      catch(UnmarshalException e){
        System.out.println("Serveur deconnecte!\nArret du programme.");
      }
      catch(NotBoundException e){
        System.out.println("Serveur introuvable!\nArret du programme.");
      }
      catch(RemoteException e){
        System.out.println("Probleme de communication avec le serveur!\nArret du programme.");
      }
      catch(ServerNotActiveException e){
        System.out.println("Recuperation du serveur impossible!\nArret du programme.");
      }
      catch(InterruptedException e){
        System.out.println("Thread interrompu!\nArret du programme.");
      }

    }else{
      // Gestion du cas où aucun fichier n'a été trouvé
      System.out.println("Pas de fichier .txt dans le dossier client!\nArret du programme.");
    }
  }
}
