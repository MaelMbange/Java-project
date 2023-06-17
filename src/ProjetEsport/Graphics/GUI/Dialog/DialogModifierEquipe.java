package ProjetEsport.Graphics.GUI.Dialog;

import ProjetEsport.Graphics.GUI.model.modelTableParticipantAffichage;
import ProjetEsport.HCS.Classes.GestionFichier.GestionnaireFichier;
import ProjetEsport.HCS.Classes.Participants.Members;
import ProjetEsport.HCS.Classes.Participants.Players;
import ProjetEsport.HCS.Classes.Participants.Teams;

import javax.swing.*;
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
    private JButton changerImageButton;
    private JButton importerButton;
    private JButton exporterButton;

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
            if(textFieldNomEquipe.getText() != null || textFieldNomEquipe.getText().isBlank()){
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

                    EquipeEnCours.setImage(EquipeAttente.getImage());

                    setVisible(false);
                    dispose();
                }
                catch(Exception a){
                    JOptionPane.showMessageDialog(this,a.getMessage(),"Equipe",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        changerImageButton.addActionListener(e->{
            JFileChooser selecteurFichier = new JFileChooser("src/ProjetEsport/Graphics/Images");
            int valeur = selecteurFichier.showOpenDialog(this);
            if(valeur == JFileChooser.APPROVE_OPTION){
                EquipeAttente.setImage(selecteurFichier.getSelectedFile().getAbsolutePath());
            }
            else{
                EquipeAttente.setImage("src/ProjetEsport/Graphics/Images/logo.png");
            }

            Image image = new ImageIcon(this.EquipeAttente.getImage()).getImage();
            Image resisedImage = image.getScaledInstance(100,100,Image.SCALE_SMOOTH);

            this.ImageEquipe.setIcon(new ImageIcon(resisedImage));
        });

        importerButton.addActionListener(e->{

            GestionnaireFichier.importerMembres(EquipeAttente);

            tableMembres.removeAll();
            tableMembres.setModel(new modelTableParticipantAffichage(EquipeAttente));

        });

        exporterButton.addActionListener(e->{

            GestionnaireFichier.exporterMembres(EquipeAttente);

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

        Image image = new ImageIcon(this.EquipeAttente.getImage()).getImage();
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
