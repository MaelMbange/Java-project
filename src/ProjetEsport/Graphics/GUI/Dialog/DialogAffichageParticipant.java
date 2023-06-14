package ProjetEsport.Graphics.GUI.Dialog;

import ProjetEsport.Graphics.GUI.model.modelTableMatch;
import ProjetEsport.Graphics.GUI.model.modelTableParticipantAffichage;
import ProjetEsport.HCS.Classes.Evenements.Matches;
import ProjetEsport.HCS.Classes.Participants.Coach;
import ProjetEsport.HCS.Classes.Participants.Members;
import ProjetEsport.HCS.Classes.Participants.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogAffichageParticipant extends JDialog {
    private JPanel contentPane;

    private JButton buttonCancel;
    private JLabel LabelPseudo;
    private JLabel LabelRole;
    private JLabel LabelStatus;
    private JPanel PanelStatus;
    private JLabel LabelDate;
    private JLabel LabelNationalite;
    private JTable table1;
    private JLabel labelNomEquipe;

    public DialogAffichageParticipant() {
        setContentPane(contentPane);
        setModal(true);

        setTitle("Afficher Membre");

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

        table1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int selectedRow = table1.getSelectedRow();

                    modelTableParticipantAffichage mtpa = (modelTableParticipantAffichage)table1.getModel();
                    Members membreSelected = mtpa.getRowMembers(selectedRow);

                    setInformation(membreSelected);
                }
            }
        });
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void setInformation(Members members){
        LabelPseudo.setText(members.getPseudo());
        LabelRole.setText(members instanceof Coach? "Coach" : "Joueur");
        if(members instanceof Players){
            PanelStatus.setVisible(true);
            LabelStatus.setText(((Players)members).getRole().name());
        }
        else
            PanelStatus.setVisible(false);

        LabelDate.setText(members.getRegisterTimeString());
        LabelNationalite.setText(members.getNationality());

        labelNomEquipe.setText(members.getEquipe().getTeamName());
        table1.setModel(new modelTableParticipantAffichage(members.getEquipe()));

        Image image = new ImageIcon(members.getEquipe().getImage()).getImage();
        Image resisedImage = image.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        labelNomEquipe.setIcon(new ImageIcon(resisedImage));

    }

    public static void main(String[] args) {
        DialogAffichageParticipant dialog = new DialogAffichageParticipant();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
