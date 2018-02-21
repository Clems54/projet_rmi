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

/**
* Classe qui doit être lancé par le Client
* souhaitant répartir le comptage d'un fichier texte
*/
public class Client {

  public static void main(String[] args) throws RemoteException, NotBoundException{

    // Lecture du dossier pour trouver par la suite un fichier texte
    File dossier = new File("."); // Permet de récuperer tout les fichiers du repertoire client
    File texte = new File(""); // Fichier texte
    for(File f : dossier.listFiles()){
      // Conditionnelle qui verifie si l'élément trouvé est un fichier et si le nom se termine par .txt
      if(f.getName().substring(f.getName().length()-4,f.getName().length()).equals(".txt") && f.isFile()){
        texte = f;
        System.out.println("Le fichier texte \""+texte.getName()+"\" a bien ete trouve.\nTraitement en cours...");
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

          // Récupération objet distant
          Registry reg = LocateRegistry.getRegistry("localhost",1099); // Adresse a changer en fonction de l'ip du serveur de noeud
          ServiceClient sc = (ServiceClient) reg.lookup("compter");
          sc.compterMots(listeLignes); // Appel de la méthode distante


        }else{
          // Gestion du cas où aucun fichier n'a été trouvé
          System.out.println("Pas de fichier .txt dans le dossier client!\nArret du programme.");
        }
      }
    }
