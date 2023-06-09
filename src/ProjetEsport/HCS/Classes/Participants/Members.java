package ProjetEsport.HCS.Classes.Participants;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class Members implements Comparable<Members>, Cloneable, Serializable {
    protected int ID;
    protected static int CurrentId = 1;
    protected LocalDate RegisterTime = LocalDate.now();
    protected Locale Nationality;
    protected String Pseudo;
    protected Teams equipe;

    public Members(){
        this.ID = CurrentId;
        CurrentId++;
        this.Nationality = Locale.getDefault(); //new Locale("en",country);
        Pseudo = "Default";
        RegisterTime = LocalDate.now();
        equipe = null;
    }

    public Members(int id){
        this.ID = id;
        RegisterTime = LocalDate.now();
        equipe = null;
    }

    // ================================== GETTERS ======================================
    public String getRegisterTimeString(Locale selectedLocation) {
        DateTimeFormatter sdf =  DateTimeFormatter.ofPattern("d MMMM, yyyy");
        return sdf.format(RegisterTime);
    }

    public String getRegisterTimeString() {
        DateTimeFormatter sdf =  DateTimeFormatter.ofPattern("d MMMM, yyyy");
        return sdf.format(RegisterTime);
    }

    public String getRegisterNormalFormat(){
        DateTimeFormatter sdf =  DateTimeFormatter.ofPattern("dd,MM,yy");
        return sdf.format(RegisterTime);
    }

    public LocalDate getRegisterTime(){
        return RegisterTime;
    }

    public int getID() {
        return ID;
    }
    public String s_getID(){
        return String.format("%04d",ID);
    }

    public String getNationality(Locale selectedLocation) {
        return Nationality.getDisplayCountry(selectedLocation);
    }
    public String getNationality() {
        return Nationality.getDisplayCountry();
    }

    public String getPseudo() {
        return Pseudo;
    }

    public Teams getEquipe() {
        return equipe;
    }


    // =====================================================================================
    public void setNationality(String CountryCode) {
        Nationality = new Locale("en",CountryCode);
    }
    public void setId(int id){
        this.ID = id;
    }
    public void setCurrentId(int CURRENTID){
        CurrentId = CURRENTID;
    }
    public void setPseudo(String pseudo) {
        Pseudo = pseudo;
    }

    public void setRegisterTime(int Jour, int Mois, int Annee){
        RegisterTime = LocalDate.of(Annee,Mois,Jour);
        System.out.println(RegisterTime);
    }

    public void setRegisterTime(LocalDate registerTime) {
        RegisterTime = registerTime;
    }

    public void setEquipe(Teams equipe) {
        this.equipe = equipe;
    }

    // =====================================================================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Members members = (Members) o;
        return ID == members.ID && Objects.equals(RegisterTime, members.RegisterTime) && Objects.equals(Nationality, members.Nationality) && Objects.equals(Pseudo, members.Pseudo);
    }

    @Override
    public int compareTo(@NotNull Members o) {
        return Integer.compare(this.ID,o.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, RegisterTime, Nationality, Pseudo);
    }

    @Override
    public Object clone() {
        try {
            return (Members) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] argv){
        Members m = new Players("PTG_1",true,"Be");
        m.setRegisterTime(10, 3, 2001);
        System.out.println(m);

        m = new Coach("Wagner","Be");
        System.out.println(m);
    }
}
