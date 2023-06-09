package ProjetEsport.Graphics.GUI.model;

import ProjetEsport.HCS.Classes.Evenements.Matches;
import ProjetEsport.HCS.Classes.Participants.Coach;
import ProjetEsport.HCS.Classes.Participants.Members;
import ProjetEsport.HCS.Classes.Participants.Players;
import ProjetEsport.HCS.Classes.Participants.Teams;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class modelTableParticipantAffichage extends AbstractTableModel {

    private List<Members> membres;
    private final String[] nomColonnes = {"Pseudo","Date enregistrement","Nationnalite","Type"};

    public modelTableParticipantAffichage(Teams equipe){
        membres = new ArrayList<>();
        membres.addAll(equipe.getTeamsPlayers());
        if(equipe.getTeamCoach() != null)
            membres.add(equipe.getTeamCoach());
        membres.sort(Members::compareTo);
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
                return membre.getPseudo();
            case 1:
                return membre.getRegisterTime();
            case 2:
                return membre.getNationality();
            case 3:
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

    public Members getRowMembers(int RowIndex){
        return membres.get(RowIndex);
    }
}
