package ProjetEsport.Graphics.GUI.model;

import ProjetEsport.HCS.Classes.Evenements.Matches;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class modelTableMatch extends AbstractTableModel {

    private List<Matches> matches;
    private String[] nomColonnes = {"Equipe A","Equipe B", "Score A", "Score B", "Etat du match"};

    public modelTableMatch(List<Matches> matches){
        this.matches = matches;
    }

    @Override
    public int getRowCount() {
        return matches.size();
    }

    @Override
    public int getColumnCount() {
        return nomColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Matches match = matches.get(rowIndex);

        switch(columnIndex){
            case 0:
                return match.getA().getTeamName();
            case 1:
                return match.getB().getTeamName();
            case 2:
                return match.getScoreA();
            case 3:
                return match.getScoreB();
            case 4:
                if(match.isMatchActive())
                    return "En cours";
                else return "Est termine";
           default:
               return null;
       }
    }

    @Override
    public String getColumnName(int column) {
        return nomColonnes[column];
    }

    public Matches getRowMatch(int RowIndex){
        return matches.get(RowIndex);
    }
}
