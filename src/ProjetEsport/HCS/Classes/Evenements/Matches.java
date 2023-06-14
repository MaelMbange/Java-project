package ProjetEsport.HCS.Classes.Evenements;

import ProjetEsport.HCS.Classes.Participants.*;

import java.io.Serializable;
import java.util.Objects;

public class Matches implements Serializable {
    private Teams A;
    private Teams B;

    private int ScoreA;
    private int ScoreB;

    private int RoundNumber;

    private boolean StateOfMatch;

    public Matches(){}

    public Matches(Teams a, Teams b){
        this.A = a;
        this.B = b;
        ScoreA = 0;
        ScoreB = 0;
        RoundNumber = 0;
        StateOfMatch = true;
    }

    public Matches(Teams a, Teams b,int roundNumber){
        this.A = a;
        this.B = b;
        ScoreA = 0;
        ScoreB = 0;
        RoundNumber = roundNumber;
        StateOfMatch = true;
    }


    // ========================== GETTERS ===========================================
    public Teams getA() {
        return A;
    }

    public Teams getB() {
        return B;
    }

    public int getScoreA() {
        return ScoreA;
    }

    public int getScoreB() {
        return ScoreB;
    }
    public final boolean isMatchActive() {return StateOfMatch;}

    public int getROundNumber(){return RoundNumber;}
    // ========================== SETTERS ===========================================
    public void setScoreA(int scoreA) {
       if(scoreA <= RoundNumber)
            ScoreA = scoreA;
    }

    public void setScoreB(int scoreB) {
        if(scoreB <= RoundNumber)
            ScoreB = scoreB;
    }

    public void setStatus(boolean actif){
        StateOfMatch = actif;
    }
    
    public void setA(Teams a) {
        A = a;
    }

    public void setB(Teams b) {
        B = b;
    }

    public void setMancheGagnePar(RoundWonBy result) {
        if(!StateOfMatch) return;

        if(ScoreA < RoundNumber/2+1 || ScoreB < RoundNumber/2+1){
            if(result == RoundWonBy.TeamA)
                ScoreA++;
            else if(result == RoundWonBy.TeamB)
                ScoreB++;
        }
        if(ScoreA >= RoundNumber/2+1 || ScoreB >= RoundNumber/2+1){
            StateOfMatch = false;
        }
    }

    public void setNombreManches(int roundNumber) throws Exception {
        if(roundNumber <= 5)
            RoundNumber = roundNumber;
        else throw new Exception("Le nombre de round ne peut etre superieur a 5");
    }

    public String ResultToString(){
        return String.format(" [%s] Vs [%s]\n",this.A.getTeamName(), this.B.getTeamName()) + String.format("  %10d - %d\n", this.ScoreA, this.ScoreB);
    }

    public String PlayerTableToString(){
        String s = "";
        for(int i = 0; i < 5; i++){
            s += String.format("| %-10s | %10s |\n",this.A.getTeamsPlayers().get(i).getPseudo(),this.B.getTeamsPlayers().get(i).getPseudo() );
        }
        return s;
    }

    @Override
    public String toString() {
        return ResultToString() + PlayerTableToString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matches matches = (Matches) o;
        return ScoreA == matches.ScoreA && ScoreB == matches.ScoreB && RoundNumber == matches.RoundNumber && Objects.equals(A, matches.A) && Objects.equals(B, matches.B);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A, B, ScoreA, ScoreB, RoundNumber, StateOfMatch);
    }

    public enum RoundWonBy {
        TeamA,
        TeamB,
    }


    public static void main(String[] argv) throws Exception {

        Players[] plist = {
                new Players("PTG_1",true, "Be"),
                new Players("PGT_2",true, "Be"),
                new Players("PGT_3",true, "Be"),
                new Players("PGT_4",true, "Be"),
                new Players("PGT_5",true, "Be"),
        };

        Players[] plist2 = {
                new Players("FUN_1",true, "Fr"),
                new Players("FUN_2",true, "Fr"),
                new Players("FUN_3",true, "Fr"),
                new Players("FUN_4",true, "Fr"),
                new Players("FUN_5",true, "Fr")
        };

        Teams t = new Teams("Quadrant");
        Teams t2 = new Teams("Sentinels");

        Coach[] c = {
                new Coach(),
                new Coach()
        };
        c[0].setPseudo("Wagner");

        c[1].setPseudo("Wilvers");

        try{
            System.out.println("[" + t.getTeamName() +"]");
            for (Players x: plist) {
                t.AjouterJoueur(x);
            }
            t.setTeamCoach(c[0]);

            System.out.println("\n[" + t2.getTeamName() +"]");
            for (Players x: plist2) {
                t2.AjouterJoueur(x);
            }
            t2.setTeamCoach(c[1]);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        Matches m = new Matches(t, t2);
        m.setNombreManches(5);

        System.out.println();
        System.out.println("Score actuel du match : ");
        System.out.println();
        System.out.printf(m.ResultToString());

        // Actualisation du score
        m.setMancheGagnePar(RoundWonBy.TeamA);
        m.setMancheGagnePar(RoundWonBy.TeamA);
        m.setMancheGagnePar(RoundWonBy.TeamB);
        m.setMancheGagnePar(RoundWonBy.TeamA);
        m.setMancheGagnePar(RoundWonBy.TeamA);

        System.out.println();
        System.out.println("Score actuel du match : \n");
        System.out.println(m);
    }
}
