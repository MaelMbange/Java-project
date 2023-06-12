package ProjetEsport.Graphics.GUI.Dialog;

import ProjetEsport.HCS.Classes.Evenements.Matches;

import javax.swing.*;
import java.awt.event.*;

public class DialogMatchAffichage extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JLabel LabelEquipeA;
    private JLabel LabelEquipeB;
    private JLabel LabelScoreA;
    private JLabel LabelScoreB;
    private JLabel LabelStatus;

    public DialogMatchAffichage() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Affichage Match");

        this.setSize(600,250);
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

    public DialogMatchAffichage(Matches m){
        setContentPane(contentPane);
        setModal(true);
        setTitle("Affichage Match");

        this.setSize(600,250);
        this.setResizable(false);

       setInformation(m);

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

    public void setInformation(Matches m){
        LabelEquipeA.setText(m.getA().getTeamName());
        LabelEquipeB.setText(m.getB().getTeamName());
        LabelScoreA.setText(String.valueOf(m.getScoreA()));
        LabelScoreB.setText(String.valueOf(m.getScoreB()));
        LabelStatus.setText((m.isMatchActive())?"En cours":"Est termine");
        pack();
    }

    public static void main(String[] args) {
        DialogMatchAffichage dialog = new DialogMatchAffichage();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
