package ProjetEsport.Graphics.GUI.model;

import ProjetEsport.HCS.Classes.Participants.Coach;
import ProjetEsport.HCS.Classes.Participants.Members;
import ProjetEsport.HCS.Classes.Participants.Players;
import ProjetEsport.HCS.Classes.Participants.Teams;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class modelTableParticipantAffichage extends AbstractTableModel {

    private List<Members> membres;
    private final String[] nomColonnes = {"Id","Pseudo","Date enregistrement","Nationnalite","Type"};

    public modelTableParticipantAffichage(Teams equipe){
        membres = new ArrayList<>();
        if(!equipe.getAllMembers().isEmpty()){
            membres.addAll(equipe.getAllMembers());
            membres.sort(Members::compareTo);
            System.out.println("model participant affichage" + equipe);
        }
        else
            System.out.println("model participant Affichage condition non valide" + equipe);
    }

    @Override
    public int getRowCount() {
        return membres.size();
    }

    @Override
    public int getColumnCount() {
        return nomColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Members membre = membres.get(rowIndex);

        switch(columnIndex){
            case 0:
                return membre.getID();
            case 1:
                return membre.getPseudo();
            case 2:
                return membre.getRegisterTime();
            case 3:
                return membre.getNationality();
            case 4:
                if(membre instanceof Coach)
                    return "Coach";
                if(membre instanceof Players)
                    return "Joueur [" + ((Players) membre).getRole().name() + "]";
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return nomColonnes[column];
    }
}
