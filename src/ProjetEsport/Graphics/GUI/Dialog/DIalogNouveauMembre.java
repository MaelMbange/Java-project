package ProjetEsport.Graphics.GUI.Dialog;

import ProjetEsport.HCS.Classes.Participants.Coach;
import ProjetEsport.HCS.Classes.Participants.Players;
import ProjetEsport.HCS.Classes.Participants.Teams;

import javax.swing.*;
import java.awt.event.*;

public class DIalogNouveauMembre extends JDialog {
    private JPanel contentPane;

    private JButton buttonValider;
    private JButton buttonAnnuler;

    private JTextField textFieldPseudo;

    private ButtonGroup group;
    private JRadioButton radioTitulaire;
    private JRadioButton radioRemplacant;

    private JComboBox comboPays;
    private JCheckBox checkBoxCoach;

    private Teams equipeEnCours;

    public DIalogNouveauMembre() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Ajouter Membre");

        this.setSize(600,600);
        this.setResizable(false);


        comboPays.addItem("Belgium");
        comboPays.addItem("France");
        comboPays.addItem("United States");
        comboPays.addItem("Japan");
        comboPays.addItem("Dutchland");
        comboPays.addItem("Germania");
        comboPays.addItem("England");

        group = new ButtonGroup();
        group.add(radioTitulaire);
        group.add(radioRemplacant);

        checkBoxCoach.setSelected(true);
        radioTitulaire.setEnabled(false);
        radioRemplacant.setEnabled(false);

        checkBoxCoach.addActionListener(e->{
            if(checkBoxCoach.isSelected()){
                radioTitulaire.setEnabled(false);
                radioRemplacant.setEnabled(false);
            }
            else{
                radioTitulaire.setEnabled(true);
                radioRemplacant.setEnabled(true);
                radioTitulaire.setSelected(true);
            }
        });

        buttonValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        //dispose();
        if(checkBoxCoach.isSelected()){
            if(!textFieldPseudo.getText().isEmpty()){
                equipeEnCours.setTeamCoach(new Coach(textFieldPseudo.getText(),(String)comboPays.getSelectedItem()));
                setVisible(false);
            }
            else
                JOptionPane.showMessageDialog(this,"Le pseudo ne peut pas etre vide!","Equipe",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try{
                if(!textFieldPseudo.getText().isEmpty()){
                    equipeEnCours.AjouterJoueur(new Players(textFieldPseudo.getText(),radioTitulaire.isSelected(),(String)comboPays.getSelectedItem()));
                    System.out.println(textFieldPseudo.getText() + " " + radioTitulaire.isSelected() + " " + (String)comboPays.getSelectedItem());
                    setVisible(false);
                }
                else
                    JOptionPane.showMessageDialog(this,"Le pseudo ne peut pas etre vide!","Equipe",JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception a){
                JOptionPane.showMessageDialog(this,a.getMessage(),"Equipe",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void setInformation(Teams equipe){
        equipeEnCours = equipe;
    }

    public static void main(String[] args) {
        DIalogNouveauMembre dialog = new DIalogNouveauMembre();
        //dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
