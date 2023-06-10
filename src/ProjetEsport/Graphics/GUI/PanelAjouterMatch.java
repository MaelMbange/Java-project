package ProjetEsport.Graphics.GUI;

import ProjetEsport.HCS.Classes.Evenements.Matches;
import ProjetEsport.HCS.Classes.Participants.Teams;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Hashtable;

public class PanelAjouterMatch extends JPanel {

    private JComboBox<String> EquipeA;
    private JComboBox<String> EquipeB;
    private final JComboBox<Integer> nombreDeManches = new JComboBox<>(new Integer[]{1,2,3,4,5});
    private final JLabel labelEquipes = new JLabel("Selectionner les equipes : ");


    private void initComponent(){
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        EquipeA = new JComboBox<>();
        EquipeB = new JComboBox<>();

        EquipeA.setMaximumSize(new Dimension(100,20));
        EquipeB.setMaximumSize(new Dimension(100,20));

        labelEquipes.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        JLabel jl = new JLabel("VS",JLabel.CENTER);

        JPanel panelCombo = new JPanel();
        panelCombo.setLayout(new BoxLayout(panelCombo, BoxLayout.LINE_AXIS));
        panelCombo.add(Box.createRigidArea(new Dimension(20,0)));
        panelCombo.add(labelEquipes);
        panelCombo.add(Box.createRigidArea(new Dimension(20,0)));
        panelCombo.add(EquipeA);
        panelCombo.add(Box.createRigidArea(new Dimension(20,0)));
        panelCombo.add(jl);
        panelCombo.add(Box.createRigidArea(new Dimension(20,0)));
        panelCombo.add(EquipeB);
        panelCombo.add(Box.createRigidArea(new Dimension(20,0)));

        nombreDeManches.setMaximumSize(new Dimension(100,20));
        JPanel panelManche = new JPanel();
        panelManche.add(Box.createRigidArea(new Dimension(20,0)));
        panelManche.setLayout(new BoxLayout(panelManche,BoxLayout.LINE_AXIS));
        JLabel jm = new JLabel("Nombre de manche          : ");
        jm.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelManche.add(jm);
        panelManche.add(Box.createRigidArea(new Dimension(20,0)));
        panelManche.add(nombreDeManches);

        panelCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelManche.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(panelCombo);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(panelManche);
        this.add(Box.createVerticalGlue());
        EquipeA.addActionListener(e-> updateComboBoxB());
    }

    public PanelAjouterMatch(){
        super();
        initComponent();
    }

    // ==========================================================

    public void setComboBox(Hashtable<String, Teams> A){
        for(Teams equipe : A.values()){
            EquipeA.addItem(equipe.getTeamName());
            EquipeB.addItem(equipe.getTeamName());
        }
        EquipeB.removeItem(EquipeA.getSelectedItem());
    }

    // ===================== methode d'update combo box ===================

    public void updateComboBoxB(){
        EquipeB.removeAllItems();

        String selectedItem = (String)EquipeA.getSelectedItem();

        if(selectedItem != null){
            for(int i = 0; i < EquipeA.getItemCount(); i++){
                String item = EquipeA.getItemAt(i);
                if(!item.equals(selectedItem))
                    EquipeB.addItem(item);
            }
        }
    }

    // ====================================================================
    // ============== getters ========================
    public JComboBox<String> getEquipeA() {
        return EquipeA;
    }

    public JComboBox<String> getEquipeB() {
        return EquipeB;
    }

    public JComboBox<Integer> getNombreDeManches() {
        return nombreDeManches;
    }
    // ===============================================

    public static void main(String[] argv){
        JFrame mf = new JFrame();
        Teams[] e = new Teams[]{new Teams("A"),new Teams("B")};
        mf.setContentPane(new PanelAjouterMatch());
        mf.pack();
        mf.setVisible(true);
    }
}
