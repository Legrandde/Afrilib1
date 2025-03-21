package AfrilibFenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AfrilibClass.Bibliotheque;

public class GestionRevue extends JPanel{
    public GestionRevue(){

        setLayout(new BorderLayout());
        // Declaration des widgets a mettre sur le contenu principale
        JLabel labelPrincipal = new JLabel("Ajouter un document");
        labelPrincipal.setFont(new Font("Poppins", Font.BOLD, 38));
        JPanel ajouterDocument = new JPanel();
        ajouterDocument.setLayout(new GridBagLayout());
        JTextField champsTitre = new JTextField("Titre de la revue");
        champsTitre.setPreferredSize(new Dimension(200, 30));
        JTextField champsNOmAuteur = new JTextField("Nom de l'auteur");
        champsNOmAuteur.setPreferredSize(new Dimension(200, 30));
        JTextField champsNombrePage = new JTextField("periode");
        champsNombrePage.setPreferredSize(new Dimension(200, 30));
        JTextField champsNombreExemplaire = new JTextField("nombre de periode");
        champsNombreExemplaire.setPreferredSize(new Dimension(200, 30));
        // Boutton pour ajouter le livre
        JButton ajouter = new JButton(" + Ajouter");
        ajouter.setForeground(Color.white);
        ajouter.setBackground(Color.decode("#333"));

        // =========================Ajout des widgets au contenu principal et disposition des widgets=========================================
        GridBagConstraints contraine = new GridBagConstraints();
        contraine.gridx =0;
        contraine.insets = new Insets(10, 20, 0, 0);
        contraine.gridy =0;
        add(labelPrincipal);
        ajouterDocument.add(champsTitre, contraine);
        contraine.gridx =1;
        ajouterDocument.add(champsNOmAuteur, contraine);
        contraine.gridx =0;
        contraine.gridy =1;
        ajouterDocument.add(champsNombrePage, contraine);
        contraine.gridx =1;
        ajouterDocument.add(champsNombreExemplaire, contraine);
        contraine.gridx =1;
        contraine.gridy =2;
        ajouterDocument.add(ajouter, contraine);
//  Premiere logique de l'application 
        ajouter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String titre = champsTitre.getText();
                String auteur = champsNOmAuteur.getText();
                int nomDePage = Integer.parseInt(champsNombrePage.getText());
                int nomExemplaire = Integer.parseInt(champsNombreExemplaire.getText());
                Bibliotheque.ajouter_document(titre, auteur,nomDePage, nomExemplaire , "Revues");
                revalidate();
                repaint();
            }
        });
        

        // Ajout des widgets au contenu principale
        add(ajouterDocument);

    }
}
