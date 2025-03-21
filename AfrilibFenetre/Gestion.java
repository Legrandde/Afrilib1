package AfrilibFenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import AfrilibClass.Bibliotheque;

// import peniel.Bibliotheque;
// import peniel.Livre;

public class Gestion extends JFrame {
    // Bibliotheque bibliotheque = new Bibliotheque();
    public Gestion(){
        ImageIcon icon = new ImageIcon("images/equipe.png");
        setIconImage(icon.getImage());
        // Creation de la bar de menu:
        JMenuBar barMenu = new JMenuBar();
        JMenu menu = new JMenu("Gestion");
        JMenuItem nMenu= new JMenuItem("Gesion des empreints et retours de document");
        menu.add(nMenu);
        barMenu.add(menu);
        setJMenuBar(barMenu);

        nMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                        new TableauDeBord();
                }
        });
        


        // ======================== Declaration du contenu pricipale de la fenetre ==================================
        JPanel ContenuPrincipale = new JPanel();
        ContenuPrincipale.setLayout(new BorderLayout());
        JLabel Bienvenu = new JLabel("Bienvenu dans l'ajout de document et d'adherent");
        Bienvenu.setForeground(Color.decode("#333"));
        Bienvenu.setFont(new Font("poppins", Font.BOLD, 24));
        Bienvenu.setBackground(Color.ORANGE);
        ContenuPrincipale.add(Bienvenu, BorderLayout.NORTH);

        JTabbedPane onglet = new JTabbedPane();
        JPanel gestionDocument = new JPanel();
        gestionDocument.setLayout(new BorderLayout());
        // Declaration des widgets a mettre sur le contenu principale
        JLabel labelPrincipal = new JLabel("Ajouter un document");
        labelPrincipal.setFont(new Font("Poppins", Font.BOLD, 38));
        JPanel ajouterDocument = new JPanel();
        ajouterDocument.setLayout(new GridBagLayout());
        JTextField champsTitre = new JTextField("Titre du libre");
        champsTitre.setPreferredSize(new Dimension(200, 30));
        JTextField champsNOmAuteur = new JTextField("Nom de l'auteur");
        champsNOmAuteur.setPreferredSize(new Dimension(200, 30));
        JTextField champsNombrePage = new JTextField("Nombre de page");
        champsNombrePage.setPreferredSize(new Dimension(200, 30));
        JTextField champsNombreExemplaire = new JTextField("Nombre d'exemplaire");
        champsNombreExemplaire.setPreferredSize(new Dimension(200, 30));
        // Boutton pour ajouter le livre
        JButton ajouter = new JButton(" + Ajouter");
        ajouter.setBackground(Color.decode("#333"));
        ajouter.setForeground(Color.WHITE);

        // =========================Ajout des widgets au contenu principal et disposition des widgets=========================================
        GridBagConstraints contraine = new GridBagConstraints();
        contraine.gridx =0;
        contraine.insets = new Insets(10, 20, 0, 0);
        contraine.gridy =0;
        gestionDocument.add(labelPrincipal);
        ajouterDocument.add(champsTitre, contraine);
        contraine.gridx =1;
        ajouterDocument.add(champsNOmAuteur, contraine);
        contraine.gridx =0;
        contraine.gridy =1;
        ajouterDocument.add(champsNombrePage, contraine);
        contraine.gridx =1;
        ajouterDocument.add(champsNombreExemplaire, contraine);
        contraine.gridx =1;
        contraine.gridy=3;
        ajouterDocument.add(ajouter, contraine);
        ImageIcon  icsnon = new ImageIcon("images/ajout.png");
        Image imgc = icsnon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        JLabel images = new JLabel(new ImageIcon(imgc));
        contraine.gridy=2;
        ajouterDocument.add(images, contraine);
//  Premiere logique de l'application 
        ajouter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    String titre = champsTitre.getText();
                    String auteur = champsNOmAuteur.getText();
                    int nomDePage = Integer.parseInt(champsNombrePage.getText());
                    int nomExemplaire = Integer.parseInt(champsNombreExemplaire.getText());
                    if(Bibliotheque.valid_nom(auteur))
                        Bibliotheque.ajouter_document(titre, auteur,nomDePage, nomExemplaire , "Livre");
                    else
                        JOptionPane.showMessageDialog(null, "Veuillez entrer un nom valide Veuillez reessayer", "Nom invalide", 0);
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null, "Veuillez enter des chiffres");
                }
            }
        });
        

        // Ajout des widgets au contenu principale
        gestionDocument.add(ajouterDocument);

        // Configuration de la fenetre principale 
        SideBar sideBar = new SideBar();
        GestionAdherent adherentst = new GestionAdherent();
        JLabel document = new JLabel("Documents");
        document.setForeground(Color.WHITE);
        document.setFont(new Font("poppins", Font.BOLD, 18));
        JLabel adherent = new JLabel("Adherent");
        adherent.setForeground(Color.WHITE);
        adherent.setFont(new Font("poppins", Font.BOLD, 18));
        adherent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                ContenuPrincipale.remove(onglet);
                ContenuPrincipale.add(adherentst,BorderLayout.CENTER);
                ContenuPrincipale.revalidate();
                ContenuPrincipale.repaint();
            }
        });

        document.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                ContenuPrincipale.remove(adherentst);
                ContenuPrincipale.add(onglet,BorderLayout.CENTER);
                ContenuPrincipale.revalidate();
                ContenuPrincipale.repaint();
            }
        });
        sideBar.add(document);
        sideBar.add(adherent);
        ContenuPrincipale.add(sideBar, BorderLayout.WEST);
        ContenuPrincipale.add(onglet, BorderLayout.CENTER);

        onglet.addTab("Ajouter un livre", gestionDocument);
        onglet.addTab("Ajouter Revue", new GestionRevue());
        onglet.addTab("Ajouter Un article", new GestionArticles());
        setTitle("Afrilib - gestion documents");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(ContenuPrincipale); // Ajout du contenu principale a la fenetre
        setVisible(true);
    }
}
