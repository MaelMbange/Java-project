package ProjetEsport.HCS.Classes.Participants;

import java.util.Objects;

public class Coach extends Members{

    private String Nom;
    private String Prenom;

    public Coach(){}
    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coach coach = (Coach) o;
        return Objects.equals(Nom, coach.Nom) && Objects.equals(Prenom, coach.Prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Nom, Prenom);
    }
}
