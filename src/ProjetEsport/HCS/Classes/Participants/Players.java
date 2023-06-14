package ProjetEsport.HCS.Classes.Participants;
import java.io.Serializable;
import java.util.*;


public class Players extends Members implements Comparator<Members>, Serializable {
    private Status Role;

    public Players(){
        super();
    }
    public Players(String Pseudo, boolean isStarter, String location){
        super();
        this.Pseudo = Pseudo;
        this.setNationality(location);

        if(isStarter)
            this.Role = Status.STARTER;
        else
            this.Role = Status.SUBSTITUTE;
    }
    public Players(int Id, String Pseudo,boolean isStarter, String location){
        super(Id);
        this.Pseudo = Pseudo;
        this.setNationality(location);

        if(isStarter)
            this.Role = Status.STARTER;
        else
            this.Role = Status.SUBSTITUTE;
    }

    /****************************** Get Members Function ************************************/
    public Status getRole() {
        return Role;
    }

    /****************************************************************************************/

    /****************************** Set Members Function ************************************/
    public Players setRole(Status role) {
        Role = role;
        return this;
    }
    /***************************************************************************************/


    @Override
    public String toString() {
        return String.format("*\t%04d [%-10s] %-20s - %s [%s]\n",this.getID(), this.getPseudo(), this.getNationality(), this.getRegisterTime(), (this.getRole() == Status.STARTER)? "STARTER" : "SUBSTITUTE");
    }

    @Override
    public int compare(Members o1, Members o2) {
        return Integer.compare(o1.ID,o2.ID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Players players = (Players) o;
        return Role == players.Role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Role);
    }

    public enum Status{
        STARTER,
        SUBSTITUTE
    }

    @Override
    public Object clone() {
        Players clone = (Players) super.clone();
        clone.setRole(this.Role);

        return (Players)clone;
    }


    public static void main(String argv[]){
        Players p = new Players("PTG",true,"Be");

        Players q = (Players) p.clone();

        System.out.println(p);
        System.out.println(q);


        // demande d'égalité par reference/zone memoire (est ce qu'il s'agit du meme objet)
        if(p == q)
            System.out.println("Sont egaux");
        else
            System.out.println("ne sont pas egaux");


        // demande d'égalité par valeur
        if(p.equals(q))
            System.out.println("Sont egaux");
        else
            System.out.println("ne sont pas egaux");
    }

}
