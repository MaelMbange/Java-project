package ProjetEsport.HCS.Classes.Participants;

import java.text.SimpleDateFormat;
import java.util.*;


public class Players extends Members implements Comparable<Players>
{
    // Contient tout les identifiants des objets existants (des Joueurs existants)
    //private static int CurrentId = 0;

    //private int ID;
    private String Pseudo;
    private Calendar RegisterTime;
    private Locale Nationality;
    private Status Role;

    private Players(){
        super();
        this.RegisterTime = Calendar.getInstance();
    }
    public Players(String Pseudo,boolean isStarter){
        super();
        //CurrentId++;
        //this.ID = CurrentId;
        this.Pseudo = Pseudo;
        this.Nationality = Locale.getDefault(); //new Locale("en",country);
        this.RegisterTime = Calendar.getInstance();

        if(isStarter)
            this.Role = Status.STARTER;
        else
            this.Role = Status.SUBSTITUTE;
    }

    /*************************************************/
    /* Permet de récupérer une instance de la classe */
    /*************************************************/
    public static Players GetInstance(){
        return new Players();
    }

    /****************************** Get Members Function ************************************/
    public int getID() {
        return ID;
    }
    public String s_getID(){
        return String.format("%04d",ID);
    }
    public String getPseudo() {
        return Pseudo;
    }
    public String getRegisterTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy");
        return sdf.format(RegisterTime.getTime());
    }

    // Permet de récupérer les informations sous un certain format d'ecriture
    public String getRegisterTime(Locale selectedLocation) {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy",selectedLocation);
        return sdf.format(RegisterTime.getTime());
    }
    public String getNationality(Locale selectedLocation) {
        return Nationality.getDisplayCountry(selectedLocation);
    }

    public String getNationality() {
        return Nationality.getDisplayCountry();
    }
    public Status getRole() {
        return Role;
    }
    /****************************************************************************************/

    /****************************** Set Members Function ************************************/

    private Players setId(int id){
        this.ID = id;
        return this;
    }
    public Players setPseudo(String pseudo) {
        Pseudo = pseudo;
        return this;
    }
    public Players setRegisterTime(int Day, int Month, int Year) {
        this.RegisterTime.clear();
        RegisterTime.set(Year,Month,Day);
        return this;
    }
    public Players setNationality(String CountryCode) {
        Nationality = new Locale("en",CountryCode);
        return this;
    }
    public Players setRole(Status role) {
        Role = role;
        return this;
    }
    /***************************************************************************************/

    @Override
    public int compareTo(Players o) {
        return this.Pseudo.compareTo(o.Pseudo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players = (Players) o;
        return ID == players.ID && Objects.equals(Pseudo, players.Pseudo) && Objects.equals(RegisterTime, players.RegisterTime) && Objects.equals(Nationality, players.Nationality) && Role == players.Role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Pseudo, RegisterTime, Nationality, Role);
    }

    @Override
    public String toString() {
        return "*\t" + this.s_getID() + " " + this.getPseudo() + " " + this.getNationality() + "\n\t" + this.getRegisterTime();
    }

    public enum Status{
        STARTER,
        SUBSTITUTE
    }


    public static void main(String argv[]){
        Players p = Players.GetInstance().setPseudo("Curim").setNationality("Be").setRole(Status.STARTER);//.setRegisterTime(3,4,2023).setId(1);
        Players q = Players.GetInstance().setPseudo("Ice").setNationality("Fr").setRole(Status.STARTER);//.setRegisterTime(3,4,2023).setId(1);

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
