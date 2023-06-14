package ProjetEsport.Graphics.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Properties;

import ProjetEsport.Graphics.Controleur.*;
import ProjetEsport.Graphics.GUI.model.*;
import ProjetEsport.HCS.Classes.Evenements.Matches;
import ProjetEsport.HCS.Classes.GestionFichier.GestionnaireFichier;
import ProjetEsport.HCS.Classes.Participants.Teams;

public class mainFrame extends JFrame
{
    private JTabbedPane panneau;

    private JPanel panelMatch;
    private JPanel panelEquipe;
    private JPanel panelParticipant;
    
    private JButton buttonAjouterMatch;
    private JButton buttonAjouterEquipe;

    private JButton buttonSupprimerMatch;
    private JButton buttonSupprimerEquipe;

    private JButton buttonModifierMatch;
    private JButton buttonModifierEquipe;

    private JButton buttonAfficherMatch;
    private JButton buttonAfficherEquipe;

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
            private JMenuItem menuItemNouveau;
        private JMenu MenuCouleur;
            private JMenuItem Claire;
            private JMenuItem Sombre;


    private String[] options = {"Ouvrir un fichier","Creer un nouveau fichier"};
    private String filePath = "src/Data/color.properties";

    public mainFrame(){
        super("Esport manager");
        initComponents();
        chargementProperties();
    }

    public void setBackgroundUI(Color color){
        Color font;
        if(color == Color.WHITE){
            font = Color.BLACK;
        }
        else font = Color.WHITE;

        setBackground(color);

        panelMatch.setBackground(color);
        panelEquipe.setBackground(color);
        panelParticipant.setBackground(color);

        listMatch.setBackground(color);
        listEquipe.setBackground(color);
        listParticipant.setBackground(color);

        menuBar.setBackground(color);

        listMatch.setForeground(font);
        listEquipe.setForeground(font);
        listParticipant.setForeground(font);
    }

    public void chargementProperties(){
        Properties prop = new Properties();
        File file = new File(filePath);
        if(file.exists()){
            System.out.println("\033[92mChargement du fichier properties!\033[0m");
            try(InputStream in = new FileInputStream(file))
            {
                prop.load(in);
                    String value = prop.getProperty("Couleur");
                if(value.equalsIgnoreCase("claire")){
                    //this.setBackground(Color.WHITE);
                    setBackgroundUI(Color.WHITE);
                }
                else {
                    //this.setBackground(Color.GRAY);
                    setBackgroundUI(Color.GRAY);
                }
                System.out.println("Valeur properties Couleur = "+ value);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            setBackgroundUI(Color.WHITE);
            try{
                System.out.println(Color.RED + "Creation du fichier properties!" + Color.BLACK);
                file.createNewFile();
                prop.setProperty("Couleur","claire");

                OutputStream out = new FileOutputStream(file);
                    prop.store(out,null);
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sauvegardeProperties(Color color){
        String value;
        if(color == Color.WHITE){
            value = "claire";
        }
        else{
            value = "sombre";
        }

        Properties prop = new Properties();
        try {
            // Chargement du fichier de propriétés existant
            Properties properties = new Properties();
            InputStream input = new FileInputStream(filePath);
            properties.load(input);
            input.close();

            // Modification de la valeur "sombre" en "claire"
            properties.setProperty("Couleur", value);

            // Enregistrement des modifications dans le fichier
            OutputStream output = new FileOutputStream(filePath);
            properties.store(output, null);
            output.close();

            System.out.println("La valeur a été modifiée avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        menuItemNouveau.addActionListener(CM);

        Claire.addActionListener(CM);
        Sombre.addActionListener(CM);

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

        buttonInfoSupplementaire = new JButton("Afficher plus d'information");
        buttonInfoSupplementaire.setBorder(BorderFactory.createCompoundBorder());

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
        JPanel panelInformationParticpant = new JPanel(new GridLayout(1,1));
        panelInformationParticpant.add(buttonInfoSupplementaire);
        // State of visibility
        panelInformationParticpant.setVisible(true);
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

        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.gridy = 0;
        panelParticipant.add(new JScrollPane(listParticipant),gridBagConstraints);

        gridBagConstraints.weighty = 0.2;
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
                menuItemNouveau = new JMenuItem("Nouveau");
                menuItemOuvrir = new JMenuItem("Ouvrir");
                menuItemEnregistrer = new JMenuItem("Enregistrer");
                menuItemQuitter = new JMenuItem("Quitter");
            MenuCouleur = new JMenu("Couleur");
                Claire = new JMenuItem("Claire");
                Sombre = new JMenuItem("Sombre");

        MenuEquipe.add(menuItemAjouterEquipe);
        MenuEquipe.add(menuItemSupprimerEquipe);

        MenuMatch.add(menuItemAjouterMatch);
        MenuMatch.add(menuItemSupprimerMatch);

        MenuSystem.add(menuItemNouveau);
        MenuSystem.add(menuItemOuvrir);
        MenuSystem.add(menuItemEnregistrer);
        MenuSystem.add(menuItemQuitter);

        MenuCouleur.add(Claire);
        MenuCouleur.add(Sombre);

        menuBar.add(MenuSystem);
        menuBar.add(MenuMatch);
        menuBar.add(MenuEquipe);
        menuBar.add(MenuCouleur);
        
        setJMenuBar(menuBar);

        setContentPane(panneau);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1500,1000));

        EnablePanels(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        //pack();
    }

    public void EnablePanels(boolean actif){
        MenuEquipe.setEnabled(actif);
        MenuMatch.setEnabled(actif);
        menuItemEnregistrer.setEnabled(actif);

        panelEquipe.setEnabled(actif);
        panelParticipant.setEnabled(actif);
        panelMatch.setEnabled(actif);

        buttonAfficherMatch.setEnabled(actif);
        buttonAjouterMatch.setEnabled(actif);
        buttonModifierMatch.setEnabled(actif);
        buttonSupprimerMatch.setEnabled(actif);

        buttonAfficherEquipe.setEnabled(actif);
        buttonModifierEquipe.setEnabled(actif);
        buttonAjouterEquipe.setEnabled(actif);
        buttonSupprimerEquipe.setEnabled(actif);

        buttonInfoSupplementaire.setEnabled(actif);
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
    public JMenuItem getMenuItemNouveau() {
        return menuItemNouveau;
    }

    public JMenuItem getClaire() {
        return Claire;
    }

    public JMenuItem getSombre() {
        return Sombre;
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
