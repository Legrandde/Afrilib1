package AfrilibFenetre;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

public class SideBarAdherant extends JPanel {

    public SideBarAdherant(){
        setLayout(new FlowLayout());
        GridBagConstraints contrainte = new GridBagConstraints();
        contrainte.gridy = 0;
        contrainte.insets = new Insets(20, 0, 0, 0);
        contrainte.anchor = GridBagConstraints.NORTH;
        contrainte.fill = GridBagConstraints.HORIZONTAL;
        contrainte.weightx= 1.0;
        JPanel panelDocument = new JPanel();
        // panelDocument.setBackground(Color.WHITE);
        panelDocument.setLayout(new GridBagLayout());
        GridBagConstraints contraint =new GridBagConstraints();
        contraint.gridy = 0;
        contraint.insets = new Insets(10, 0, 0, 0);
        
        
        JPanel panelAdherant = new JPanel();
        panelAdherant.setLayout(new GridBagLayout());
        panelAdherant.setForeground(Color.WHITE);
        GridBagConstraints contrain =new GridBagConstraints();
        contrain.gridy = 0;

        
        

        setBackground(Color.decode("#333"));
        setPreferredSize(new Dimension(200, 700));

        add(panelDocument, contrainte);
        contrainte.gridy = 1;
        add(panelAdherant, contrainte);
    }
}
