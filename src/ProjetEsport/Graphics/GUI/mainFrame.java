package ProjetEsport.Graphics.GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import ProjetEsport.Graphics.Controleur.*;
import ProjetEsport.Graphics.GUI.model.*;
import ProjetEsport.HCS.Classes.Evenements.Matches;
import ProjetEsport.HCS.Classes.Participants.Coach;
import ProjetEsport.HCS.Classes.Participants.Members;
import ProjetEsport.HCS.Classes.Participants.Players;
import ProjetEsport.HCS.Classes.Participants.Teams;

public class mainFrame extends JFrame
{
    private JTabbedPane panneau;

    private JPanel panelMatch;
    private JPanel panelEquipe;
    private JPanel panelParticipant;
    private JPanel panelInformationParticpant;
    
    private JButton buttonAjouterMatch;
    private JButton buttonAjouterEquipe;

    private JButton buttonSupprimerMatch;
    private JButton buttonSupprimerEquipe;

    private JButton buttonModifierMatch;
    private JButton buttonModifierEquipe;

    private JButton buttonAfficherMatch;
    private JButton buttonAfficherEquipe;

    private JLabel labelParticipantId;
    private JLabel labelParticipantPseudo;
    private JLabel labelParticipantNomEquipe;
    private JLabel labelParticipantNationalite;
    private JLabel labelParticipantDateEnregistrement;
    private JLabel labelParticipantTypeMembre;
    private JLabel labelParticipantRole;
    private JButton buttonInfoSupplementaire;

    /*private JList<String> listMatch;
    private JList<String> listEquipe;
    private JList<String> listParticipant;*/

    private JTable listMatch;
    private JTable listEquipe;
    private JTable listParticipant;

    private JMenuBar menuBar;    
        private JMenu MenuEquipe;
            private JMenuItem menuItemAjouterEquipe;
            private JMenuItem menuItemSupprimerEquipe;
        private JMenu MenuMatch;
            private JMenuItem menuItemAjouterMatch;
            private JMenuItem menuItemSupprimerMatch;
        private JMenu MenuSystem;
            private JMenuItem menuItemQuitter;
            private JMenuItem menuItemEnregistrer;
            private JMenuItem menuItemOuvrir;

    public mainFrame(){
        super("Esport manager");
        initComponents();
    }

    public void setControler(ControleurMainFrame CM){
        panneau.addChangeListener(CM);

        buttonAjouterMatch.addActionListener(CM);
        buttonAjouterEquipe.addActionListener(CM);

        buttonSupprimerMatch.addActionListener(CM);
        buttonSupprimerEquipe.addActionListener(CM);

        buttonModifierMatch.addActionListener(CM);
        buttonModifierEquipe.addActionListener(CM);

        buttonAfficherMatch.addActionListener(CM);
        buttonAfficherEquipe.addActionListener(CM);

        menuItemAjouterEquipe.addActionListener(CM);
        menuItemSupprimerEquipe.addActionListener(CM);
        menuItemAjouterMatch.addActionListener(CM);
        menuItemSupprimerMatch.addActionListener(CM);
        menuItemQuitter.addActionListener(CM);
        menuItemEnregistrer.addActionListener(CM);
        menuItemOuvrir.addActionListener(CM);

        listMatch.addMouseListener(CM);
        listEquipe.addMouseListener(CM);
        listParticipant.addMouseListener(CM);

        buttonInfoSupplementaire.addActionListener(CM);

        addWindowListener(CM);
    }

