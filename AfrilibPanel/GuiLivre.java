package AfrilibPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import AfrilibClass.Adherant;
import AfrilibClass.Bibliotheque;

import java.awt.event.MouseEvent;

public class GuiLivre  extends JPanel{
    Bibliotheque bibliotheque = new Bibliotheque();
    public GuiLivre(){
        setBackground(Color.ORANGE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(950, 100));
        setPreferredSize(new Dimension(950, 500));
        

        
        // ================================================================================



//          widjets pour livre disponible ===============================================================
        JLabel etiquetteLivreDiponible = new JLabel("Liste des  Livres disponibles");
        etiquetteLivreDiponible.setBackground(Color.decode("#333"));
        // etiquetteLivreDiponible.setForeground(Color.WHITE);
        JPanel paneauTableau = new JPanel();
        paneauTableau.setLayout(new BorderLayout());
        etiquetteLivreDiponible.setFont(new Font("Poppins", Font.BOLD, 20));
        String[] EntetelisteLivre = {"Titre","Auteur", "nombre de page", "Nombre d'exemplaire"};
        JTable taleauLivreDisponible = new JTable(Bibliotheque.afficher_tous_documents(), EntetelisteLivre);
        revalidate();
        repaint();
        add(etiquetteLivreDiponible, BorderLayout.NORTH);
        add(paneauTableau);
        paneauTableau.add(taleauLivreDisponible.getTableHeader(), BorderLayout.NORTH);
        paneauTableau.add(taleauLivreDisponible, BorderLayout.CENTER);

        JPanel btns = new JPanel();
        JButton btnEmpreinter = new JButton("Empreinter");
        btnEmpreinter.setBackground(Color.decode("#333"));
        btnEmpreinter.setForeground(Color.WHITE);
        JButton btnREserver = new JButton("Reserver");
        btnREserver.setBackground(Color.ORANGE);
        
        btns.add(btnREserver);
        btns.add(btnEmpreinter);
        add(btns, BorderLayout.SOUTH);
        taleauLivreDisponible.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int libreSelectionnee = taleauLivreDisponible.getSelectedRow();
                    if (libreSelectionnee != -1) {
                        Object nomLivreSelectionnee = taleauLivreDisponible.getValueAt(libreSelectionnee, 0);
                        btnEmpreinter.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Adherant.empreiter(nomLivreSelectionnee);
                            }
                        });

                        btnREserver.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Adherant.reserverDocument();;
                            }
                        });
                    }
                }
        });

    }
}
