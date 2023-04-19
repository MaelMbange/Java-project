package ProjetEsport.HCS.Classes.Evenements;

import java.util.ArrayList;
import java.util.Objects;

import ProjetEsport.HCS.Classes.Participants.*;
public class Tournament {

    private ArrayList<Matches> matches;
    private ArrayList<Members> membres;
    private ArrayList<Players> joueurs;
    private ArrayList<Coach> coachs;
    private ArrayList<Teams> equipes;


    // faire des tests pour savoir si il faut ou non tout mettre dans members, ou non.
    // ensuite trier par type encodé lorsque l'on veut recuperer dans l'interface
    public Tournament(){
        matches = new ArrayList<Matches>();
        membres = new ArrayList<Members>();
        joueurs = new ArrayList<Players>();
        coachs = new ArrayList<Coach>();
        equipes = new ArrayList<Teams>();
    }

    public void AjouterPlayer(String pseudo, boolean estTitulaire, String nationnalite) throws Exception {
        Players p = joueurs.stream().filter(joueur -> joueur.getPseudo().equalsIgnoreCase(pseudo)).findFirst().orElse(null);
        if(p != null) throw new Exception("Le joueur existe deja !");

        p = new Players(pseudo, estTitulaire, nationnalite);
        joueurs.add(p);
        membres.add(p);
    }

    public void AjouterCoach(String pseudo, String nationnalite) throws Exception {
        Coach c = coachs.stream().filter(coach -> coach.getPseudo().equalsIgnoreCase(pseudo)).findFirst().orElse(null);
        if(c != null) throw new Exception("Le Coach existe deja !");

        c = new Coach(pseudo, nationnalite);
        coachs.add(c);
        membres.add(c);
    }

    public void AjouterEquipe(String nomEquipe, String description) throws Exception {
        Teams t = equipes.stream().filter(equipe -> equipe.getTeamName().equalsIgnoreCase(nomEquipe)).findFirst().orElse(null);
        if(t != null) throw new Exception("L'equipe existe deja !");

        equipes.add(new Teams(nomEquipe,description));
    }

    public void AjouterMembreEquipe(String pseudo, String nomEquipe) throws Exception {
        Teams t = equipes.stream().filter(equipe -> equipe.getTeamName().equalsIgnoreCase(nomEquipe)).findFirst().orElse(null);
        if(t == null) throw new Exception("L'equipe n'existe pas !");

        Members m = membres.stream().filter(membre -> membre.getPseudo().equalsIgnoreCase(pseudo)).findFirst().orElse(null);
        if(m == null) throw new Exception("Le membre n'existe pas !");

        if(m instanceof Players)
            t.addPlayers((Players)m);
        else if(m instanceof Coach)
            t.setTeamCoach((Coach)m);
    }

    public void AjouterMatch(String NomEquipeA, String NomEquipeB, int nombreRound) throws Exception {
        Teams A = equipes.stream().filter(e-> e.getTeamName().equalsIgnoreCase(NomEquipeA)).findFirst().orElse(null);
        Teams B = equipes.stream().filter(e-> e.getTeamName().equalsIgnoreCase(NomEquipeB)).findFirst().orElse(null);

        if(A == null || B == null) throw new Exception("Une des Equipes n'existe pas !");
        if(A.getTeamsPlayers().size() < 5 && B.getTeamsPlayers().size() < 5) {
            throw new Exception("Impossibles de creer un match avec des equipes vides !");
        }
        // premier test pour voir si le match A vs B existe
        Matches m = matches.stream().filter(x-> x.getA().getTeamName().equalsIgnoreCase(NomEquipeA)
                && x.getB().getTeamName().equalsIgnoreCase(NomEquipeB)).findFirst().orElse(null);
        // deuxieme test pour voir si le match B vs A existe
        if(m == null)
            m = matches.stream().filter(x-> x.getA().getTeamName().equalsIgnoreCase(NomEquipeB)
                    && x.getB().getTeamName().equalsIgnoreCase(NomEquipeA)).findFirst().orElse(null);
        if(m != null) throw new Exception("Le match existe Deja !");


        m = new Matches(A,B);
        m.setRoundNumber(nombreRound);
        matches.add(m);
        System.out.printf("Creation du match [%s] Vs [%s]\n",NomEquipeA, NomEquipeB);
    }

