package ProjetEsport.Graphics.GUI.model;

import ProjetEsport.HCS.Classes.Participants.Coach;
import ProjetEsport.HCS.Classes.Participants.Members;
import ProjetEsport.HCS.Classes.Participants.Players;
import ProjetEsport.HCS.Classes.Participants.Teams;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class modelTableParticipant extends AbstractTableModel {

    private List<Members> participants;
    private final String[] nomColonnes = {"Pseudo","Date enregistrement","Nationnalite","Type","Nom equipe"};

    public modelTableParticipant(List<Teams> equipes){
        participants = new ArrayList<>();
        for(Teams equipe : equipes){

            participants.addAll(equipe.getTeamsPlayers());
            if(equipe.getTeamCoach() != null)
                participants.add(equipe.getTeamCoach());
            participants.sort(Members::compareTo);
            //System.out.println("model participant" +equipe);
        }
    }

    @Override
    public int getRowCount() {
        return participants.size();
    }

    @Override
    public int getColumnCount() {
        return nomColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Members membre = participants.get(rowIndex);

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
            case 4:
                return membre.getEquipe().getTeamName();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return nomColonnes[column];
    }

    public Members getRowMembers(int RowIndex){
        return participants.get(RowIndex);
    }
}
