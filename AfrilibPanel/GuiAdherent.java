package AfrilibPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import AfrilibClass.Bibliotheque;

public class GuiAdherent extends JPanel{
    Bibliotheque bibliotheque = new Bibliotheque();
    public GuiAdherent(){

        JPanel status = new JPanel();
        //===================  Intetiations des widgets de Status ===========================================================================================

        JLabel etiquetestates = new JLabel("Chercher un Adherent");
        etiquetestates.setFont(new Font("poppins", Font.BOLD, 20));
        etiquetestates.setForeground(Color.WHITE);
        // String[] optionDeTrie = {"Livre", "Revue", "Nouvelle"};
        // JComboBox<String> trieRecherche = new JComboBox<>(optionDeTrie);
        // trieRecherche.setBackground(Color.decode("#7DC2A5"));
        JTextField champsRecherche = new JTextField();
        JButton chercher = new JButton("Chercher");
        chercher.setBackground(Color.ORANGE);
        chercher.setForeground(Color.BLACK);
        champsRecherche.setPreferredSize(new Dimension(200, 30));

        status.setBackground(Color.decode("#333"));
        status.setLayout(new GridBagLayout());
        GridBagConstraints contraintes = new GridBagConstraints();
        contraintes.gridy =0;
        contraintes.anchor = GridBagConstraints.CENTER;

        // ==============Ajout des elements de status ===============================================

        status.add(etiquetestates, contraintes);
        contraintes.gridy =1;
        // status.add(trieRecherche, contraintes);
        contraintes.gridy =1;
        contraintes.gridx =1;
        status.add(champsRecherche, contraintes);
        contraintes.gridx=2;
        status.add(chercher, contraintes);

//      ================================================================================================

        setBackground(Color.decode("#7DC2A5"));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(950, 100));
        setPreferredSize(new Dimension(950, 500));
        

        
        // ================================================================================



//          widjets pour livre disponible ===============================================================
        JLabel etiquetteLivreDiponible = new JLabel("Liste des Adherents");
        JPanel paneauTableau = new JPanel();
        paneauTableau.setLayout(new BorderLayout());
        etiquetteLivreDiponible.setFont(new Font("Poppins", Font.BOLD, 20));
        String[] Matricule =new String[1];
        chercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Matricule[0] = champsRecherche.getText();
                
            }
        });

        String[] EntetelisteLivre = {"Matricule","nom", "Prenom", "E-mail", "Nombre de documents empreinter"};
        JTable taleauLivreDisponible = new JTable(Bibliotheque.recherche_adherant(), EntetelisteLivre);
        repaint();
        add(status, BorderLayout.NORTH);
        add(etiquetteLivreDiponible, BorderLayout.CENTER);
        add(paneauTableau);
        paneauTableau.add(taleauLivreDisponible.getTableHeader(), BorderLayout.NORTH);
        paneauTableau.add(taleauLivreDisponible, BorderLayout.CENTER);
    }
}
