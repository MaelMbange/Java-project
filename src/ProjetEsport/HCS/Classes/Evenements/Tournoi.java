package ProjetEsport.HCS.Classes.Evenements;
import ProjetEsport.HCS.Classes.Participants.*;

import java.util.Hashtable;

public class Tournoi {
    private Hashtable<String,Matches> tableMatch;
    private Hashtable<String, Teams> tableEquipe;
    private String NomTournoi;

    private static Tournoi instance = null;

    private Tournoi(){
        tableEquipe = new Hashtable<>();
        tableMatch = new Hashtable<>();
        NomTournoi = "";
    }
    public static Tournoi getInstance(){
        if(instance != null) return instance;
        return new Tournoi();
    }

    public void setNomTournois(String nomTournoi){
        NomTournoi = nomTournoi;
    }

    public String getNomTournois(){
        return NomTournoi;
    }

    //============= Partie Matche =============

    public void AjouterMatch(Teams a, Teams b, int roundNumber){
        if(!(tableMatch.containsKey(a.getTeamName()+" - "+b.getTeamName()) || tableMatch.containsKey(b.getTeamName()+" - "+a.getTeamName()))){
            tableMatch.put(a.getTeamName()+" - "+b.getTeamName(),new Matches(a,b,roundNumber));
        }
    }

    public void ModifierScoreMatch(String matchKey, int scoreA, int scoreB){
        tableMatch.get(matchKey).setScoreA(scoreA);
        tableMatch.get(matchKey).setScoreB(scoreB);
    }

    public void RetirerMatch(Matches matches){
        String v1 = matches.getA().getTeamName() + " - " + matches.getB().getTeamName();
        String v2 = matches.getB().getTeamName() + " - " + matches.getA().getTeamName();

        if(tableMatch.containsKey(v1)){
            tableMatch.remove(v1);
        }
        else if(tableMatch.containsKey(v2)){
            tableMatch.remove(v2);
        }
    }

    public void MancheGagnePar(String nomMatch, Matches.RoundWonBy equipe){
        tableMatch.get(nomMatch).setMancheGagnePar(equipe);
    }

    public Hashtable<String,Matches> getTableMatch(){
        return tableMatch;
    }
    //=========================================

    //============= Partie Equipe =============
    public void AjouterUneEquipe(Teams equipe){
        if(!tableEquipe.containsKey(equipe.getTeamName())){
            tableEquipe.put(equipe.getTeamName(),equipe);
        }
    }

    public void AjouterUneEquipe(String teamName,String description){
        if(teamName != null && !teamName.isEmpty()){
            System.out.println("Ajout de l'equipe reussie!");
            tableEquipe.put(teamName,new Teams(teamName,description));
        }
    }

    public void RetirerUneEquipe(String nomEquipe){
        tableEquipe.remove(nomEquipe);
    }


    public void AjouterMembreEquipe(String nomEquipe, Members membre) throws Exception{
        if(tableEquipe.containsKey(nomEquipe)){
            if(membre instanceof Players)
                tableEquipe.get(nomEquipe).AjouterJoueur((Players)membre);
            else if(membre instanceof Coach)
                tableEquipe.get(nomEquipe).setTeamCoach((Coach)membre);
        }   
    }

    public Hashtable<String,Teams> getTableEquipe(){
        return tableEquipe;
    }
    //=========================================
}
