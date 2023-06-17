package ProjetEsport.HCS.Classes.GestionFichier;

import ProjetEsport.HCS.Classes.Evenements.Tournoi;
import ProjetEsport.HCS.Classes.Participants.Coach;
import ProjetEsport.HCS.Classes.Participants.Members;
import ProjetEsport.HCS.Classes.Participants.Players;
import ProjetEsport.HCS.Classes.Participants.Teams;

import java.io.*;
import java.util.StringTokenizer;

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

    public static void importerMembres(Teams equipe){

        //Attributes[0] = "Type"
        //Attributes[1] = pseudo
        //Attributes[2] = Nationnalite
        //Attributes[3] = RegisterDate - Jour
        //Attributes[4] = RegisterDate - Mois
        //Attributes[5] = RegisterDate - Année
        //Attributes[6] = Roles

        try {
            File file = new File("src/Data/"+ equipe.getTeamName().toLowerCase() + ".team");

            if(file.exists()) {

                equipe.resetJoueurs();

                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String joueurData;
                StringTokenizer st;
                String[] Attributes = new String[7];
                while ((joueurData = br.readLine()) != null) {

                    Attributes = new String[7];
                    st = new StringTokenizer(joueurData, ",");
                    for (int i = 0; st.hasMoreTokens(); i++) {
                        Attributes[i] = st.nextToken();
                    }


                    if (Attributes[0].equalsIgnoreCase("Coach")) {
                        Coach coach = new Coach();
                        coach.setPseudo(Attributes[1]);
                        coach.setNationality(Attributes[2]);
                        coach.setRegisterTime(Integer.parseInt(Attributes[3]), Integer.parseInt(Attributes[4]), Integer.parseInt(Attributes[5]));

                        equipe.setTeamCoach(coach);
                    } else{
                        Players joueur = new Players();

                        joueur.setPseudo(Attributes[1]);
                        joueur.setNationality(Attributes[2]);
                        joueur.setRegisterTime(Integer.parseInt(Attributes[3]), Integer.parseInt(Attributes[4]), Integer.parseInt(Attributes[5]));
                        joueur.setRole(Attributes[6].equalsIgnoreCase("Starter") ? Players.Status.STARTER : Players.Status.SUBSTITUTE);

                        equipe.AjouterJoueur(joueur);
                    }
                }

                br.close();
                fr.close();

                System.out.println("Importation Reussie !");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void exporterMembres(Teams equipe){
        File file = new File("src/Data/"+ equipe.getTeamName().toLowerCase() + ".team");
        if(file.exists()) file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            FileWriter fw = new FileWriter( "src/Data/"+ equipe.getTeamName().toLowerCase() + ".team");
            BufferedWriter bw = new BufferedWriter(fw);

                if(equipe.getTeamCoach() != null){
                    bw.append("Coach," + equipe.getTeamCoach().getPseudo() + "," + equipe.getTeamCoach().getNationality() + "," + equipe.getTeamCoach().getRegisterNormalFormat());
                    bw.newLine();
                }

                for(Players m : equipe.getTeamsPlayers()){
                        bw.append("Joueur," + m.getPseudo() + "," + m.getNationality() + "," + m.getRegisterNormalFormat() + ","  + m.getRole().name());
                        bw.newLine();

                }

            bw.close();
            fw.close();

            System.out.println("Exportation Reussie !");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
