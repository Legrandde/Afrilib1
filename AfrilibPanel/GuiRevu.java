package AfrilibPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import AfrilibClass.Adherant;

public class GuiRevu  extends JPanel{
    
    public GuiRevu(){
        setBackground(Color.ORANGE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(950, 100));
        setPreferredSize(new Dimension(950, 500));
        

        
        // ================================================================================



//          widjets pour livre disponible ===============================================================
        JLabel etiquetteLivreDiponible = new JLabel("Liste des  Revues disponibles");
        // etiquetteLivreDiponible.setForeground(Color.WHITE);
        JPanel paneauTableau = new JPanel();
        JButton btnEmpreinter = new JButton("Empreinter");
        btnEmpreinter.setBackground(Color.orange);
        paneauTableau.setLayout(new BorderLayout());
        etiquetteLivreDiponible.setFont(new Font("Poppins", Font.BOLD, 20));
        String[] EntetelisteLivre = {"Titre","Auteur", "nombre de page", "Nombre d'exemplaire"};
        ArrayList<ArrayList<Object>> listRevues = new ArrayList<>();
        String ligne;
        try{
                // Recuperation des revues dans le fichier Revues.txt et affichage ligne par ligne des revues disponibles
                BufferedReader lecteur = new BufferedReader(new FileReader("BaseDeDonnee/Revues.txt"));
                while ((ligne = lecteur.readLine())!=null) {
                        String[] attribu = ligne.split("&&");
                        if(attribu.length >=3)
                        listRevues.add(new ArrayList<>(Arrays.asList(attribu[0],attribu[1], attribu[2], attribu[3])));
                }
                lecteur.close();
        }
        catch( IOException e){
                System.out.print("erreur "+ e.getMessage());
        }
    
        Object[][] donneeLivreDispo = new Object[listRevues.size()][];
        for(int i = 0 ; i< listRevues.size(); i++){
                donneeLivreDispo[i]= listRevues.get(i).toArray();
        }
        JTable taleauLivreDisponible = new JTable(donneeLivreDispo, EntetelisteLivre);
        repaint();
        taleauLivreDisponible.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int libreSelectionnee = taleauLivreDisponible.getSelectedRow();
                    if (libreSelectionnee != -1) {
                        Object nomLivreSelectionnee = taleauLivreDisponible.getValueAt(libreSelectionnee, 0);

                        String Matricule =JOptionPane.showInputDialog(null, "votre matricule");
                        btnEmpreinter.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous Empreinter " + nomLivreSelectionnee, "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (choix == JOptionPane.YES_OPTION) {
                                    Adherant.empreiter(nomLivreSelectionnee);
                                    
                                } else if (choix == JOptionPane.NO_OPTION) {
                                    JOptionPane.showMessageDialog(null, "Échec d'emprunt");
                                }
                            }
                        });
                    }
                }
        });
        add(etiquetteLivreDiponible, BorderLayout.NORTH);
        add(paneauTableau);
        add(btnEmpreinter, BorderLayout.SOUTH);
        paneauTableau.add(taleauLivreDisponible.getTableHeader(), BorderLayout.NORTH);
        paneauTableau.add(taleauLivreDisponible, BorderLayout.CENTER);
    }
}
