import javax.swing.ImageIcon;
import javax.swing.JFrame;

import AfrilibFenetre.ContenuPrimaire;


//  C'est la classe pour l'entrer du  programme

public class Afrilib {

    public static void main(String[] args) {

        JFrame fenetrePrimaire = new JFrame("Afrilib");
        ImageIcon icon = new ImageIcon("images/equipe.png");
        fenetrePrimaire.setIconImage(icon.getImage());

        
        fenetrePrimaire.setContentPane(new ContenuPrimaire());
        fenetrePrimaire.setSize(800, 600);
        fenetrePrimaire.setLocationRelativeTo(null);
        fenetrePrimaire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetrePrimaire.setVisible(true);

    }
}