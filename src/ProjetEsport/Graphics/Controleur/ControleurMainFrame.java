package ProjetEsport.Graphics.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ProjetEsport.Graphics.GUI.*;
import ProjetEsport.HCS.Classes.Evenements.Matches;
import ProjetEsport.HCS.Classes.Evenements.Tournoi;
import ProjetEsport.HCS.Classes.Participants.Teams;

public class ControleurMainFrame extends WindowAdapter implements ActionListener
{
    private mainFrame Fenetre;
    private Tournoi tournoi;

    public ControleurMainFrame(mainFrame FENETRE,Tournoi TOURNOI){
        Fenetre = FENETRE;
        tournoi = TOURNOI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == Fenetre.getMenuItemOuvrir()){
            JDialog j = new JDialog(Fenetre,"Ouvrir",true);
            j.setLocation(200,200);
            j.setSize(200,200);
            j.setVisible(true);
            j.dispose();
        }else if(e.getSource() == Fenetre.getMenuItemEnregistrer()){
            JDialog j = new JDialog(Fenetre,"Enregistrer",true);
            j.setLocation(200,200);
            j.setSize(200,200);
            j.setVisible(true);
            j.dispose();
        }else if(e.getSource() == Fenetre.getMenuItemQuitter()){
            Fenetre.setVisible(false);
            Fenetre.dispose();
        }else if(e.getSource() == Fenetre.getButtonAjouterEquipe() ||e.getSource() == Fenetre.getMenuItemAjouterEquipe()){
            JDialog j = new JDialog(Fenetre,"ClickButton AjEquipe",true);
            j.setLocation(200,200);
            j.setSize(200,200);
            j.setVisible(true);
            j.dispose();
        }else if(e.getSource() == Fenetre.getButtonSupprimerEquipe() || e.getSource() == Fenetre.getMenuItemSupprimerEquipe()){
            JDialog j = new JDialog(Fenetre,"ClickButton SUpEquipe",true);
            j.setLocation(200,200);
            j.setSize(200,200);
            j.setVisible(true);
            j.dispose();
        }else if(e.getSource() == Fenetre.getButtonAjouterMatch() || e.getSource() == Fenetre.getMenuItemAjouterMatch()){

            if(!tournoi.getTableEquipe().isEmpty()){
                DialogMatch dm = new DialogMatch(Fenetre,"Fenetre d'ajout d'un match",true,tournoi.getTableEquipe());
                dm.setVisible(true);
                if(dm.getEtatDialog()){
                    Teams A = tournoi.getTableEquipe().get((String)dm.getComboBoxASelectedItem());
                    Teams B = tournoi.getTableEquipe().get((String)dm.getComboBoxBSelectedItem());
                    tournoi.AjouterMatch(A,B);

                    //Fenetre.getListMatch().removeAll();
                    //Fenetre.setListMatch(tournoi.getTableMatch().keySet().toArray(new String[0]));
                }
                dm.dispose();
            }
            else {
                JOptionPane.showMessageDialog(Fenetre,"Aucune equipe existante!\nPas d'ajout possible!","Match",JOptionPane.INFORMATION_MESSAGE);
            }


        }else if(e.getSource() == Fenetre.getButtonSupprimerMatch() || e.getSource() == Fenetre.getMenuItemSupprimerMatch()){
            JDialog j = new JDialog(Fenetre,"ClickButton SupMatch",true);
            j.setLocation(200,200);
            j.setSize(200,200);
            j.setVisible(true);
            j.dispose();
        }else if(e.getSource() == Fenetre.getButtonInfoSupplementaire()){
            JDialog j = new JDialog(Fenetre,"ClickButton ButtonInfoSup",true);
            j.setLocation(200,200);
            j.setSize(200,200);
            j.setVisible(true);
            j.dispose();
        }
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public static void main(String[] argv){
        mainFrame p1 = new mainFrame();
        Tournoi tournoi = Tournoi.getInstance();
        tournoi.AjouterUneEquipe("Requiem","We are forerunners !");
        tournoi.AjouterUneEquipe("Meridian", "We are Humans !");
        tournoi.AjouterUneEquipe("Great sharity", "We are Covenants !");
        ControleurMainFrame CM = new ControleurMainFrame(p1,tournoi);

        p1.setControler(CM);
        p1.setVisible(true);
    }
}
