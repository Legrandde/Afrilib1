package AfrilibClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Bibliotheque {
    public Bibliotheque(){}

    public static void ajouter_document(String titre,String auteur,int nomDePage, int nomExemplaire, String typeDocument){  
        try{
            BufferedWriter ecriture;
            if(typeDocument.equals("Livre"))
                ecriture = new BufferedWriter(new FileWriter("BaseDeDonnee/Livre.txt", true));
            else if(typeDocument.equals("Revues"))
                ecriture = new BufferedWriter(new FileWriter("BaseDeDonnee/Revues.txt", true));
            else
                ecriture = new BufferedWriter(new FileWriter("BaseDeDonnee/Articles.txt", true));
            ecriture.write(titre+" && "+auteur+" && "+nomDePage+" && "+nomExemplaire);
            JOptionPane.showMessageDialog(null, titre+" Ajouter avec succes", "Enregistrement", 1);
            ecriture.newLine();
            ecriture.close();
        }
        catch(IOException err){
            System.out.println("erreur:");
        }
    }
    public static Object[][] afficher_tous_documents(){
        ArrayList<ArrayList<Object>> listArticles = new ArrayList<>();
        String ligne;
        try{
            BufferedReader lecteur = new BufferedReader(new FileReader("BaseDeDonnee/Livre.txt"));
            while ((ligne = lecteur.readLine())!=null) {
                String[] attribu = ligne.split("&&");
                if(attribu.length >=3)
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
        return donneeLivreDispo;
    }
    public static boolean verifier_etat_document(LocalDate dateDempreint,Object statusdocument, Object etatLivre ){
        LocalDate date = (LocalDate) dateDempreint;
        long diffDate = ChronoUnit.DAYS.between(date, LocalDate.now());
        // JOptionPane.showMessageDialog(null, "Vous avez choisi de rendre  "+nomDocument+ " Son status est : "+statusdocument);
        if(statusdocument.equals("Rendu")){
            JOptionPane.showMessageDialog(null, "Ce Livre est Deja rendu !");
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Le livre est il en bon etat ?", "Verification etat livre", 1);
            if (etatLivre.equals("Bon Etat") && diffDate <30) {
                JOptionPane.showMessageDialog(null, "Document rendu en bon Etat et a temps");
                return true;
            }
            else
                return false;
        }
        
    }
    
    public static Object[][] recherche_adherant() {
        ArrayList<Object[]> listArticles = new ArrayList<>();
        String ligne;

        try (BufferedReader lecteur = new BufferedReader(new FileReader("BaseDeDonnee/adherant.txt"))) {
            while ((ligne = lecteur.readLine()) != null) {

                String[] attributs = ligne.split("&&");
                if (attributs.length ==5) {
                    listArticles.add(attributs); // Ajouter tous les adhérents
                }
                
            }
        } catch (IOException e) {
            System.out.print("Erreur : " + e.getMessage());
        }

        // Convertir la liste en tableau d'objets
        Object[][] resultats = new Object[listArticles.size()][];
        for (int i = 0; i < listArticles.size(); i++) {
            resultats[i] = listArticles.get(i);
        }

        return resultats;
    }
    @SuppressWarnings("resource")
    public static boolean virifier_disponibilite(String typeDocuemnt){
        String ligne;
        try{
            
            BufferedReader lecteur;
            if(typeDocuemnt.equals("Livre"))
                lecteur = new BufferedReader(new FileReader("BaseDeDonnee/Livre.txt"));
            else if(typeDocuemnt.equals("Revue"))
                lecteur = new BufferedReader(new FileReader("BaseDeDonnee/Revues.txt"));
            else
                lecteur = new BufferedReader(new FileReader("BaseDeDonnee/Articles.txt"));
            while ((ligne = lecteur.readLine())!=null) {
                String[] attribu = ligne.split("&&");
                int attribus = Integer.parseInt(attribu[4]);
                if (attribus < 0)
                    return true;
                else
                    return false;       
            }
            lecteur.close();
        }
        catch( IOException e){
                System.out.print("erreur "+ e.getMessage());
        }
    
        return true;
    }
    public static void enregistrer_adherant(Adherant adherant){
        if(verification_disponibilite_matricule(adherant)){
            if (nomvalid(adherant) && prenomvalid(adherant)) {
                if (mailValid(adherant)) {
                    try{
                        BufferedWriter ecriture = new BufferedWriter(new FileWriter("BaseDeDonnee/adherant.txt", true));
                        ecriture.write(adherant.matriculeAdherant+" && "+adherant.nomAdherant+" && "+adherant.prenom+" && "+adherant.addresseAdherant+" && "+adherant.compteAdherant);
                        ecriture.newLine();
                        ecriture.close();
                        JOptionPane.showMessageDialog(null, adherant.nomAdherant+" a été Ajouté !", "Ajout de livre", 1);
                    }
                    catch(IOException err){
                        JOptionPane.showMessageDialog(null, err.getMessage(), null, 0);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Email invalid", "Erreur", 0, null);
            }
            else
            JOptionPane.showMessageDialog(null, "Veuillez entre un nom et un prenom valide", "Erreur", 0, null);

        }
        
    }
    // Verification si le matricule recement ajouter n'est pas utilisé
    public static boolean verification_disponibilite_matricule(Adherant adherant) {
        boolean matriculeDisponible = true; // Initialiser à true
        BufferedReader lectureMatricule = null; // Déclaration en dehors du try
        try {
            lectureMatricule = new BufferedReader(new FileReader("BaseDeDonnee/adherant.txt"));
            String ligne;
            while ((ligne = lectureMatricule.readLine()) != null) {
                String[] matricule = ligne.split("&&");
                // Supprimer les espaces autour du matricule
                String matriculeTrimmed = matricule[0].trim();
                // Vérifier si le matricule est déjà utilisé
                if (matriculeTrimmed.equals(adherant.addresseAdherant.trim())) {
                    JOptionPane.showMessageDialog(null, "Ce matricule existe déjà !", "Matricule", JOptionPane.WARNING_MESSAGE);
                    matriculeDisponible = false; // Mettre à jour à false
                    break; // Sortir de la boucle si le matricule est trouvé
                }
            }
        } catch (IOException err) {
            matriculeDisponible = false; // En cas d'erreur, le matricule n'est pas disponible
            JOptionPane.showMessageDialog(null, err.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (lectureMatricule != null) {
                try {
                    lectureMatricule.close(); // Fermeture dans finally
                } catch (IOException e) {
                    e.printStackTrace(); // Gérer l'exception de fermeture
                }
            }
        }
        return matriculeDisponible; // Retourner la disponibilité du matricule
    }
    // Verification de l'adresse mail si oui ou non elle est valide
    public static boolean mailValid(Adherant  adherant){
        String emailRegex = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$";
        Pattern emPattern = Pattern.compile(emailRegex);
        Matcher matcher = emPattern.matcher(adherant.addresseAdherant);
        return matcher.matches();
    }
    // Verification de la validité du nom fourni pour les adherents
    public static boolean nomvalid(Adherant adherant){
        String nomRegex = "^[A-ZÀ-Ÿ][a-zà-ÿ]+(?: [A-ZÀ-Ÿ][a-zà-ÿ]+)*$";
        Pattern emPattern = Pattern.compile(nomRegex);
        Matcher matcher = emPattern.matcher(adherant.nomAdherant);
        return matcher.matches();
    }
    // Verification du prenom de l'adherent
    public static boolean prenomvalid(Adherant adherant){
        String nomRegex = "^[A-ZÀ-Ÿ][a-zà-ÿ]+(?: [A-ZÀ-Ÿ][a-zà-ÿ]+)*$";
        Pattern emPattern = Pattern.compile(nomRegex);
        Matcher matcher = emPattern.matcher(adherant.prenom);
        return matcher.matches();
    }
    public static void taxer_adherant(LocalDate date,  Object statusdocument, Object etatLivre ){
        try{
            long diffDate = ChronoUnit.DAYS.between(date, LocalDate.now());
            BufferedWriter ecris = new BufferedWriter(new FileWriter("BaseDeDonnee/taxe.txt", true));
            if(!verifier_etat_document(date, statusdocument, etatLivre)){
                ecris.write("Document en mauvais etat&&"+"5000&&20&&non payer");
                ecris.newLine();
                ecris.close();
                JOptionPane.showMessageDialog(null, "Document rendu en mauvais etat, une amande 5000 vous ait atribuer");
            }  
            else if(diffDate>=30){
                ecris.write("retard&&"+"5000&&20&&non payer");
                ecris.newLine();
                ecris.close();
                JOptionPane.showMessageDialog(null, "Document rendu en retard, une amande de 1200 vous ait attribuer");
            }
            ecris.close();
        }
        catch(IOException ex){
            System.err.println("EChec"+ex.getMessage());
        }
    }
    public void enrgistrer_date_empreint(String Matricule) throws IOException{
        try(BufferedWriter ecris = new BufferedWriter(new FileWriter("BaseDeDonnee/traceEmpreinte.txt", true))){
            LocalDate dateEpreint = LocalDate.now();
            ecris.write(Matricule+"&&"+dateEpreint);
            ecris.newLine();
            ecris.close();
        }
    }
    public void gerer_mise_ajour_doc_rendu(String matricule, String nomDocument){
        String ligne;
        StringBuilder contenu = new StringBuilder();
        boolean documentTrouve = false;

        // Lecture du fichier empreint.txt
        try (BufferedReader br = new BufferedReader(new FileReader("empreint.txt"))) {
            while ((ligne = br.readLine()) != null) {
                String[] elements = ligne.split("&&");
                // Vérification si le matricule et le nom du document correspondent
                if (elements.length >= 2 && elements[0].equals(matricule) && elements[1].equals(nomDocument)) {
                    // Remplacement par "Rien"
                    contenu.append(matricule).append("&&").append(nomDocument).append("&&").append("Rien").append("&&").append("Rien\n");
                    documentTrouve = true;
                } else {
                    // Conserver la ligne inchangée
                    contenu.append(ligne).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Écriture dans le fichier si une mise à jour a été effectuée
        if (documentTrouve) {
            try (FileWriter fw = new FileWriter("empreint.txt")) {
                fw.write(contenu.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static Boolean valid_nom(String nomAuteur){
        String nomRegex = "^[A-ZÀ-Ÿ][a-zà-ÿ]+(?: [A-ZÀ-Ÿ][a-zà-ÿ]+)*$";
        Pattern emPattern = Pattern.compile(nomRegex);
        Matcher matcher = emPattern.matcher(nomAuteur);
        return matcher.matches();
    }
    public static void enregistrer_amande_payer(String natureTaxe, String montantAPayer, String matricule) {
        String ligne;
        StringBuilder contenu = new StringBuilder();
        boolean amendeTrouvee = false;
    
        // Lecture du fichier taxe.txt
        try (BufferedReader br = new BufferedReader(new FileReader("taxe.txt"))) {
            while ((ligne = br.readLine()) != null) {
                String[] elements = ligne.split("&&");
                // Vérification si les conditions correspondent
                if (elements.length >= 4 && 
                    elements[0].equals(natureTaxe) && 
                    elements[1].equals(montantAPayer) && 
                    elements[2].equals(matricule) && 
                    elements[3].equals("non payée")) {
                    
                    // Mise à jour de la situation à "payée"
                    contenu.append(natureTaxe).append("&&")
                           .append(montantAPayer).append("&&")
                           .append(matricule).append("&&")
                           .append("payée").append("\n");
                    amendeTrouvee = true;
                } else {
                    // Conserver la ligne inchangée
                    contenu.append(ligne).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Écriture dans le fichier si une mise à jour a été effectuée
        if (amendeTrouvee) {
            try (FileWriter fw = new FileWriter("taxe.txt")) {
                fw.write(contenu.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean verification_existance_matricule(String matricule) {
        boolean exist = false; // Initialiser à false
        try (BufferedReader lecteur = new BufferedReader(new FileReader("BaseDeDonnee/adherant.txt"))) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                String[] matricules = ligne.split("&&");
                JOptionPane.showMessageDialog(null, matricules[0]+""+matricule, ligne, 0);
                if (matricules[0].equals(matricule+" ")) {
                    exist = true; // Le matricule existe
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Erreur : " + e.getMessage());
        }
        return exist; // Retourner le statut correct
    }
    
}
