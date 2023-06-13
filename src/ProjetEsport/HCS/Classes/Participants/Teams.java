package ProjetEsport.HCS.Classes.Participants;

import java.time.LocalDate;
import java.util.*;
import ProjetEsport.HCS.Classes.Interfaces.getInstanceAt;
import org.jetbrains.annotations.NotNull;

public class Teams implements getInstanceAt<Members>, Cloneable, Comparable<Teams>{
    private ArrayList<Players> TeamsPlayers;
    private Coach TeamCoach;
    private String TeamName;
    private String Description;

    private String Image;

    public Teams(){
    }

    // Creer une instance d'equipe
    public Teams(String Name){
        this.TeamName = Name;
        TeamsPlayers = new ArrayList<Players>(5);
        TeamCoach = null;
        Description = "You can add a description!";
        Image = "src/ProjetEsport/Graphics/Images/logo.png";
    }

    public Teams(String Name, String description){
        this.TeamName = Name;
        TeamsPlayers = new ArrayList<Players>(5);
        TeamCoach = null;
        if(description == null) Description = "";
        else if(description.length() <= 200)
            Description = description;
        Image = "src/ProjetEsport/Graphics/Images/logo.png";
    }

    // Ajouter un joueur
    public void AjouterJoueur(Players player) throws Exception {
        if(TeamsPlayers.size() < 5)
            if(!TeamsPlayers.contains(player)){
                TeamsPlayers.add(player);
                player.setEquipe(this);
                System.out.printf("[%s] Joueur ajoute !\n",this.TeamName);
            }
            else throw new Exception("The player your trying to add is already in the team!");
        else throw new Exception("Max amount of players has already been reached!");
    }

    // Supprimer un joueur
    public void SupprimerJoueur(Players player) throws Exception {
        if(TeamsPlayers.contains(player)){
            TeamsPlayers.remove(player);
            System.out.printf("[%s] Joueur supprime !\n",this.TeamName);
        }
        else throw new Exception("The player your trying to remove is not in the team!");
    }

    // Mettre une petite description  à l'équipe
    public void setDescription(String description) throws Exception {
        if(description == null){
            Description = "";
            return;
        }
        if(description.length() <= 200)
            Description = description;
    }

    // Etablir le nom de l'équipe
    public void setTeamName(String teamName) throws Exception {
        if(!TeamName.equals(teamName))
            TeamName = teamName;
    }
    public void setTeamCoach(Coach c){
        this.TeamCoach = c;
        c.setEquipe(this);
        System.out.printf("[%s] Coach  ajoute !\n",this.TeamName);
    }

    // suppression du coach
    public void supprimerCoach(){
        this.TeamCoach = null;
        System.out.printf("[%s] Coach supprime !\n",this.TeamName);
    }

    public String getTeamName() {
        return TeamName;
    }
    public String getDescription() {
        return Description;
    }
    public int getCountPlayer(){
        return TeamsPlayers.size();
    }
    public Coach getTeamCoach() {
        return TeamCoach;
    }
    public ArrayList<Players> getTeamsPlayers() {
        return TeamsPlayers;
    }

    public ArrayList<Members> getAllMembers(){
        ArrayList<Members> listMembres = new ArrayList<>(TeamsPlayers);
        listMembres.add(TeamCoach);
        return listMembres;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    @Override
    public String toString() {
        String s = "\n=== Equipe ===\n[" + TeamName + "]\ndescription: \"" + Description + "\"\n\n";

        s += "=== Coach ===\n";
        if(TeamCoach != null)
            s += TeamCoach + "\n";
        else s+= "vide\n";

        s += "=== Joueurs ===\n";
        if(!TeamsPlayers.isEmpty())
            s += TeamsPlayers + "\n";
        else s+= "vide\n";

        return s;
    }

    @Override
    public int compareTo(@NotNull Teams o) {
        return this.getTeamName().compareTo(o.getTeamName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teams teams = (Teams) o;
        return Objects.equals(TeamsPlayers, teams.TeamsPlayers) && Objects.equals(TeamName, teams.TeamName) && Objects.equals(Description, teams.Description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(TeamsPlayers, TeamCoach, TeamName, Description);
    }

    @Override
    public Members getInstance(int n) {
        //return TeamsPlayers.get(n);
        if(n == 6) return TeamCoach;
        return TeamsPlayers.get(n);
    }

    @Override
    public Teams clone() {
        try
        {
            Teams cloned = (Teams)super.clone();
            cloned.TeamsPlayers = new ArrayList<>();
            for (Players player : TeamsPlayers) {
                cloned.TeamsPlayers.add((Players) player.clone());  // Copie de chaque objet Players dans TeamsPlayers
            }
            if(this.TeamCoach != null)
                cloned.TeamCoach = (Coach)TeamCoach.clone();  // Copie de l'objet Coach

            // Vous pouvez également copier les attributs immuables directement
            cloned.TeamName = this.TeamName;
            cloned.Description = this.Description;

            return cloned;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] argv){
        try{
            Teams teams = new Teams("Quadrant","We are a french team");
            //teams.setDescription("We are a french team");

            Players[] players = new Players[]{
                    new Players("PTG_1",true, "Be"),
                    new Players("PGT_2",true, "Be"),
                    new Players("PGT_3",true, "Be"),
                    new Players("PGT_4",true, "Be")
            };

            teams.TeamsPlayers.addAll(List.of(players));
            teams.TeamsPlayers.sort(Players::compareTo);

            Coach c = new Coach("Wagner","Be");
            teams.setTeamCoach(c);

            //Locale Place = new Locale("En");

            System.out.println(teams);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
