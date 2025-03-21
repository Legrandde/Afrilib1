package AfrilibFenetre;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Il s'agit la du panel ratacher a la premiere fenetre du logiciel

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ContenuPrimaire extends JPanel {
    private Image arrierePlan;
    public ContenuPrimaire(){


        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        arrierePlan = new ImageIcon("images/arrierePlan.jpg").getImage();
        JLabel labelBienvenu = new JLabel("<html>Bienvenu sur Afrilib <br> </html>");
        labelBienvenu.setFont(new Font("Algerian", Font.BOLD, 48));
        JTextArea paragraphe1 = new JTextArea("La bibliothèque africaine où vous pouvez emprunter des documents de toutes sortes. Ne perdez plus votre temps pour lire le roman de vos rêves, donc commencez maintenant!");
        
        paragraphe1.setFont(new Font("Arial", Font.PLAIN, 16));
        paragraphe1.setForeground(Color.WHITE);
        paragraphe1.setLineWrap(true); // Activer le retour à la ligne
        paragraphe1.setWrapStyleWord(true); // Retour à la ligne par mot
        paragraphe1.setOpaque(false); // Rendre le fond transparent
        paragraphe1.setEditable(false); // Désactiver l'édition
        paragraphe1.setBackground(new Color(0, 0, 0, 0)); // Fond transparent
        paragraphe1.setPreferredSize(new Dimension(400, 50));
        JButton btnBienvenu = new JButton("Commencer");


        btnBienvenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new Authentification();
                // fenetrePrimaire.dispose();
            }
        });

        

        GridBagConstraints contraite = new GridBagConstraints();
        contraite.gridx = 0;
        contraite.gridy = 0;
        add(labelBienvenu, contraite);

        contraite.gridx = 0;
        contraite.gridy = 1;
        contraite.fill = GridBagConstraints.HORIZONTAL;
        add(paragraphe1, contraite);

        contraite.gridx = 0;
        contraite.gridy = 2;
        contraite.ipady = 10;
        contraite.gridheight = 2;
        contraite.fill = GridBagConstraints.NONE;
        contraite.insets = new Insets(10, 0, 0, 0);
        btnBienvenu.setBackground(Color.decode("#333"));
        btnBienvenu.setForeground(Color.WHITE);
        add(btnBienvenu, contraite);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(arrierePlan, 0, 0, getWidth(), getHeight(), this);
    }
}
