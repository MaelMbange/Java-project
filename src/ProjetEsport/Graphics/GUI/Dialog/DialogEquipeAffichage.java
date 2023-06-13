package ProjetEsport.Graphics.GUI.Dialog;

import ProjetEsport.Graphics.GUI.model.modelTableParticipantAffichage;
import ProjetEsport.HCS.Classes.Participants.Teams;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogEquipeAffichage extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JLabel labelNomEquipe;
    private JTable tableJoueur;
    private JTextPane textDescription;
    private JLabel ImageEquipe;

    public DialogEquipeAffichage() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Affichage Equipe");

        this.setSize(600,600);
        this.setResizable(false);

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
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void setInformation(Teams t){
        labelNomEquipe.setText(t.getTeamName());
        textDescription.setText(t.getDescription());
        textDescription.setEditable(false);

        tableJoueur.setModel(new modelTableParticipantAffichage(t));

        Image image = new ImageIcon(t.getImage()).getImage();
        Image resisedImage = image.getScaledInstance(100,100,Image.SCALE_SMOOTH);

        this.ImageEquipe.setIcon(new ImageIcon(resisedImage));
        //pack();
    }

    public static void main(String[] args) {
        DialogEquipeAffichage dialog = new DialogEquipeAffichage();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
