package AfrilibFenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Authentification extends JFrame {
    
    public Authentification(){


        JPanel conteneur = new JPanel();
        conteneur.setLayout(new BorderLayout());
        conteneur.setBackground(Color.BLACK);
        JPanel conteneurImage = new JPanel();
        ImageIcon iconSecurite = new ImageIcon("images/login.png");
        Image imager = iconSecurite.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        iconSecurite = new ImageIcon(imager);
        JLabel imageSecurite = new JLabel(iconSecurite);
        conteneurImage.add(imageSecurite);

        conteneurImage.setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();
        contrainte.gridy=1;
        JLabel bonretour = new JLabel("Bon retour parmis nous");
        bonretour.setFont(new Font("poppins", Font.BOLD, 48));
        bonretour.setForeground(Color.WHITE);
        conteneurImage.add(bonretour, contrainte);
        conteneurImage.setBackground(Color.decode("#333"));
        conteneurImage.setPreferredSize(new Dimension(600, 700));
        

        contrainte.gridy = 2;
        JLabel t1 = new JLabel("Veuillez renseigner vos identifiants");
        t1.setFont(new Font("poppins", Font.BOLD , 16));
        t1.setForeground(Color.WHITE);
        conteneurImage.add(t1, contrainte);
        contrainte.gridy = 3;

        JLabel t2 = new JLabel("pour pouvoir accedée a votre espace administrateur");
        t2.setFont(new Font("poppins", Font.BOLD , 16));
        t2.setForeground(Color.WHITE);
        conteneurImage.add(t2, contrainte);
        ImageIcon gif = new ImageIcon("images/Contact-us.gif");
        JLabel arrierPlan = new JLabel(gif);
        arrierPlan.setBounds(0,0,500,400);
        // conteneurImage.add(arrierPlan);


        // Pour le formulaire 
        
        JPanel conteneurFormulaie = new JPanel();

        conteneurFormulaie.setLayout(new GridBagLayout());
        conteneurFormulaie.setBackground(Color.ORANGE);
        conteneurFormulaie.setPreferredSize(new Dimension(600, 700));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
    
        JLabel connexion = new JLabel("Connexion");
        connexion.setFont(new Font("poppins", Font.BOLD, 30));
        conteneurFormulaie.add(connexion);
        JLabel identifiant = new JLabel("Identifiant");
        gbc.gridy = 1;
        conteneurFormulaie.add(identifiant, gbc);
        JTextField champsNOm = new JTextField();
        gbc.gridy= 2;
        champsNOm.setPreferredSize(new Dimension(250, 30));
        conteneurFormulaie.add(champsNOm, gbc);
        gbc.gridy= 3;
        JLabel motdePass = new JLabel("Mots de passe");
        conteneurFormulaie.add(motdePass, gbc);
        JTextField champsMotsDePasse = new JTextField();
        gbc.gridy= 4;
        champsMotsDePasse.setPreferredSize(new Dimension(250, 30));
        conteneurFormulaie.add(champsMotsDePasse, gbc);
        JButton envoyer = new JButton("connexion");
        envoyer.setPreferredSize(new Dimension(200, 30));
        envoyer.setBackground(Color.decode("#333"));
        envoyer.setForeground(Color.WHITE);
        gbc.gridy= 5;
        gbc.insets = new Insets(10, 0, 0, 0);
        conteneurFormulaie.add(envoyer, gbc);



        // Partie logique et gestion administrateurs


        envoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(champsNOm.getText().isEmpty() || champsMotsDePasse.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Veuillez entrer les identifians", "Authentification", 0);

                else{
                    if(champsNOm.getText().equals("hamath") && champsMotsDePasse.getText().equals("hamathpass")){
                        JOptionPane.showMessageDialog(null, "Identifiants valide connexion", "Authentification", 1);
                        new TableauDeBord();
                    }
                    else if(champsNOm.getText().equals("peniel") && champsMotsDePasse.getText().equals("penielpass")){
                        JOptionPane.showMessageDialog(null, "Identifiants valide connexion", "Authentification", 1);
                        dispose();
                        new TableauDeBord();
                    }
                    else if(champsNOm.getText().equals("barry") && champsMotsDePasse.getText().equals("barrypass")){
                        JOptionPane.showMessageDialog(null, "Identifiants valide connexion", "Authentification", 1);
                        new TableauDeBord();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Identifiants invalid", "Authetification", 0);
                }
            }
        });




 



        ImageIcon icon = new ImageIcon("images/equipe.png");
        setIconImage(icon.getImage());
        // Ajout des composants finals
        conteneur.add(conteneurImage, BorderLayout.WEST);
        conteneur.add(conteneurFormulaie, BorderLayout.CENTER);
        setContentPane(conteneur);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    
}
