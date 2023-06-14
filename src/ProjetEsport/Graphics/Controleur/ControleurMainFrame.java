package ProjetEsport.Graphics.Controleur;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import ProjetEsport.Graphics.GUI.*;
import ProjetEsport.Graphics.GUI.Dialog.*;
import ProjetEsport.Graphics.GUI.model.*;
import ProjetEsport.HCS.Classes.Evenements.Matches;
import ProjetEsport.HCS.Classes.Evenements.Tournoi;
import ProjetEsport.HCS.Classes.GestionFichier.GestionnaireFichier;
import ProjetEsport.HCS.Classes.Participants.Members;
import ProjetEsport.HCS.Classes.Participants.Teams;

public class ControleurMainFrame extends WindowAdapter implements ActionListener, MouseListener, ChangeListener
{
    private mainFrame Fenetre;
    private Tournoi tournoi;

    public ControleurMainFrame(mainFrame FENETRE,Tournoi TOURNOI){
        Fenetre = FENETRE;
        tournoi = TOURNOI;
    }

    public void resetAffichage(){
        Fenetre.getListEquipe().removeAll();
        Fenetre.setListEquipe(new ArrayList<>(tournoi.getTableEquipe().values()));

        Fenetre.getListMatch().removeAll();
        Fenetre.setListMatch(new ArrayList<>(tournoi.getTableMatch().values()));

        Fenetre.getListParticipant().removeAll();
        Fenetre.setListParticipant(new ArrayList<>(tournoi.getTableEquipe().values()));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == Fenetre.getPanneau()){
            int selectedIndex = Fenetre.getPanneau().getSelectedIndex();
            if(selectedIndex == 0){
                Fenetre.getListMatch().removeAll();
                Fenetre.setListMatch(new ArrayList<>(tournoi.getTableMatch().values()));
            }
            else if(selectedIndex == 1){
                Fenetre.getListEquipe().removeAll();
                Fenetre.setListEquipe(new ArrayList<>(tournoi.getTableEquipe().values()));
            }
            else if(selectedIndex == 2){
                Fenetre.getListParticipant().removeAll();
                Fenetre.setListParticipant(new ArrayList<>(tournoi.getTableEquipe().values()));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == Fenetre.getMenuItemNouveau()){
            String nomFichier = "";
            while(nomFichier.isBlank()) {
                nomFichier = JOptionPane.showInputDialog("Entrez le nom du fichier :");
            }
            System.out.println("Nouveau fichier :" + nomFichier);
            tournoi.setFileName("src/Data/" + nomFichier + ".data");

            GestionnaireFichier.CreationFichier(tournoi.getFileName());

            tournoi.resetTournoi();
            resetAffichage();
            Fenetre.EnablePanels(true);

        }else if(e.getSource() == Fenetre.getMenuItemOuvrir()){
            JFileChooser selecteurFichier = new JFileChooser("src/Data/");
            int valeur = selecteurFichier.showOpenDialog(Fenetre);

            if(valeur == JFileChooser.APPROVE_OPTION){

                String fileName = selecteurFichier.getSelectedFile().getPath();
                System.out.println("Ouverture de : " + fileName);

                tournoi = GestionnaireFichier.ouvrirFichier(fileName);
                Fenetre.EnablePanels(true);
                resetAffichage();
            }

        }else if(e.getSource() == Fenetre.getMenuItemEnregistrer()){
            GestionnaireFichier.sauvegarderFichier(tournoi);

        }else if(e.getSource() == Fenetre.getMenuItemQuitter()) {
            GestionnaireFichier.sauvegarderFichier(tournoi);
            Fenetre.setVisible(false);
            Fenetre.dispose();

        }else if(e.getSource() == Fenetre.getClaire()){
            Fenetre.sauvegardeProperties(Color.WHITE);
            Fenetre.setBackgroundUI(Color.WHITE);
        }else if(e.getSource() == Fenetre.getSombre()){
            Fenetre.sauvegardeProperties(Color.GRAY);
            Fenetre.setBackgroundUI(Color.GRAY);
        }else if(e.getSource() == Fenetre.getButtonAjouterEquipe() ||e.getSource() == Fenetre.getMenuItemAjouterEquipe()){

            DialogAjouterEquipe dae = new DialogAjouterEquipe();
            dae.setInformation(tournoi.getTableEquipe().keySet().toArray(new String[0]));

            dae.setVisible(true);
            //tournoi.AjouterUneEquipe(dae.getEquipe());
            if(dae.isOk())
                tournoi.AjouterUneEquipe(new Teams(dae.getNomEquipe(),dae.getDescriptionEquipe()));
            dae.dispose();

            Fenetre.getListEquipe().removeAll();
            Fenetre.setListEquipe(new ArrayList<>(tournoi.getTableEquipe().values()));

        }else if(e.getSource() == Fenetre.getButtonSupprimerEquipe() || e.getSource() == Fenetre.getMenuItemSupprimerEquipe()){

            int selectedRow = Fenetre.getListEquipe().getSelectedRow();
            if(selectedRow != -1){

                modelTableEquipe mtm = (modelTableEquipe)Fenetre.getListEquipe().getModel();
                Teams equipeSelected = mtm.getRowTeams(selectedRow);

                tournoi.RetirerUneEquipe(equipeSelected.getTeamName());

                Fenetre.getListEquipe().removeAll();
                Fenetre.setListEquipe(new ArrayList<>(tournoi.getTableEquipe().values()));
            }
            else
                JOptionPane.showMessageDialog(Fenetre,"Aucune equipe existante ou selectionnee!\nSuppression impossible!","Equipe",JOptionPane.INFORMATION_MESSAGE);

        }else if(e.getSource() == Fenetre.getButtonAjouterMatch() || e.getSource() == Fenetre.getMenuItemAjouterMatch()){

            if(!tournoi.getTableEquipe().isEmpty()){
                DialogMatch dm = new DialogMatch(Fenetre,"Fenetre d'ajout d'un match",true,tournoi.getTableEquipe());
                dm.setVisible(true);
                if(dm.getEtatDialog()){
                    Teams A = tournoi.getTableEquipe().get((String)dm.getComboBoxASelectedItem());
                    Teams B = tournoi.getTableEquipe().get((String)dm.getComboBoxBSelectedItem());
                    int roundNumber = dm.getNombreDeManche();
                    tournoi.AjouterMatch(A,B,roundNumber);

                    Fenetre.getListMatch().removeAll();
                    Fenetre.setListMatch(new ArrayList<>(tournoi.getTableMatch().values()));
                }
                dm.dispose();
            }
            else {
                JOptionPane.showMessageDialog(Fenetre,"Aucune equipe existante!\nPas d'ajout possible!","Match",JOptionPane.INFORMATION_MESSAGE);
            }


        }else if(e.getSource() == Fenetre.getButtonSupprimerMatch() || e.getSource() == Fenetre.getMenuItemSupprimerMatch()){

            int selectedRow = Fenetre.getListMatch().getSelectedRow();
            if(selectedRow != -1){

                modelTableMatch mtm = (modelTableMatch)Fenetre.getListMatch().getModel();
                Matches matchSelected = mtm.getRowMatch(selectedRow);

                tournoi.RetirerMatch(matchSelected);

                Fenetre.getListMatch().removeAll();
                Fenetre.setListMatch(new ArrayList<>(tournoi.getTableMatch().values()));
            }
            else
                JOptionPane.showMessageDialog(Fenetre,"Aucun match existant ou selectionne!\nPas d'affichage possible!","Match",JOptionPane.INFORMATION_MESSAGE);

        }else if(e.getSource() == Fenetre.getButtonInfoSupplementaire()){

            int selectedRow = Fenetre.getListParticipant().getSelectedRow();
            if(selectedRow != -1) {
                DialogAffichageParticipant dap = new DialogAffichageParticipant();

                modelTableParticipant mtp = (modelTableParticipant) Fenetre.getListParticipant().getModel();
                Members membreSelected = mtp.getRowMembers(selectedRow);

                dap.setInformation(membreSelected);
                dap.setVisible(true);
                dap.dispose();
            }
            else
                JOptionPane.showMessageDialog(Fenetre,"Aucun participant existant ou selectionne!\nPas d'affichage possible!","Participant",JOptionPane.INFORMATION_MESSAGE);

        }
        else if(e.getSource() == Fenetre.getButtonAfficherMatch()){

            int selectedRow = Fenetre.getListMatch().getSelectedRow();
            if(selectedRow != -1){
                DialogMatchAffichage dma = new DialogMatchAffichage();

                modelTableMatch mtm = (modelTableMatch)Fenetre.getListMatch().getModel();
                Matches matchSelected = mtm.getRowMatch(selectedRow);

                dma.setInformation(matchSelected);
                dma.setVisible(true);
                dma.dispose();
            }
            else
                JOptionPane.showMessageDialog(Fenetre,"Aucun match existant ou selectionne!\nPas d'affichage possible!","Match",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getSource() == Fenetre.getButtonAfficherEquipe()){
            int selectedRow = Fenetre.getListEquipe().getSelectedRow();
            if(selectedRow != -1){
                DialogEquipeAffichage dea = new DialogEquipeAffichage();

                modelTableEquipe mte = (modelTableEquipe)Fenetre.getListEquipe().getModel();
                Teams EquipeSelected = mte.getRowTeams(selectedRow);

                dea.setInformation(EquipeSelected);
                dea.setVisible(true);
                dea.dispose();
            }else
                JOptionPane.showMessageDialog(Fenetre,"Aucune equipe existante ou selectionnee!\nPas d'affichage possible!","Equipe",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getSource() == Fenetre.getButtonModifierMatch()){
            int selectedRow = Fenetre.getListMatch().getSelectedRow();
            if(selectedRow != -1){
                modelTableMatch mtm = (modelTableMatch)Fenetre.getListMatch().getModel();
                Matches matchSelected = mtm.getRowMatch(selectedRow);
                if(matchSelected.isMatchActive()){

                    DialogModifierMatch dmm = new DialogModifierMatch();
                    dmm.setInformation(matchSelected);
                    dmm.setVisible(true);
                    dmm.dispose();

                    Fenetre.getListMatch().removeAll();
                    Fenetre.setListMatch(new ArrayList<>(tournoi.getTableMatch().values()));
                }
                else JOptionPane.showMessageDialog(Fenetre,"Le match est termine!\nEdition Impossible!","Match",JOptionPane.INFORMATION_MESSAGE);
            }else
                JOptionPane.showMessageDialog(Fenetre,"Aucune equipe existante ou selectionnee!\nPas d'affichage possible!","Match",JOptionPane.INFORMATION_MESSAGE);

        }
        else if(e.getSource() == Fenetre.getButtonModifierEquipe()){
            int selectedRow = Fenetre.getListEquipe().getSelectedRow();
            if(selectedRow != -1) {
                DialogModifierEquipe dme = new DialogModifierEquipe();

                modelTableEquipe mte = (modelTableEquipe) Fenetre.getListEquipe().getModel();
                Teams EquipeSelected = mte.getRowTeams(selectedRow);

                dme.setInformation(EquipeSelected);
                dme.setVisible(true);
                dme.dispose();

                //System.out.println("tournoi equipes : " + tournoi.getTableEquipe().values());

                Fenetre.getListEquipe().removeAll();
                Fenetre.setListEquipe(new ArrayList<>(tournoi.getTableEquipe().values()));

                // -----------------------------------------------------------------------------//
                Fenetre.getListParticipant().removeAll();
                Fenetre.setListParticipant(new ArrayList<>(tournoi.getTableEquipe().values()));
            }
            else
                JOptionPane.showMessageDialog(Fenetre,"Aucune equipe existante ou selectionnee!\nModification impossible","Equipe",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //GestionnaireFichier.sauvegarderFichier(Fenetre.getCurrentFileName(),tournoi);
        GestionnaireFichier.sauvegarderFichier(tournoi);
        System.exit(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getClickCount() == 2){
            if(Fenetre.getPanneau().getSelectedComponent() == Fenetre.getPanelMatch()){
                DialogMatchAffichage dma = new DialogMatchAffichage();
                int selectedRow = Fenetre.getListMatch().getSelectedRow();
                modelTableMatch mtm = (modelTableMatch)Fenetre.getListMatch().getModel();
                Matches matchSelected = mtm.getRowMatch(selectedRow);

                dma.setInformation(matchSelected);
                dma.setVisible(true);
                dma.dispose();
            }
            else if(Fenetre.getPanneau().getSelectedComponent() == Fenetre.getPanelEquipe()){
                int selectedRow = Fenetre.getListEquipe().getSelectedRow();
                if(selectedRow != -1) {
                    DialogEquipeAffichage dea = new DialogEquipeAffichage();

                    modelTableEquipe mte = (modelTableEquipe) Fenetre.getListEquipe().getModel();
                    Teams EquipeSelected = mte.getRowTeams(selectedRow);

                    dea.setInformation(EquipeSelected);
                    dea.setVisible(true);
                    dea.dispose();
                }
            }else if(Fenetre.getPanneau().getSelectedComponent() == Fenetre.getPanelParticipant()){
                int selectedRow = Fenetre.getListParticipant().getSelectedRow();
                if(selectedRow != -1) {
                    DialogAffichageParticipant dap = new DialogAffichageParticipant();

                    modelTableParticipant mtp = (modelTableParticipant) Fenetre.getListParticipant().getModel();
                    Members membreSelected = mtp.getRowMembers(selectedRow);

                    dap.setInformation(membreSelected);
                    dap.setVisible(true);
                    dap.dispose();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public static void main(String[] argv){
        Tournoi tournoi = Tournoi.getInstance();
        //tournoi.AjouterUneEquipe("Requiem","We are forerunners !");
        //tournoi.AjouterUneEquipe("Meridian", "We are Humans !");
        //tournoi.AjouterUneEquipe("Great sharity", "We are Covenants !");

        System.out.println(tournoi.getTableEquipe().keySet());

        mainFrame p1 = new mainFrame();
        ControleurMainFrame CM = new ControleurMainFrame(p1,tournoi);

        p1.setControler(CM);
        p1.setVisible(true);
    }
}
