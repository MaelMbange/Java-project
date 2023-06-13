package ProjetEsport.Graphics.GUI.Dialog;

import ProjetEsport.Graphics.GUI.model.modelTableParticipantAffichage;
import ProjetEsport.HCS.Classes.Participants.Players;
import ProjetEsport.HCS.Classes.Participants.Teams;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class DialogModifierEquipe extends JDialog {
    private JPanel contentPane;

    private JButton buttonCancel;
    private JButton validerButton;

    private JTextField textFieldNomEquipe;
    private JTextArea textAreaDescription;

    private JTable tableMembres;

    private JButton ajouterUnMembreButton;
    private JLabel ImageEquipe;

    private Teams EquipeEnCours;
    private Teams EquipeAttente;


    public DialogModifierEquipe() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Modifier Equipe");

        tableMembres.setCellEditor(null);

        this.setSize(700,600);
        this.setResizable(false);
        textFieldNomEquipe.setEditable(false);


        buttonCancel.addActionListener(new ActionListener() {
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

        ajouterUnMembreButton.addActionListener(e -> {
            // Ajouter des informations de joueurs
            DIalogNouveauMembre dnm = new DIalogNouveauMembre();
            dnm.setInformation(EquipeAttente);
            dnm.setVisible(true);
            dnm.dispose();

            tableMembres.removeAll();
            tableMembres.setModel(new modelTableParticipantAffichage(EquipeAttente));

            //pack();
        });

        validerButton.addActionListener(e->{
            if(textFieldNomEquipe.getText() != null ){
                try{
                    //EquipeEnCours.setTeamName(textFieldNomEquipe.getText());
                    EquipeEnCours.setDescription(textAreaDescription.getText());

                    for(Players p : EquipeAttente.getTeamsPlayers()) {
                        if (!EquipeEnCours.getTeamsPlayers().contains(p)) {
                            EquipeEnCours.AjouterJoueur(p);
                        }
                    }
                    if(EquipeAttente.getTeamCoach() != null)
                        EquipeEnCours.setTeamCoach(EquipeAttente.getTeamCoach());

                    setVisible(false);
                    dispose();
                }
                catch(Exception a){
                    JOptionPane.showMessageDialog(this,a.getMessage(),"Equipe",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void setInformation(Teams EquipeSelectionne){
        this.EquipeEnCours = EquipeSelectionne;
        this.EquipeAttente = this.EquipeEnCours.clone();

        tableMembres.removeAll();
        this.tableMembres.setModel(new modelTableParticipantAffichage(this.EquipeAttente));

        this.textFieldNomEquipe.setText(EquipeAttente.getTeamName());
        this.textAreaDescription.setText(EquipeAttente.getDescription());

        Image image = new ImageIcon(this.EquipeEnCours.getImage()).getImage();
        Image resisedImage = image.getScaledInstance(100,100,Image.SCALE_SMOOTH);

        this.ImageEquipe.setIcon(new ImageIcon(resisedImage));

        //pack();
    }

    public static void main(String[] args) {
        DialogModifierEquipe dialog = new DialogModifierEquipe();
        //dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
