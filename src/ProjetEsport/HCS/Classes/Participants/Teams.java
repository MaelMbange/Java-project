package ProjetEsport.HCS.Classes.Participants;

import java.util.*;

public class Teams {
    private ArrayList<Players> TeamsMembers;
    private Coach TeamCoach;
    private String TeamName;
    private String Description;

    private Teams(){
    }

    // Creer une instance d'equipe
    public Teams(String Name){
        this.TeamName = Name;
        TeamsMembers = new ArrayList<Players>(5);
        Description = "You can add a description!";
        TeamCoach = new Coach();
    }

    // Ajouter un joueur
    public void addPlayers(Players player) throws Exception {
        if(TeamsMembers.size() < 5)
            if(!TeamsMembers.contains(player)){
                TeamsMembers.add(player);
                System.out.println("Joueur ajoute !");
            }
            else throw new Exception("The player your trying to add is already in the team!");
        else throw new Exception("Max amount of players has already been reached!");
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

    public void setTeamCoach(String nom, String prenom){
        this.TeamCoach.setNom(nom);
        this.TeamCoach.setPrenom(prenom);
    }


    public String getTeamName() {
        return TeamName;
    }

    public String getDescription() {
        return Description;
    }

    public int getCountPlayer(){
        return TeamsMembers.size();
    }

    public Coach getTeamCoach() {
        return TeamCoach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teams teams = (Teams) o;
        return Objects.equals(TeamsMembers, teams.TeamsMembers) && Objects.equals(TeamName, teams.TeamName) && Objects.equals(Description, teams.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TeamsMembers, TeamCoach, TeamName, Description);
    }

    public static void main(String[] argv){
        try{
            Teams teams = new Teams("Quadrant");
            teams.setDescription("We are a french team");

            Players[] players = new Players[]{
                    Players.GetInstance().setPseudo("B").setNationality("Be").setRole(Players.Status.STARTER),
                    Players.GetInstance().setPseudo("A").setNationality("Fr").setRole(Players.Status.STARTER),
                    new Players("D",true).setNationality("Au"),
                    new Players("C",true).setRegisterTime(19,Calendar.JULY,2019),
            };

            teams.TeamsMembers.addAll(List.of(players));
            teams.TeamsMembers.sort(Players::compareTo);

            Locale Place = new Locale("En");

            System.out.println();
            System.out.println("Team name : " + teams.getTeamName());
            System.out.println("Description : " + teams.getDescription());
            System.out.println();
            System.out.println("===============Membres==================");

            for (Players p : teams.TeamsMembers) {
                System.out.println();
                System.out.println( "*\t" + p.s_getID() + " " + p.getPseudo() + " " + p.getNationality(Place));
                System.out.println("\t" + p.getRegisterTime(Place));
            }
            System.out.println();

            System.out.println("========================================");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
