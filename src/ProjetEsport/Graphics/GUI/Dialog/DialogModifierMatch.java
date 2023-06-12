package ProjetEsport.Graphics.GUI.Dialog;

import ProjetEsport.HCS.Classes.Evenements.Matches;

import javax.swing.*;
import java.awt.event.*;

public class DialogModifierMatch extends JDialog {
    private JPanel contentPane;
    private JButton buttonValider;
    private JButton buttonAnnuler;
    private JCheckBox checkBoxStatus;
    private JSlider sliderA;
    private JSlider sliderB;
    private JLabel labelScoreA;
    private JLabel labelScoreB;
    private JLabel labelEquipeA;
    private JLabel labelEquipeB;

    private Matches match;

    public DialogModifierMatch() {
        setContentPane(contentPane);
        setModal(true);

        setTitle("Modifier Equipe");

        this.setSize(600,600);
        this.setResizable(false);

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

        sliderA.addChangeListener(e->{
            updateSliderB();
        });

        sliderB.addChangeListener(e->{
            labelScoreB.setText(String.valueOf(sliderB.getValue()));
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
        match.setScoreA(Integer.parseInt(labelScoreA.getText()));
        match.setScoreB(Integer.parseInt(labelScoreB.getText()));
        match.setStatus(!checkBoxStatus.isSelected());

        setVisible(false);
        //dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void updateSliderB(){
        labelScoreA.setText(String.valueOf(sliderA.getValue()));
        sliderB.setMaximum(match.getROundNumber()-sliderA.getValue());
    }

    public void setInformation(Matches m){
        match = m;

        labelEquipeA.setText(match.getA().getTeamName());
        labelEquipeB.setText(match.getB().getTeamName());

        labelScoreA.setText(String.valueOf(match.getScoreA()));
        labelScoreB.setText(String.valueOf(match.getScoreB()));

        sliderA.setMaximum(match.getROundNumber());
        sliderA.setMinimum(0);
        sliderA.setValue(match.getScoreA());

        sliderB.setMaximum(match.getROundNumber());
        sliderB.setMinimum(0);

        checkBoxStatus.setSelected(!match.isMatchActive());

        pack();
    }

    public static void main(String[] args) {
        DialogModifierMatch dialog = new DialogModifierMatch();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
