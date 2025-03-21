package AfrilibFenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AfrilibClass.Adherant;
import AfrilibClass.Bibliotheque;

public class GestionAdherent extends JPanel{
    public GestionAdherent(){
 
        setLayout(new BorderLayout());
        // Declaration des widgets a mettre sur le contenu principale
        JLabel labelPrincipal = new JLabel("Ajouter un adherent");
        labelPrincipal.setFont(new Font("Poppins", Font.BOLD, 38));
        JPanel ajouterDocument = new JPanel();
        ajouterDocument.setLayout(new GridBagLayout());
        JTextField nomAdherent = new JTextField("Nom de l'adherent");
        nomAdherent.setPreferredSize(new Dimension(200, 30));
        JTextField prenomAdherent = new JTextField("Prenom de l'adherent");
        prenomAdherent.setPreferredSize(new Dimension(200, 30));
        JTextField nombreDocsEmpreinter = new JTextField("Nombre de document empreinter");
        nombreDocsEmpreinter.setPreferredSize(new Dimension(200, 30));
        JTextField emailAdherent = new JTextField("Email de l'aderent");
        emailAdherent.setPreferredSize(new Dimension(200, 30));
        // Boutton pour ajouter le livre
        JButton ajouter = new JButton(" + Ajouter");

        // =========================Ajout des widgets au contenu principal et disposition des widgets=========================================
        GridBagConstraints contraine = new GridBagConstraints();
        contraine.gridx =0;
        contraine.insets = new Insets(10, 20, 0, 0);
        contraine.gridy =0;
        add(labelPrincipal);
        ajouterDocument.add(nomAdherent, contraine);
        contraine.gridx =1;
        ajouterDocument.add(prenomAdherent, contraine);
        contraine.gridx =0;
        contraine.gridy =1;
        ajouterDocument.add(nombreDocsEmpreinter, contraine);
        contraine.gridx =1;
        ajouterDocument.add(emailAdherent, contraine);
        contraine.gridx =1;
        contraine.gridy =2;
        ajouterDocument.add(ajouter, contraine);
//  Premiere logique de l'application 
        ajouter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                 
                String Matricule = nomAdherent.getText();

                String nom = nomAdherent.getText();
                String prenom = prenomAdherent.getText();
                String email = emailAdherent.getText();
                Adherant adherant =new Adherant(Adherant.getMatricule(), nom, prenom, email, "adherent");
                Bibliotheque.enregistrer_adherant(adherant);
                revalidate();
                repaint();
            }
        });
        

        // Ajout des widgets au contenu principale
        add(ajouterDocument);

    }
}
