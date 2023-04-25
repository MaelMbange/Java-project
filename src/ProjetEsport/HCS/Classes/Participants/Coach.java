package ProjetEsport.HCS.Classes.Participants;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class Coach extends Members implements Cloneable{

    public Coach(){}
    public Coach(String pseudo, String Location){
        super();
        this.Pseudo = pseudo;
        this.setNationality(Location);
    }

    public Coach(int id, String pseudo, String Location){
        super(id);
        this.Pseudo = pseudo;
        this.setNationality(Location);
    }
    @Override
    public String toString() {
        return String.format("*\t%04d [%-10s] %-20s - %s\n",this.getID(), this.getPseudo(), this.getNationality(), this.getRegisterTime());
    }
    @Override
    public Coach clone() {
        try
        {
            return (Coach) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    public static void main(String argv[]){
        Coach c = new Coach("PTG","Be");
        Coach cc = c.clone();

        System.out.println(c);
        System.out.println(cc);
    }
}
