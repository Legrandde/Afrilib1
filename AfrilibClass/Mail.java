package AfrilibClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.swing.JOptionPane;

public class Mail {
    public Mail(){

    }
    public void envoi_mail(){
        try{
            BufferedReader lecteur = new BufferedReader(new FileReader("BaseDeDonnee/traceEmpreinte.txt"));
            String lignes;
            LocalDate date;
            while ((lignes=lecteur.readLine())!=null) {
                String[] dates = lignes.split(lignes);
                date= LocalDate.parse(dates[1]);
                if(Period.between(LocalDate.now(), date).getDays() >=30){
                    JOptionPane.showMessageDialog(null, "Message envoyer", lignes, 0);
                }
            }
        }
        catch(IOException er){

        }

    }
}