    public void EtablirPointPourMatch(String NomEquipeA, String NomEquipeB,Matches.RoundWonBy matchGagnePar) throws Exception {
        Matches m = matches.stream().filter(x-> x.getA().getTeamName().equalsIgnoreCase(NomEquipeA)
                && x.getB().getTeamName().equalsIgnoreCase(NomEquipeB)).findFirst().orElse(null);
        if(m == null)
            m = matches.stream().filter(x-> x.getA().getTeamName().equalsIgnoreCase(NomEquipeB)
                    && x.getB().getTeamName().equalsIgnoreCase(NomEquipeA)).findFirst().orElse(null);
        if(m == null) throw new Exception("Le match n'existe pas !");

        m.setRoundResult(matchGagnePar);
    }


    public ArrayList<Matches> getMatches() {
        return matches;
    }

    public ArrayList<Members> getMembers() {
        return membres;
    }

    public ArrayList<Players> getJoueurs() {
        return joueurs;
    }

    public ArrayList<Coach> getCoachs() {
        return coachs;
    }

    public ArrayList<Teams> getEquipes() {
        return equipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(matches, that.matches) && Objects.equals(membres, that.membres) && Objects.equals(joueurs, that.joueurs) && Objects.equals(coachs, that.coachs) && Objects.equals(equipes, that.equipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matches, membres, joueurs, coachs, equipes);
    }

    @Override
    public String toString() {
        return  "====== Joueurs ======\n" + joueurs + "\n====== Coaches ======\n" + coachs.toString() + "\n====== Equipes ======\n" + equipes;
    }

    public static void main(String argv[]) throws Exception {
        Tournament tournoi = new Tournament();

        try {
            //System.out.println("1");
            tournoi.AjouterCoach("Wagner", "Be");
            tournoi.AjouterCoach("Caprasse", "Fr");

            //System.out.println("2");
            tournoi.AjouterPlayer("PTG_1",true, "Be");
            tournoi.AjouterPlayer("PTG_2",true, "Be");
            tournoi.AjouterPlayer("PTG_3",true, "Be");
            tournoi.AjouterPlayer("PTG_4",true, "Be");
            tournoi.AjouterPlayer("PTG_5",false, "Be");

            //System.out.println("3");
            tournoi.AjouterPlayer("FUN_1",true, "Fr");
            tournoi.AjouterPlayer("FUN_2",true, "Fr");
            tournoi.AjouterPlayer("FUN_3",true, "Fr");
            tournoi.AjouterPlayer("FUN_4",true, "Fr");
            tournoi.AjouterPlayer("FUN_5",false, "Fr");

            //System.out.println("4");
            tournoi.AjouterEquipe("Requiem","We are forerunners !");
            tournoi.AjouterEquipe("Meridian", "We are Humans !");
            tournoi.AjouterEquipe("Great sharity", "We are Covenants !");

            //System.out.println("5");
            System.out.println();
            tournoi.AjouterMembreEquipe("Wagner", "Requiem");
            tournoi.AjouterMembreEquipe("PTG_1", "Requiem");
            tournoi.AjouterMembreEquipe("PTG_2", "Requiem");
            tournoi.AjouterMembreEquipe("PTG_3", "Requiem");
            tournoi.AjouterMembreEquipe("PTG_4", "Requiem");
            tournoi.AjouterMembreEquipe("PTG_5", "Requiem");

            System.out.println();
            tournoi.AjouterMembreEquipe("Caprasse", "Meridian");
            tournoi.AjouterMembreEquipe("FUN_1", "Meridian");
            tournoi.AjouterMembreEquipe("FUN_2", "Meridian");
            tournoi.AjouterMembreEquipe("FUN_3", "Meridian");
            tournoi.AjouterMembreEquipe("FUN_4", "Meridian");
            tournoi.AjouterMembreEquipe("FUN_5", "Meridian");

            System.out.println();
            tournoi.AjouterMembreEquipe("Caprasse", "Great sharity");
            tournoi.AjouterMembreEquipe("FUN_1", "Great sharity");
            tournoi.AjouterMembreEquipe("FUN_2", "Great sharity");
            tournoi.AjouterMembreEquipe("FUN_3", "Great sharity");
            tournoi.AjouterMembreEquipe("FUN_4", "Great sharity");
            tournoi.AjouterMembreEquipe("FUN_5", "Great sharity");


            System.out.println();
            tournoi.AjouterMatch("Requiem","Meridian",5);
            tournoi.EtablirPointPourMatch("Requiem","Meridian", Matches.RoundWonBy.TeamA);

            tournoi.AjouterMatch("Great sharity","Requiem",5);
            tournoi.EtablirPointPourMatch("Great sharity","Requiem", Matches.RoundWonBy.TeamA);

            System.out.println();
            //System.out.println(tournoi.coachs);
            //System.out.println(tournoi.joueurs);
            //System.out.println(tournoi.equipes);
            System.out.println(tournoi.matches);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
        }

    }
}