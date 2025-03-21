package AfrilibFenetre;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class Historique extends JFrame {

    public Historique(){

        JPanel contenuePrincipal = new JPanel();
        contenuePrincipal.setLayout(new FlowLayout());
        JPanel status = new JPanel();
        JLabel etiquetestates = new JLabel("Statistique");
        JPanel livre = new JPanel();
        livre.add(new JLabel("Livres"));

        JPanel revues = new JPanel();
        revues.add(new JLabel("Revues"));
        JPanel nouvelles = new JPanel();
        nouvelles.add(new JLabel("Nouvelles"));
        status.setBackground(Color.YELLOW);
        status.setLayout(new GridBagLayout());
        GridBagConstraints contraintes = new GridBagConstraints();
        contraintes.gridx = 0;
        contraintes.insets = new Insets(0, 25, 0, 0);
        contraintes.gridy =0;
        etiquetestates.setFont(new Font("Poppins",  Font.BOLD, 20));
        livre.setBackground(Color.PINK);
        livre.setPreferredSize(new Dimension(200, 100));
        revues.setBackground(Color.GRAY);
        revues.setPreferredSize(new Dimension(200, 100));
        nouvelles.setBackground(Color.RED);
        nouvelles.setPreferredSize(new Dimension(200, 100));


        JPanel listLivreDispo = new JPanel();
        listLivreDispo.setBackground(Color.GREEN);
        listLivreDispo.setLayout(new BorderLayout());
        status.setPreferredSize(new Dimension(950, 200));
        listLivreDispo.setPreferredSize(new Dimension(950, 500));
        

        status.add(etiquetestates, contraintes);
        contraintes.gridx = 0;
        contraintes.gridy = 1;
        status.add(livre, contraintes);
        contraintes.gridy = 1;
        contraintes.gridx = 1;
        status.add(revues, contraintes);
        contraintes.gridy = 1;
        contraintes.gridx = 2;
        status.add(nouvelles, contraintes);

//          widjets pour livre disponible ===============================================================
        JLabel etiquetteLivreDiponible = new JLabel("Historique des empreints");
        JPanel paneauTableau = new JPanel();
        paneauTableau.setLayout(new BorderLayout());
        etiquetteLivreDiponible.setFont(new Font("Poppins", Font.BOLD, 20));
        String[] EntetelisteLivre = {"Titre","Prix", "Status"};
        Object[][] donneeLivreDispo = {
            {"Batouala", 4000+"CFA", "Rendu"},
            {"Enfant noir", 5000+"CFA", "pre en cours"},
            {"Cercle de trophique",2000+ "CFA", "pre en cours"},
            {"Une si long lettre ", 4000+"CFA", "pre en cours"}
    };
        JTable taleauLivreDisponible = new JTable(donneeLivreDispo, EntetelisteLivre);
        listLivreDispo.add(etiquetteLivreDiponible, BorderLayout.NORTH);
        listLivreDispo.add(paneauTableau);
        paneauTableau.add(taleauLivreDisponible.getTableHeader(), BorderLayout.NORTH);
        paneauTableau.add(taleauLivreDisponible, BorderLayout.CENTER);


    
// ====================================================================================================================================

        contenuePrincipal.add(status);
        contenuePrincipal.add(listLivreDispo);
        contenuePrincipal.setBackground(Color.BLUE);
        setContentPane(contenuePrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Afrilib - hisotique");
        setVisible(true);
        
    }
}
