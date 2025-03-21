package AfrilibFenetre;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// C'est la fentre sur la quelle est placer le tableau de bord

import javax.swing.*;

import AfrilibPanel.GuiAdherent;
import AfrilibPanel.GuiArticles;
import AfrilibPanel.GuiHistorique;
import AfrilibPanel.GuiLivre;
import AfrilibPanel.GuiRevu;
import AfrilibPanel.GuiTaxe;

public class TableauDeBord extends JFrame {

    public TableauDeBord(){

        ImageIcon icon = new ImageIcon("images/equipe.png");
        setIconImage(icon.getImage());
        // Creation de la bar de menu:
        JMenuBar barMenu = new JMenuBar();
        JMenu menu = new JMenu("Gestion");
        JMenuItem nMenu= new JMenuItem("Gesion des documents et des Adherants");
        menu.add(nMenu);
        barMenu.add(menu);
        setJMenuBar(barMenu);

        nMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                        new Gestion();
                }
        });
        // Intentiation d'un onglets
        JPanel contenuGlobale = new JPanel();
        contenuGlobale.setLayout(new BorderLayout());
        
        JTabbedPane onglet = new JTabbedPane();
        JPanel contenuePrincipal = new JPanel();
        contenuePrincipal.setLayout(new BorderLayout());
        contenuGlobale.add(contenuePrincipal, BorderLayout.CENTER);
        SideBarAdherant sideBar = new SideBarAdherant();
        contenuGlobale.add(sideBar, BorderLayout.WEST);
        JPanel test = new JPanel();
        test.setLayout(new BoxLayout(test, BoxLayout.Y_AXIS));
        test.setBackground(Color.decode("#333"));
        test.setPreferredSize(new Dimension(200, 400));
        sideBar.setLayout(new BorderLayout());
        sideBar.add(test, BorderLayout.CENTER);
        ImageIcon iconDocument = new ImageIcon("images/pile-de-livres.png");
        Image imageDocument = iconDocument.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel document = new JLabel("Documents",new ImageIcon(imageDocument), JLabel.LEFT);


        document.setBackground(Color.decode("#333"));
        document.setForeground(Color.WHITE);
        document.setPreferredSize(new Dimension(300, 200));
        document.setFont(new Font("poppins", Font.BOLD, 18));
        ImageIcon iconHistorique =new ImageIcon("images/lhistoire.png");
        Image imageHistorique = iconHistorique.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel historique = new JLabel("Historique", new ImageIcon(imageHistorique), JLabel.LEFT);

        historique.setBackground(Color.decode("#333"));
        historique.setForeground(Color.WHITE);
        historique.setFont(new Font("poppins", Font.BOLD, 18));
        ImageIcon iconTaxe = new ImageIcon("images/hors-taxe.png");
        Image imageTaxe = iconTaxe.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel taxes = new JLabel("Taxes", new ImageIcon(imageTaxe), JLabel.LEFT);


        // =============================================================================
        // ImageIcon iconAjouter = new ImageIcon("C:\\Users\\HP\\Documents\\projet2024-2025\\Afrilib\\images\\adherent.png"); // Remplacez par le chemin de votre image
        // Image imgAjouter = iconAjouter.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // =============================================================================
        // JButton btnAjouterLivre = new JButton("Ajouter un Document", iconAjouter);

        ImageIcon iconAdherent = new ImageIcon("images/equipe.png");
        Image imgAdherent = iconAdherent.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel adherents = new JLabel("Adherants", new ImageIcon(imgAdherent), JLabel.LEFT);

        // JLabel taxe = new JLabel("Taxes");
        
        
        JPanel espace = new JPanel();
        espace.setPreferredSize(new Dimension(100, 100));
        ImageIcon imgAdmin =new ImageIcon("images/admin3.jpg");
        Image imgAdmins = imgAdmin.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel admin =  new JLabel(new ImageIcon(imgAdmins));

        espace.add(admin);
        espace.setBackground(Color.ORANGE);
        sideBar.add(espace, BorderLayout.NORTH);




        test.add(document);
        GuiHistorique historique2 = new GuiHistorique();
        document.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                        contenuGlobale.removeAll();
                        contenuGlobale.add(sideBar, BorderLayout.WEST);
                        contenuGlobale.add(contenuePrincipal, BorderLayout.CENTER);
                        contenuGlobale.revalidate();
                        contenuGlobale.repaint();
                }
        });



        test.add(historique);
        historique.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                        contenuGlobale.removeAll();
                        contenuGlobale.add(sideBar, BorderLayout.WEST);
                        contenuGlobale.add(historique2, BorderLayout.CENTER);
                        contenuGlobale.revalidate();
                        contenuGlobale.repaint();
                }
        });
        test.add(taxes);
        GuiTaxe taxe = new GuiTaxe();
        taxes.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                        contenuGlobale.removeAll();
                        contenuGlobale.add(sideBar, BorderLayout.WEST);
                        contenuGlobale.add(taxe, BorderLayout.CENTER);
                        contenuGlobale.revalidate();
                        contenuGlobale.repaint();
                }
        });
        
        test.add(adherents);
        GuiAdherent adherent = new GuiAdherent();
        adherents.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                        contenuGlobale.removeAll();
                        contenuGlobale.add(sideBar, BorderLayout.WEST);
                        contenuGlobale.add(adherent, BorderLayout.CENTER);
                        contenuGlobale.revalidate();
                        contenuGlobale.repaint();
                }
        });
        adherents.setBackground(Color.decode("#333"));
        adherents.setForeground(Color.WHITE);
        adherents.setFont(new Font("poppins", Font.BOLD, 18));
        taxes.setBackground(Color.decode("#333"));
        taxes.setForeground(Color.WHITE);
        taxes.setFont(new Font("poppins", Font.BOLD, 18));
        // Ajout des widjets pour le  la recharcher de livre =======================================================================================



        JPanel status = new JPanel();
        //===================  Intetiations des widgets de Status ===========================================================================================

        JLabel etiquetestates = new JLabel("Chercher un document");
        etiquetestates.setFont(new Font("poppins", Font.BOLD, 20));
        etiquetestates.setForeground(Color.white);
        String[] optionDeTrie = {"Livre", "Revue", "Nouvelle"};
        JComboBox<String> trieRecherche = new JComboBox<>(optionDeTrie);
        trieRecherche.setBackground(Color.decode("#7DC2A5"));
        JTextField champsRecherche = new JTextField();
        
        JButton chercher = new JButton("Chercher");
        chercher.setBackground(Color.ORANGE);
        champsRecherche.setPreferredSize(new Dimension(200, 30));

        status.setBackground(Color.decode("#333"));
        status.setLayout(new GridBagLayout());
        GridBagConstraints contraintes = new GridBagConstraints();
        contraintes.gridy =0;
        contraintes.anchor = GridBagConstraints.CENTER;

        // ==============Ajout des elements de status ===============================================

        status.add(etiquetestates, contraintes);
        contraintes.gridy =1;
       
        contraintes.gridy =1;
        contraintes.gridx =1;
        status.add(champsRecherche, contraintes);
        contraintes.gridx=2;
        status.add(chercher, contraintes);

//      ================================================================================================

       
        

        
        // ================================================================================



//          widjets pour livre disponible // -- Donc affichage des livres dispinibles  ===============================================================
        
        JPanel paneauTableau = new JPanel();
        paneauTableau.setLayout(new BorderLayout());
        
        
        
        // Ajout de livre dispo sur l'onglet
        onglet.addTab("Livre", new GuiLivre());
        onglet.addTab("Revue", new GuiRevu());
        onglet.addTab("Articles", new GuiArticles());



// ====================================================================================================================================
       
        // contenuePrincipal.add(new SideBar(), BorderLayout.WEST);
        
        contenuePrincipal.add(status, BorderLayout.NORTH);
        contenuePrincipal.add(onglet, BorderLayout.CENTER);
        

        


















        contenuePrincipal.setBackground(Color.decode("#333"));
        setContentPane(contenuGlobale);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Afrilib - Tableau de bord");
        setVisible(true);

        
    }
}
