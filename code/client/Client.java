import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
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

  public static void main(String[] args) throws RemoteException, NotBoundException, ServerNotActiveException, InterruptedException{

    // Lecture du dossier pour trouver par la suite un fichier texte
    File dossier = new File("."); // Permet de récuperer tout les fichiers du repertoire client
    File texte = new File(""); // Fichier texte
    for(File f : dossier.listFiles()){
      // Conditionnelle qui verifie si l'élément trouvé est un fichier et si le nom se termine par .txt
      if(f.getName().substring(f.getName().length()-4,f.getName().length()).equals(".txt") && f.isFile()){
        texte = f;
        System.out.println("Le fichier texte \""+texte.getName()+"\" a bien ete trouve.\nCalcul en cours...");
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
        }catch(IOException e){}
        }catch(FileNotFoundException e){}


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

          // récupérer l'annuaire sur serveur et port
          Registry reg = LocateRegistry.getRegistry(serveur, port);

          // Appel de la méthode distante
          ServiceClient sc = (ServiceClient) reg.lookup("compter");
          System.out.println("Le texte contient "+sc.compterMots(listeLignes)+" mots.");
          System.out.println("Fin du calcul.");

        }else{
          // Gestion du cas où aucun fichier n'a été trouvé
          System.out.println("Pas de fichier .txt dans le dossier client!\nArret du programme.");
        }
      }
    }
