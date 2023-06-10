package ProjetEsport.HCS.Classes.Participants;

import java.util.*;
import ProjetEsport.HCS.Classes.Interfaces.getInstanceAt;

public class Teams implements getInstanceAt<Members>{
    private ArrayList<Players> TeamsPlayers;
    private Coach TeamCoach;
    private String TeamName;
    private String Description;

    public Teams(){
    }

    // Creer une instance d'equipe
    public Teams(String Name){
        this.TeamName = Name;
        TeamsPlayers = new ArrayList<Players>(5);
        TeamCoach = null;
        Description = "You can add a description!";
    }

    public Teams(String Name, String description){
        this.TeamName = Name;
        TeamsPlayers = new ArrayList<Players>(5);
        TeamCoach = null;
        if(description == null) Description = "";
        else if(description.length() <= 200)
            Description = description;
    }

    // Ajouter un joueur
    public void AjouterJoueur(Players player) throws Exception {
        if(TeamsPlayers.size() < 5)
            if(!TeamsPlayers.contains(player)){
                TeamsPlayers.add(player);
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
        if(description == null) throw new Exception("Description can't be null");
        if(description.length() <= 200)
            Description = description;
        else throw new Exception("Description is the same as before!");
    }

    // Etablir le nom de l'équipe
    public void setTeamName(String teamName) throws Exception {
        if(!TeamName.equals(teamName))
            TeamName = teamName;
        else throw new Exception("TeamName is the same as before!");
    }
    public void setTeamCoach(Coach c){
        this.TeamCoach = c;
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
