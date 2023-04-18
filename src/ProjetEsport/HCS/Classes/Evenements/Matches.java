package ProjetEsport.HCS.Classes.Evenements;

import ProjetEsport.HCS.Classes.Participants.*;

import java.util.Objects;

public class Matches {
    private Teams A;
    private Teams B;

    private int ScoreA;
    private int ScoreB;

    private int RoundNumber;

    private boolean StateOfMatch;
    private Matches(){}

    public Matches(Teams a, Teams b){
        this.A = a;
        this.B = b;
        ScoreA = 0;
        ScoreB = 0;
        RoundNumber = 0;
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

    // ========================== SETTERS ===========================================
    public void setA(Teams a) {
        A = a;
    }

    public void setB(Teams b) {
        B = b;
    }

    public void SetRoundResult(RoundWonBy result) {
        if(!StateOfMatch) return;

        if(ScoreA < RoundNumber/2+1 || ScoreB < RoundNumber/2+1){
            if(result == RoundWonBy.Team1)
                ScoreA++;
            else if(result == RoundWonBy.Team2)
                ScoreB++;
        }
        if(ScoreA >= RoundNumber/2+1 || ScoreB >= RoundNumber/2+1){
            StateOfMatch = false;
        }
    }

    public void setRoundNumber(int roundNumber) {
        RoundNumber = roundNumber;
    }

    @Override
    public String toString() {
        return "[" + this.A.getTeamName() + "] Vs [" + this.B.getTeamName() + "]\n" + String.format("%10d - %d", this.ScoreA, this.ScoreB);
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
        Team1,
        Team2,
        Equality
    }


    public static void main(String[] argv){

        Players[] plist = {
                Players.GetInstance().setPseudo("A").setNationality("Be").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("B").setNationality("Fr").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("C").setNationality("Ge").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("D").setNationality("Us").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("E").setNationality("Uk").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("F").setNationality("Sw").setRole(Players.Status.STARTER),
                //Players.GetInstance().setPseudo("G").setNationality("Br").setRole(Players.Status.STARTER),
                //Players.GetInstance().setPseudo("H").setNationality("Es").setRole(Players.Status.STARTER),
        };

        Players[] plist2 = {
                Players.GetInstance().setPseudo("G").setNationality("Be").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("H").setNationality("Fr").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("I").setNationality("Ge").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("J").setNationality("Us").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("K").setNationality("Uk").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("L").setNationality("Sw").setRole(Players.Status.STARTER),
                Players.GetInstance().setPseudo("G").setNationality("Br").setRole(Players.Status.STARTER),
                //Players.GetInstance().setPseudo("H").setNationality("Es").setRole(Players.Status.STARTER),
        };

        Teams t = new Teams("Quadrant");
        Teams t2 = new Teams("Sentinels");

        try{
            System.out.println("TeamA");
            for (Players x: plist) {
                t.addPlayers(x);
            }

            System.out.println("\nTeamB");
            for (Players x: plist2) {
                t2.addPlayers(x);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        Matches m = new Matches(t, t2);
        m.setRoundNumber(5);

        System.out.println();
        System.out.println("Score actuel du match : ");
        System.out.println();
        System.out.printf("[%s] Vs [%s]%n", m.A.getTeamName(), m.B.getTeamName());
        System.out.printf("%10d - %d%n", m.ScoreA, m.ScoreB);


        // Actualisation du score

        m.SetRoundResult(RoundWonBy.Team1);
        m.SetRoundResult(RoundWonBy.Team1);
        m.SetRoundResult(RoundWonBy.Team2);
        m.SetRoundResult(RoundWonBy.Team1);
        m.SetRoundResult(RoundWonBy.Team1);

        System.out.println();
        System.out.println("Score actuel du match : \n");
        System.out.println(m);



    }
}
