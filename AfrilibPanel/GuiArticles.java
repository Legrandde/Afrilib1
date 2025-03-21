package AfrilibPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import AfrilibClass.Adherant;

import java.awt.event.MouseEvent;

public class GuiArticles  extends JPanel{
    
    public GuiArticles(){
        setBackground(Color.ORANGE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(950, 100));
        setPreferredSize(new Dimension(950, 500));
        

        
        // ================================================================================



//          widjets pour livre disponible ===============================================================
        JLabel etiquetteLivreDiponible = new JLabel("Liste des  Articles disponibles");
        JPanel paneauTableau = new JPanel();
        paneauTableau.setLayout(new BorderLayout());
        etiquetteLivreDiponible.setFont(new Font("Poppins", Font.BOLD, 20));
        String[] EntetelisteLivre = {"Titre","Auteur", "nombre de page", "Nombre d'exemplaire"};
        ArrayList<ArrayList<Object>> listArticles = new ArrayList<>();
        String ligne;
        try{
                BufferedReader lecteur = new BufferedReader(new FileReader("BaseDeDonnee/Articles.txt"));
                while ((ligne = lecteur.readLine())!=null) {
                        String[] attribu = ligne.split("&&");
                        listArticles.add(new ArrayList<>(Arrays.asList(attribu[0],attribu[1], attribu[2], attribu[3])));
                }
                lecteur.close();
        }
        catch( IOException e){
                System.out.print("erreur "+ e.getMessage());
        }
    
        Object[][] donneeLivreDispo = new Object[listArticles.size()][];
        for(int i = 0 ; i< listArticles.size(); i++){
                donneeLivreDispo[i]= listArticles.get(i).toArray();
        }
        JTable taleauLivreDisponible = new JTable(donneeLivreDispo, EntetelisteLivre);
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
