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

public class Client {

  public static void main(String[] args) throws RemoteException, NotBoundException{

    File dossier = new File("."); // Permet de lire tout les fichiers du repertoire client
    File texte = new File(""); // Fichier texte

    for(File f : dossier.listFiles()){
      if(f.getName().substring(f.getName().length()-4,f.getName().length()).equals(".txt") && f.isFile()){
        texte = f;
        System.out.println("Le fichier texte \""+texte.getName()+"\" a bien ete trouve.\nTraitement en cours...");
        break;
      }
    }

    if(texte.exists()){
      ArrayList<String> listeLignes = new ArrayList<String>();

      // Lecture du fichier texte
      try{
        BufferedReader br = new BufferedReader(new FileReader(texte));
        String ligne;
        try{
          while((ligne = br.readLine()) != null){
            listeLignes.add(ligne);
          }
        }catch(IOException e){}
        }catch(FileNotFoundException e){}

          // Adresse a changer en fonction de l'ip du serveur de noeud
          Registry reg = LocateRegistry.getRegistry("localhost",1099);
          ServiceClient sc = (ServiceClient) reg.lookup("compter");
          sc.compterMots(listeLignes);


        }else{
          System.out.println("Pas de fichier .txt dans le dossier client!\nArret du programme.");
        }
      }
    }
