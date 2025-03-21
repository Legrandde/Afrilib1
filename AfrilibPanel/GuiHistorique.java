package AfrilibPanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import AfrilibClass.Adherant;
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

public class GuiHistorique extends JPanel {

    public GuiHistorique(){
        // Agorithme pour compter le nombre de livre
       


        setLayout(new BorderLayout());
        JPanel status = new JPanel();
        JLabel etiquetestates = new JLabel("Statistique");
        JPanel livre = new JPanel();
        livre.add(new JLabel("Livres"));
        String nombreLivre = ""+compteurDocs("BaseDeDonnee/Livre.txt");
        livre.add(new JLabel(nombreLivre));

        JPanel revues = new JPanel();
        
        revues.add(new JLabel("Revues"));
        String nombreRevue = ""+compteurDocs("BaseDeDonnee/Revues.txt");
        revues.add(new JLabel(nombreRevue));
        JPanel nouvelles = new JPanel();
        nouvelles.add(new JLabel("Articles"));
        String nombreArticles = ""+compteurDocs("BaseDeDonnee/Articles.txt");
        nouvelles.add(new JLabel(nombreArticles));
        status.setBackground(Color.decode("#333"));
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
        listLivreDispo.setBackground(Color.ORANGE);
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
        String[] EntetelisteLivre = {"Matricule", "Titre du livre"};
        Object[][] donneeLivreDispo = Adherant.afficherEmprunt();
       
        JTable taleauLivreDisponible = new JTable(donneeLivreDispo, EntetelisteLivre);
        revalidate();
        repaint();
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
            public void mouseClicked(MouseEvent e){
                int ligneSelectionnee = taleauLivreDisponible.getSelectedRow();
                

                Object nomDocument = taleauLivreDisponible.getValueAt(ligneSelectionnee, 0);
                String nomDocumentStr = (String) nomDocument;
                Object statusdocument = taleauLivreDisponible.getValueAt(ligneSelectionnee, 1);
                String nomDoc = (String) statusdocument;
                // Object dateDempreint = taleauLivreDisponible.getValueAt(ligneSelectionnee, 4);
                // LocalDate date = (LocalDate) dateDempreint;
                // long diffDate = ChronoUnit.DAYS.between(date, LocalDate.now());
                int choix = JOptionPane.showConfirmDialog(null, "Le livre est il en bon etat", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(choix == JOptionPane.YES_OPTION){
                    btnRendredocument.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            Adherant.rendreDocument(nomDoc, nomDocumentStr);
                        }
                    });
            
                }
                else{
                    int choix1 = JOptionPane.showConfirmDialog(null, "Le livre est il en retard", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (choix1 == JOptionPane.YES_OPTION) {
                        try{
                            BufferedWriter ecriAmande = new BufferedWriter(new FileWriter("BaseDeDonnee/taxe.txt", true));
                            ecriAmande.write("retard&&1200&&"+nomDocumentStr+"&&non payée");
                            ecriAmande.close();
                            JOptionPane.showMessageDialog(null, "Document rendu en retard: Amande de 1200FCFA");
                        }
                        catch(IOException errtaxe){
                            System.out.println(errtaxe.getMessage());
                        }
                    }
                    else if (choix1 == JOptionPane.NO_OPTION) {
                        try{
                            BufferedWriter ecriAmande = new BufferedWriter(new FileWriter("BaseDeDonnee/taxe.txt", true));
                            ecriAmande.write("Abimé&&5000&&"+nomDocumentStr+"&&non payée");
                            JOptionPane.showMessageDialog(null, "Document rendu abimé: Amande de 5000FCFA");
                            ecriAmande.close();
                        }
                        catch(IOException errtaxe){
                            System.out.println(errtaxe.getMessage());
                        }
                    }

                }
            
                // JOptionPane.showMessageDialog(null, "Vous avez choisi de rendre  "+nomDocument+ " Son status est : "+statusdocument);
                
            }
        });

       

// ====================================================================================================================================
        add(status,BorderLayout.NORTH);
        add(listLivreDispo, BorderLayout.CENTER);
        setBackground(Color.BLUE);
    }

    public int compteurDocs(String cheminVersFichier){
        int compteurLivre = 0;
        try{
            BufferedReader cmpt = new BufferedReader(new FileReader(cheminVersFichier));
            
            while (cmpt.readLine()!=null) {
                compteurLivre++;
            }
            cmpt.close();
        }
        catch(IOException erCount){
            System.out.println(erCount.getMessage());
        }
        return compteurLivre-1;
    }

    
}