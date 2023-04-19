package ProjetEsport.HCS.Classes.Participants;

import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public abstract class Members {
    protected int ID;
    protected static int CurrentId = 1;
    protected final Calendar RegisterTime = Calendar.getInstance();
    protected Locale Nationality;

    protected String Pseudo;

    public Members(){
        this.ID = CurrentId;
        CurrentId++;
        this.Nationality = Locale.getDefault(); //new Locale("en",country);
        Pseudo = "Default";
    }
    public Members(int id){
        this.ID = id;
    }

    // ================================== GETTERS ======================================
    public String getRegisterTime(Locale selectedLocation) {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy",selectedLocation);
        return sdf.format(RegisterTime.getTime());
    }

    public String getRegisterTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy");
        return sdf.format(RegisterTime.getTime());
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


    // =====================================================================================
    public void setNationality(String CountryCode) {
        Nationality = new Locale("en",CountryCode);
    }
    protected void setId(int id){
        this.ID = id;
    }

    public void setPseudo(String pseudo) {
        Pseudo = pseudo;
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
    public int hashCode() {
        return Objects.hash(ID, RegisterTime, Nationality, Pseudo);
    }

    public static void main(String[] argv){
        Members m = new Players("PTG_1",true,"Be");
        System.out.println(m);

        m = new Coach("Wagner","Be");
        System.out.println(m);
    }
}
