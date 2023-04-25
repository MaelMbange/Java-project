package ProjetEsport.Graphics.GUI;

import javax.swing.*;

public class PanelPrincipal extends JFrame {
    private JPanel panel1;
    private JPanel PanelTournoi;
    private JButton buttonAjouterMatch;
    private JButton buttonAjouterEquipe;
    private JPanel ScrollPaneTournoiNom;
    private JScrollPane ScrollBarNbrParticipant;
    private JScrollPane ScrollPaneNbrEquipe;
    private JLabel LabelNbrParticipant;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JPanel PanelBouton;
    private JButton buttonModifierMatch;
    private JButton ButtonModifierEquipe;
    // ProjetEsport/Graphics/Images/HoCHCS.png

    private void InitComponents(){

    }
    public PanelPrincipal(){


    }


    public static void main(String[] argv){
        PanelPrincipal p = new PanelPrincipal();
        p.setSize(100,100);
        p.setVisible(true);
    }
}
