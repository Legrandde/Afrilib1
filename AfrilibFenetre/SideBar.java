package AfrilibFenetre;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideBar extends JPanel {

    public SideBar(){
        
        
        JPanel panelDocument = new JPanel();
        // panelDocument.setBackground(Color.WHITE);
        panelDocument.setLayout(new GridBagLayout());
        GridBagConstraints contraint =new GridBagConstraints();
        contraint.gridy = 0;
        
        
        
       
        contraint.gridy = 1;
        contraint.insets = new Insets(10, 0, 0, 0);
        
        
        JPanel panelAdherant = new JPanel();
        panelAdherant.setLayout(new GridBagLayout());
        panelAdherant.setForeground(Color.WHITE);
        GridBagConstraints contrain =new GridBagConstraints();
        contrain.gridy = 0;

        

        setBackground(Color.decode("#333"));
        setPreferredSize(new Dimension(200, 700));

        
    }
}
