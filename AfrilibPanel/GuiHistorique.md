import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GuiHistorique extends JPanel {

    public GuiHistorique(){

        setLayout(new BorderLayout());
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
        String[] EntetelisteLivre = {"Titre","Prix", "Status", "Etat", "Date d'empreint","Matricule Adherant"};
        Object[][] donneeLivreDispo = {
            {"Batouala", 5000+"CFA", "Rendu", "Bon Etat", LocalDate.of(2024,12,3),23},
            {"Enfant noir", 5000+"CFA", "pre en cours", "Bon Etat", LocalDate.now(), 34},
            {"Cercle de trophique",5000+ "CFA", "pre en cours", "mauvais etat", LocalDate.now(), 20},
            {"Une si long lettre ", 5000+"CFA", "encours", "mauvais etat", LocalDate.now(), 20}
    };
        JTable taleauLivreDisponible = new JTable(donneeLivreDispo, EntetelisteLivre);
        listLivreDispo.add(etiquetteLivreDiponible, BorderLayout.NORTH);
        listLivreDispo.add(paneauTableau);
        paneauTableau.add(taleauLivreDisponible.getTableHeader(), BorderLayout.NORTH);
        paneauTableau.add(taleauLivreDisponible, BorderLayout.CENTER);
        JButton btnRendredocument = new JButton("Rendre Livre");
        btnRendredocument.setBackground(Color.BLACK);
        btnRendredocument.setForeground(Color.white);
        add(btnRendredocument, BorderLayout.SOUTH);
        taleauLivreDisponible.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int ligneSelectionnee = taleauLivreDisponible.getSelectedRow();
                Object nomDocument = taleauLivreDisponible.getValueAt(ligneSelectionnee, 0);
                Object statusDocument = taleauLivreDisponible.getValueAt(ligneSelectionnee, 2);
                Object etatLivre = taleauLivreDisponible.getValueAt(ligneSelectionnee, 3);
                LocalDate dateDempreint = (LocalDate) taleauLivreDisponible.getValueAt(ligneSelectionnee, 4);
                long diffDate = ChronoUnit.DAYS.between(dateDempreint, LocalDate.now());
        
                if (statusDocument.equals("Rendu")) {
                    JOptionPane.showMessageDialog(null, "Ce Livre est Déjà rendu !");
                } else {
                    JOptionPane.showMessageDialog(null, "Le livre est-il en bon état ?", "Vérification état livre", JOptionPane.QUESTION_MESSAGE);
                    if (etatLivre.equals("Bon Etat") && diffDate < 30) {
                        JOptionPane.showMessageDialog(null, "Document rendu en bon état et à temps");
                        try (BufferedReader reader = new BufferedReader(new FileReader("BaseDeDonnee/emprunt.txt"))) {
                            String ligne;
                            while ((ligne = reader.readLine()) != null) {
                                String[] champs = ligne.split("&&");
                                if (champs[0].equals("20")) { // Supposant que 20 est un identifiant
                                    for (int i = 1; i < 3; i++) {
                                        if (champs[i].equals(nomDocument)) {
                                            champs[i] = "Rien";
                                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("BaseDeDonnee/emprunt.txt"))) {
                                                writer.write(String.join("&&", champs));
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            System.err.println("Erreur de lecture/écriture : " + ex.getMessage());
                        }
                    } else {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BaseDeDonnee/taxe.txt", true))) {
                            if (etatLivre.equals("mauvais état")) {
                                writer.write("Document en mauvais état&&5000&&20&&non payer");
                                writer.newLine();
                                JOptionPane.showMessageDialog(null, "Document rendu en mauvais état, une amende de 5000 vous est attribuée");
                            } else if (diffDate >= 30) {
                                writer.write("retard&&1200&&20&&non payer");
                                writer.newLine();
                                JOptionPane.showMessageDialog(null, "Document rendu en retard, une amende de 1200 vous est attribuée");
                            }
                        } catch (IOException ex) {
                            System.err.println("Échec d'écriture : " + ex.getMessage());
                        }
                    }
                }
            }
        });

        btnRendredocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
            }
        });


// ====================================================================================================================================
        add(status,BorderLayout.NORTH);
        add(listLivreDispo, BorderLayout.CENTER);
        setBackground(Color.BLUE);
    }

    
}