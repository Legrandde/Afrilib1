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

public class GuiTaxe extends JPanel {

    public GuiTaxe(){

        setLayout(new BorderLayout());
        JPanel status = new JPanel();
        JLabel etiquetestates = new JLabel("Statistique");
        JPanel livre = new JPanel();
        livre.add(new JLabel("Livres"));

        JPanel revues = new JPanel();
        revues.add(new JLabel("Revues"));
        JPanel nouvelles = new JPanel();
        nouvelles.add(new JLabel("Nouvelles"));
        status.setBackground(Color.orange);
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
        listLivreDispo.setBackground(Color.decode("#333"));
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
        JLabel etiquetteLivreDiponible = new JLabel("Listre des taxes");
        etiquetestates.setForeground(Color.WHITE);
        etiquetteLivreDiponible.setForeground(Color.WHITE);
        JPanel paneauTableau = new JPanel();
        paneauTableau.setLayout(new BorderLayout());
        etiquetteLivreDiponible.setFont(new Font("Poppins", Font.BOLD, 20));
        String[] EntetelisteLivre = {"Nature Taxe", "Montant à payer", "Matriculle adherent", "Situation"};
        Object[][] donneeLivreDispo = Adherant.afficherTaxe();
        JTable taleauLivreDisponible = new JTable(donneeLivreDispo, EntetelisteLivre);
        repaint();
        listLivreDispo.add(etiquetteLivreDiponible, BorderLayout.NORTH);
        listLivreDispo.add(paneauTableau);
        paneauTableau.add(taleauLivreDisponible.getTableHeader(), BorderLayout.NORTH);
        paneauTableau.add(taleauLivreDisponible, BorderLayout.CENTER);
        JButton payerTaxe = new JButton("Payer la taxe");
        payerTaxe.setBackground(Color.ORANGE);
        payerTaxe.setForeground(Color.white);
        add(payerTaxe, BorderLayout.SOUTH);
        payerTaxe.setForeground(Color.decode("#333"));
        taleauLivreDisponible.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int ligneSelectionnee = taleauLivreDisponible.getSelectedRow();
                

                Object nomDocument = taleauLivreDisponible.getValueAt(ligneSelectionnee, 0);
                String natureTaxe = (String) nomDocument;
                Object statusdocument = taleauLivreDisponible.getValueAt(ligneSelectionnee, 1);
                String montantApayer = (String) statusdocument;

                Object etatLivre = taleauLivreDisponible.getValueAt(ligneSelectionnee, 2);
                String matricule = (String) etatLivre;
                // Object dateDempreint = taleauLivreDisponible.getValueAt(ligneSelectionnee, 4);
                // LocalDate date = (LocalDate) dateDempreint;
                // long diffDate = ChronoUnit.DAYS.between(date, LocalDate.now());
                int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous payer la taxe", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(choix == JOptionPane.YES_OPTION){
                    payerTaxe.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            Adherant.payerTaxe(natureTaxe, montantApayer,matricule);
                        }
                    });
            
                }
                else
                    JOptionPane.showMessageDialog(null, "Payement annulé");
                    
                
            
                // JOptionPane.showMessageDialog(null, "Vous avez choisi de rendre  "+nomDocument+ " Son status est : "+statusdocument);
                
            }
        });

       

// ====================================================================================================================================
        add(status,BorderLayout.NORTH);
        add(listLivreDispo, BorderLayout.CENTER);
        setBackground(Color.decode("#333"));
    }

    
}