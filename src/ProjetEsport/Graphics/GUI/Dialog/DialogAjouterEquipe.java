package ProjetEsport.Graphics.GUI.Dialog;

import ProjetEsport.HCS.Classes.Participants.Teams;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DialogAjouterEquipe extends JDialog {
    private JPanel contentPane;

    private JButton buttonValider;
    private JButton buttonCancel;

    private JTextField textFieldNomEquipe;
    private JTextArea textAreaDescription;

    private String[] equipeExistante;

    public DialogAjouterEquipe() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Ajouter Equipe");

        this.setSize(600,450);
        this.setResizable(false);


        buttonValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

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

    private void onOK() {
        // add your code here
        if(textFieldNomEquipe.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Necessite d'un nom d'equipe!","Equipe",JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        List<String> le = new ArrayList<>(List.of(equipeExistante));
        boolean isIn = false;
        for(String s : le){
            if(s.equalsIgnoreCase(textFieldNomEquipe.getText())){
                isIn = true;
                JOptionPane.showMessageDialog(this,"L'equipe Existe deja!","Equipe",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        //Teams equipe = new Teams(textFieldNomEquipe.getText(),textAreaDescription.getText());
        setVisible(false);

        //equipe = new Teams(textFieldNomEquipe)
        //dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void setInformation(String[] equipeExistante){
        if(equipeExistante != null)
         this.equipeExistante = equipeExistante;
        else
            this.equipeExistante = new String[]{};

        //System.out.println("DAE" + Arrays.toString(equipeExistante));
    }

    public String getNomEquipe(){
        return textFieldNomEquipe.getText();
    }

    public String getDescriptionEquipe(){
        return textAreaDescription.getText();
    }

    public static void main(String[] args) {
        DialogAjouterEquipe dialog = new DialogAjouterEquipe();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