    private void initComponents(){
        panneau = new JTabbedPane();
        panelMatch = new JPanel(new GridBagLayout());
        panelEquipe = new JPanel(new GridBagLayout());
        panelParticipant = new JPanel(new GridBagLayout());

        buttonAjouterMatch = new JButton("Ajouter");
        buttonAjouterEquipe = new JButton("Ajouter");

        buttonSupprimerMatch = new JButton("Supprimer");
        buttonSupprimerEquipe = new JButton("Supprimer");

        buttonModifierMatch = new JButton("Modifier");
        buttonModifierEquipe = new JButton("Modifier");

        buttonAfficherMatch = new JButton("Afficher");
        buttonAfficherEquipe = new JButton("Afficher");

        labelParticipantId = new JLabel("Id : ");
        labelParticipantPseudo = new JLabel("Pseudo : ");
        labelParticipantNomEquipe = new JLabel("Nom de l'équipe : ");
        labelParticipantNationalite = new JLabel("Nationalité : ");
        labelParticipantDateEnregistrement = new JLabel("Date d'enregistrement : ");
        labelParticipantTypeMembre = new JLabel("Type de membre : ");
        labelParticipantRole = new JLabel("Role : ");
        buttonInfoSupplementaire = new JButton("Afficher plus d'information");

        labelParticipantId.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        labelParticipantPseudo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        labelParticipantNomEquipe.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        labelParticipantNationalite.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        labelParticipantDateEnregistrement.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        labelParticipantTypeMembre.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        labelParticipantRole.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        buttonInfoSupplementaire.setBorder(BorderFactory.createCompoundBorder());

        /*listMatch = new JList<>();
        listEquipe = new JList<>();
        listParticipant = new JList<>();*/

        //========== Data test ===================

        /*Players[] plist = {
                new Players("PTG_1",true, "Be"),
                new Players("PGT_2",true, "Be"),
                new Players("PGT_3",true, "Be"),
                new Players("PGT_4",true, "Be"),
                new Players("PGT_5",true, "Be"),
        };
        Players[] plist2 = {
                new Players("FUN_1",true, "Fr"),
                new Players("FUN_2",true, "Fr"),
                new Players("FUN_3",true, "Fr"),
                new Players("FUN_4",true, "Fr"),
                new Players("FUN_5",false, "Fr")
        };
        Teams t = new Teams("Quadrant");
        Teams t2 = new Teams("Sentinels");
        Coach[] c = {
                new Coach(),
                new Coach()
        };
        c[0].setPseudo("Wagner");
        c[1].setPseudo("Wilvers");   try{
            System.out.println("[" + t.getTeamName() +"]");
            for (Players x: plist) {
                t.AjouterJoueur(x);
            }
            t.setTeamCoach(c[0]);

            System.out.println("\n[" + t2.getTeamName() +"]");
            for (Players x: plist2) {
                t2.AjouterJoueur(x);
            }
            t2.setTeamCoach(c[1]);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        List<Matches> lm = new ArrayList<>();
        lm.add(new Matches(t,t2));
        lm.add(new Matches(t2,t));

        List<Teams> le = new ArrayList<>();
        le.add(t);
        le.add(t2);*/

        // =======================================

        listMatch = new JTable();
        //listMatch = new JTable(new modelTableMatch(lm));
        listMatch.setCellEditor(null);

        listEquipe = new JTable();
        //listEquipe = new JTable(new modelTableEquipe(le));
        listEquipe.setCellEditor(null);

        listParticipant = new JTable();
        //listParticipant = new JTable(new modelTableParticipant(le));
        listParticipant.setCellEditor(null);

        // ======= Panel information participant =======
        JPanel panelInformationParticpant = new JPanel(new GridLayout(3,3));
        // row 1
        panelInformationParticpant.add(labelParticipantId);
        panelInformationParticpant.add(labelParticipantPseudo);
        panelInformationParticpant.add(labelParticipantNomEquipe);
        // row 2
        panelInformationParticpant.add(labelParticipantNationalite);
        panelInformationParticpant.add(labelParticipantDateEnregistrement);
        panelInformationParticpant.add(buttonInfoSupplementaire);
        // row 3
        panelInformationParticpant.add(labelParticipantTypeMembre);
        panelInformationParticpant.add(labelParticipantRole);
        panelInformationParticpant.add(new JLabel());
        // State of visibility
        panelInformationParticpant.setVisible(false);
        // ============================================

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 0.8;
        panelMatch.add(new JScrollPane(listMatch), gridBagConstraints);

            gridBagConstraints.weighty = 0.1;
            gridBagConstraints.gridy = 1;
        panelMatch.add(createButtonPanel(buttonAjouterMatch, buttonSupprimerMatch,buttonModifierMatch,buttonAfficherMatch), gridBagConstraints);

            gridBagConstraints.weighty = 0.8;
            gridBagConstraints.gridy = 0;
        panelEquipe.add(new JScrollPane(listEquipe), gridBagConstraints);

            gridBagConstraints.weighty = 0.1;
            gridBagConstraints.gridy = 1;
        panelEquipe.add(createButtonPanel(buttonAjouterEquipe, buttonSupprimerEquipe,buttonModifierEquipe,buttonAfficherEquipe), gridBagConstraints);

        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.gridy = 0;
        panelParticipant.add(new JScrollPane(listParticipant),gridBagConstraints);

        gridBagConstraints.gridy = 1;
        panelParticipant.add(panelInformationParticpant,gridBagConstraints);

        panneau.addTab("Match",panelMatch);
        panneau.addTab("Equipe",panelEquipe);
        panneau.addTab("Participant",panelParticipant);

        menuBar = new JMenuBar();
            MenuEquipe = new JMenu("Equipe");
                menuItemAjouterEquipe = new JMenuItem("Ajouter");
                menuItemSupprimerEquipe = new JMenuItem("Supprimer");
            MenuMatch = new JMenu("Match");
                menuItemAjouterMatch = new JMenuItem("Ajouter");
                menuItemSupprimerMatch = new JMenuItem("Supprimer");
            MenuSystem = new JMenu("File");
                menuItemOuvrir = new JMenuItem("Ouvrir");
                menuItemEnregistrer = new JMenuItem("Enregistrer");
                menuItemQuitter = new JMenuItem("Quitter");

        MenuEquipe.add(menuItemAjouterEquipe);
        MenuEquipe.add(menuItemSupprimerEquipe);

        MenuMatch.add(menuItemAjouterMatch);
        MenuMatch.add(menuItemSupprimerMatch);

        MenuSystem.add(menuItemOuvrir);
        MenuSystem.add(menuItemEnregistrer);
        MenuSystem.add(menuItemQuitter);

        menuBar.add(MenuSystem);
        menuBar.add(MenuMatch);
        menuBar.add(MenuEquipe);
        
        setJMenuBar(menuBar);

        setContentPane(panneau);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1500,1000));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        //pack();
    }

    private JPanel createButtonPanel(JButton button1, JButton button2,JButton button3,JButton button4) {
        JPanel panel = new JPanel(new GridLayout(1, 4));
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        return panel;
    }

    // ================ Partie Button ======================
    public final JButton getButtonAjouterMatch() {
        return buttonAjouterMatch;
    }
    public final JButton getButtonAjouterEquipe() {
        return buttonAjouterEquipe;
    }
    public final JButton getButtonSupprimerMatch() {
        return buttonSupprimerMatch;
    }
    public final JButton getButtonSupprimerEquipe() {
        return buttonSupprimerEquipe;
    }

    public final JButton getButtonModifierMatch() {
        return buttonModifierMatch;
    }

    public final JButton getButtonModifierEquipe() {
        return buttonModifierEquipe;
    }

    public final JButton getButtonAfficherMatch() {
        return buttonAfficherMatch;
    }

    public final JButton getButtonAfficherEquipe() {
        return buttonAfficherEquipe;
    }

    public final JButton getButtonInfoSupplementaire(){
        return buttonInfoSupplementaire;
    }

    // ================= Partie Item ==========================
    public final JMenuItem getMenuItemAjouterEquipe() {
        return menuItemAjouterEquipe;
    }
    public final JMenuItem getMenuItemSupprimerEquipe() {
        return menuItemSupprimerEquipe;
    }
    public final JMenuItem getMenuItemAjouterMatch() {
        return menuItemAjouterMatch;
    }
    public final JMenuItem getMenuItemSupprimerMatch() {
        return menuItemSupprimerMatch;
    }
    public final JMenuItem getMenuItemQuitter() {
        return menuItemQuitter;
    }
    public final JMenuItem getMenuItemEnregistrer() {
        return menuItemEnregistrer;
    }
    public final JMenuItem getMenuItemOuvrir() {
        return menuItemOuvrir;
    }

    // =================== Partie Tableau ========================
    public JTable getListMatch() {
        return listMatch;
    }

    public JTable getListEquipe() {
        return listEquipe;
    }

    public JTable getListParticipant() {
        return listParticipant;
    }

    public void setListMatch(List<Matches> matches) {
        this.listMatch.setModel(new modelTableMatch(matches));
    }

    public void setListEquipe(List<Teams> equipes) {
        this.listEquipe.setModel(new modelTableEquipe(equipes));
    }

    public void setListParticipant(List<Teams> equipes) {
        this.listParticipant.setModel(new modelTableParticipant(equipes));
    }

    // ===========================================================

    // =================== Partie Panneau ========================
    public JTabbedPane getPanneau() {
        return panneau;
    }

    public JPanel getPanelMatch() {
        return panelMatch;
    }

    public JPanel getPanelEquipe() {
        return panelEquipe;
    }

    public JPanel getPanelParticipant() {
        return panelParticipant;
    }
    // ===========================================================

    public static void main(String[] argv){
        mainFrame mf = new mainFrame();
        mf.setVisible(true);
    }
}
