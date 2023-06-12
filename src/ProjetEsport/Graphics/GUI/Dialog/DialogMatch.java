package ProjetEsport.Graphics.GUI.Dialog;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

import ProjetEsport.Graphics.GUI.PanelAjouterMatch;
import ProjetEsport.HCS.Classes.Participants.Teams;

public class DialogMatch extends JDialog {
    private PanelAjouterMatch panel1;
    private JPanel panelButton;
    private JButton buttonValider,buttonAnnuler;
    private boolean etatValide;

    private void initComponent(){
        buttonValider = new JButton("Valider");
        buttonAnnuler = new JButton("Annuler");

        buttonValider.setMaximumSize(new Dimension(100,20));

        panel1 = new PanelAjouterMatch();
        panelButton = createButtonPanel(buttonValider,buttonAnnuler);

        this.setLayout(new GridBagLayout());
        this.setSize(600,250);
        this.setResizable(false);
        ImageIcon img = new ImageIcon("src/ProjetEsport/Graphics/Images/logo.png");

        this.setIconImage(img.getImage());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.8;
        this.add(panel1,gridBagConstraints);

        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.gridy = 1;
        this.add(panelButton,gridBagConstraints);

        etatValide = false;

        buttonAnnuler.addActionListener(e -> {
            setVisible(false);
            etatValide = false;
        });

        buttonValider.addActionListener(e -> {
            setVisible(false);
            etatValide = true;
        });
    }

    public DialogMatch(JFrame daddy, String titre, boolean modal,Hashtable<String, Teams> listeEquipe){
        super(daddy,titre,modal);
        if(listeEquipe != null){
            setLocationRelativeTo(daddy);
            initComponent();
            panel1.setComboBox(listeEquipe);
        }
    }

    private JPanel createButtonPanel(JButton button1, JButton button2) {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(button1);
        panel.add(button2);
        return panel;
    }

    // ======================= Getters ======================
    public PanelAjouterMatch getPanelSelection() {
        return panel1;
    }
    public JButton getButtonValider() {
        return buttonValider;
    }
    public JButton getButtonAnnuler() {
        return buttonAnnuler;
    }
    public boolean getEtatDialog(){
        return etatValide;
    }
    public String getComboBoxASelectedItem(){
        return (String)panel1.getEquipeA().getSelectedItem();
    }
    public String getComboBoxBSelectedItem(){
        return (String)panel1.getEquipeB().getSelectedItem();
    }
    public int getNombreDeManche(){return panel1.getNombreDeMancheInt();}
    // =====================================================

    public static void main(String[] argv){
        Hashtable<String, Teams> e = new Hashtable<>();
        e.put("EquipeA",new Teams("EquipeA"));
        e.put("EquipeB",new Teams("EquipeB"));
        e.put("EquipeC",new Teams("EquipeC"));
        e.put("EquipeD",new Teams("EquipeD"));

        DialogMatch nd = new DialogMatch(null,"Ajout d'un match",true,e);
        //nd.pack();
        nd.setVisible(true);
        if(nd.getEtatDialog()){
            JDialog j = new JDialog(nd,"Dialog etat True",true);
            j.setLocation(200,200);
            j.setSize(200,200);
            j.setVisible(true);
            j.dispose();
        }
        nd.dispose();
    }
}
