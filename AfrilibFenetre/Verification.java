package AfrilibFenetre;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Verification {
    public Verification(){


    }

    public void verifier_etat_document(boolean etatDocument, boolean atemps){
        if (etatDocument &&  atemps) {
            JOptionPane.showMessageDialog(null, "Livre rendue en bon etat", "Rendre livre", 0);
        }
        else{
            try{
                BufferedWriter ecris = new BufferedWriter(new FileWriter("BaseDeDonne/taxe.txt"));
                if(!etatDocument)
                    ecris.write("Document en mauvais etat && "+"500 && 20 && non payer");
                if(!atemps)
                    ecris.write("Retart && "+"120 && 20 && non payer");
                ecris.newLine();
                ecris.close();
            }
            catch(IOException e){
                System.err.println("EChec");
            }
        }
    }

    public void payerAmender(int Matricule){

        try{

            BufferedReader lire = new BufferedReader(new FileReader("BaseDeDonnee/taxe.txt"));
            String contenuTaxe;
            String[] listeTaxe;
            while ((contenuTaxe = lire.readLine())!=null) {
                
                listeTaxe  = contenuTaxe.split("&&");
                listeTaxe[3]="payer";
                lire.close();
                try{
                    BufferedWriter ecris = new BufferedWriter(new FileWriter("BaseDeDonne/taxe.txt"));
                    
                    ecris.write(listeTaxe.toString());
                    ecris.close();
                }
                catch(IOException e){
                    System.err.println("EChec");
                }

            }
            
        }
        catch(IOException erreur){
            System.out.println("erreur");
        }
    }
}
