package ProjetEsport.HCS.Classes.GestionFichier;

import ProjetEsport.HCS.Classes.Evenements.Tournoi;

import java.io.*;

public class GestionnaireFichier {

    public static String CreationFichier(String fichier){
        String path = fichier;
        File file = new File(path);
        if(file.exists()) file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    public static void sauvegarderFichier(Tournoi tournoi){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tournoi.getFileName()));
                oos.writeObject(tournoi);
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Sauvegarde Erreur !");
            return;
        }
        System.out.println("Sauvegarde terminée !");
    }

    public static Tournoi ouvrirFichier(String filePath){
        File file = new File( filePath);
        if(!file.exists()){
            return null;
        }
        Tournoi tournoi;
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            tournoi = (Tournoi)ois.readObject();
            ois.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Chargement Erreur !");
            return null;
        }
        System.out.println("Chargement terminé !");
        tournoi.setFileName(filePath);
        return tournoi;
    }
}
