package ProjetEsport.Graphics.GUI.model;

import ProjetEsport.HCS.Classes.Participants.Players;
import ProjetEsport.HCS.Classes.Participants.Teams;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import java.util.List;

public class modelTableEquipe extends AbstractTableModel {

    private List<Teams> equipes;
    private String[] nomColonnes = {"Nom de l'equipe","Description"};

    public modelTableEquipe(List<Teams> equipes){
        this.equipes = equipes;
        this.equipes.sort(Teams::compareTo);
    }

    @Override
    public int getRowCount() {
        return equipes.size();
    }

    @Override
    public int getColumnCount() {
        return nomColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Teams equipe = equipes.get(rowIndex);

        switch(columnIndex){
            case 0:
                return equipe.getTeamName();
            case 1:
                return equipe.getDescription();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nomColonnes[column];
    }

    public Teams getRowTeams(int RowIndex){
        return equipes.get(RowIndex);
    }
}
