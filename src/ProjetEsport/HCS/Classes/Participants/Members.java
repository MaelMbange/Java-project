package ProjetEsport.HCS.Classes.Participants;

import java.util.Objects;

public abstract class Members {
    protected int ID;
    protected static int CurrentId = 1;

    public Members(){
        this.ID = CurrentId;
        CurrentId++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Members members = (Members) o;
        return ID == members.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Members{" +
                "ID=" + ID +
                '}';
    }

    public static void main(String argv[]){

    }
}
