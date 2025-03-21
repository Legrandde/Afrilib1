package AfrilibClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Adherant {
    static int matricule = 1;
    public String matriculeAdherant;
    public String nomAdherant;
    public String prenom;
    public String compteAdherant;
    public String addresseAdherant;
    public String dureeApprentie;
    public double amandeAdherant;

    public Adherant(String matricule, String nom, String prenom, String adresse, String compte) {
        this.matriculeAdherant = matricule;
        this.nomAdherant = nom;
        this.prenom = prenom;
        this.compteAdherant = compte;
        this.addresseAdherant = adresse;
        this.amandeAdherant = 0;
        Adherant.matricule++;
    }
    public static String getMatricule(){
        String matricules = ""+matricule;
        return  matricules;
    }

    public static void empreiter(Object nomLivreSelectionnee) {
        int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous emprunter " + nomLivreSelectionnee, "Confirmation", JOptionPane.YES_NO_OPTION);
    
        if (choix == JOptionPane.YES_OPTION) {
            String matricule = (String) JOptionPane.showInputDialog("Entrer votre matricule");
            String livreSelectionne = nomLivreSelectionnee.toString();
    
            if (Bibliotheque.verification_existance_matricule(matricule)) {
                try (BufferedReader lecteur = new BufferedReader(new FileReader("BaseDeDonnee/emprunt.txt"))) {
                    String lignes;
                    boolean matriculeTrouver = false;
                    StringBuilder contenuFichier = new StringBuilder();
    
                    while ((lignes = lecteur.readLine()) != null) {
                        String[] matricules = lignes.split("&&");
                        if (matricules[0].equals(matricule)) {
                            matriculeTrouver = true;
                            boolean peutEmprunter = false; // Indique s'il y a de la place pour un nouvel emprunt
    
                            // Vérifier les emprunts existants et trouver un "Rien"
                            for (int i = 1; i < matricules.length; i++) {
                                if (matricules[i].equals("Rien")) {
                                    peutEmprunter = true; // Il y a de la place pour un nouvel emprunt
                                    matricules[i] = livreSelectionne; // Remplacer "Rien" par le titre du livre
                                    JOptionPane.showMessageDialog(null, "L'emprunt de \"" + livreSelectionne + "\" a été effectué avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                                    break; // Sortir de la boucle après l'emprunt
                                }
                            }
    
                            // Si pas de place pour un nouvel emprunt
                            if (!peutEmprunter) {
                                JOptionPane.showMessageDialog(null, "Vous ne pouvez emprunter que 3 documents à la fois.", "Alerte", JOptionPane.WARNING_MESSAGE);
                                return; // Sortir de la méthode si le livre ne peut pas être emprunté
                            }
    
                            contenuFichier.append(String.join("&&", matricules)).append("\n");
                        } else {
                            contenuFichier.append(lignes).append("\n");
                        }
                    }
    
                    // Si le matricule n'est pas trouvé, ajouter une nouvelle entrée
                    if (!matriculeTrouver) {
                        contenuFichier.append(matricule).append("&&").append(livreSelectionne).append("&&Rien&&Rien\n");
                        JOptionPane.showMessageDialog(null, "L'emprunt de \"" + livreSelectionne + "\" a été effectué avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }
    
                    // Écrire le contenu mis à jour dans le fichier
                    try (BufferedWriter écrivain = new BufferedWriter(new FileWriter("BaseDeDonnee/emprunt.txt"))) {
                        écrivain.write(contenuFichier.toString());
                    }
    
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ce matricule n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static Object[][] afficherEmprunt() {
        ArrayList<ArrayList<Object>> listArticles = new ArrayList<>();
        String ligne;
    
        try (BufferedReader lecteur = new BufferedReader(new FileReader("BaseDeDonnee/emprunt.txt"))) {
            while ((ligne = lecteur.readLine()) != null) {
                String[] attribu = ligne.split("&&");
                if (attribu.length > 0) {
                    String matricule = attribu[0]; // Récupérer le matricule
    
                    // Ajouter le matricule et les livres empruntés à la liste
                    for (int j = 1; j < attribu.length; j++) {
                        if (!attribu[j].equals("Rien")) { // Vérifier que le livre n'est pas "Rien"
                            listArticles.add(new ArrayList<>(Arrays.asList(matricule, attribu[j])));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.print("Erreur : " + e.getMessage());
        }
    
        // Convertir la liste en tableau d'objets
        Object[][] donneeLivreDispo = new Object[listArticles.size()][];
        for (int i = 0; i < listArticles.size(); i++) {
            donneeLivreDispo[i] = listArticles.get(i).toArray();
        }
    
        return donneeLivreDispo;
    }
    public static void rendreDocument(String nomLivreSelectionne, String matriculeAdherant) {
        String matricule = matriculeAdherant;
    
        try (BufferedReader litRenue = new BufferedReader(new FileReader("BaseDeDonnee/emprunt.txt"))) {
            String ligne;
            StringBuilder nouvelleLigne = new StringBuilder();
            boolean livreTrouve = false;
    
            // Lecture du fichier et modification des données
            while ((ligne = litRenue.readLine()) != null) {
                String[] verif = ligne.split("&&");
                if (verif[0].equals(matricule)) {
                    for (int j = 1; j < verif.length; j++) {
                        if (verif[j].equals(nomLivreSelectionne)) {
                            verif[j] = "Rien"; // Remplacer le livre rendu par "Rien"
                            livreTrouve = true;
                            break;
                        }
                    }
                }
                nouvelleLigne.append(String.join("&&", verif)).append("\n");
            }
    
            // Écriture dans le fichier si le livre a été trouvé et rendu
            if (livreTrouve) {
                try (BufferedWriter ecrir = new BufferedWriter(new FileWriter("BaseDeDonnee/emprunt.txt"))) {
                    ecrir.write(nouvelleLigne.toString());
                    JOptionPane.showMessageDialog(null, "Retour du livre effectué avec succès");
    
                    // Enregistrement de la date de retour
                    try (BufferedWriter ecritdateRetour = new BufferedWriter(new FileWriter("BaseDeDonnee/traceRetour.txt", true))) {
                        ecritdateRetour.write(matricule + "&&" + LocalDate.now() + "&&" + nomLivreSelectionne);
                        ecritdateRetour.newLine();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Livre non trouvé dans vos emprunts");
            }
    
        } catch (IOException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
    public static void payerTaxe(String natureTaxe, String montantAPayer, String matricule) {
        // Lire le fichier taxe.txt
        StringBuilder contenuTaxe = new StringBuilder();
        boolean taxeTrouvee = false;
    
        try (BufferedReader lecteurTaxe = new BufferedReader(new FileReader("BaseDeDonnee/taxe.txt"))) {
            String ligne;
            while ((ligne = lecteurTaxe.readLine()) != null) {
                String[] attributs = ligne.split("&&");
                // Vérifier si la taxe correspond à celle à payer
                if (attributs[0].equalsIgnoreCase(natureTaxe) && attributs[2].equals(matricule)) {
                    // Mettre à jour la situation à "payée"
                    attributs[3] = "payée";
                    taxeTrouvee = true;
                }
                contenuTaxe.append(String.join("&&", attributs)).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier : " + e.getMessage());
            return;
        }
    
        // Si la taxe a été trouvée, l'écrire dans le fichier taxe.txt
        if (taxeTrouvee) {
            try (BufferedWriter ecrireTaxe = new BufferedWriter(new FileWriter("BaseDeDonnee/taxe.txt"))) {
                ecrireTaxe.write(contenuTaxe.toString());
                JOptionPane.showMessageDialog(null, "La taxe a été mise à jour avec succès.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'écriture dans le fichier : " + e.getMessage());
            }
        }else {
            JOptionPane.showMessageDialog(null, "La taxe est deja régler");
        }
    }
    
    public static  void reserverDocument() {
        // Demander à l'utilisateur le matricule
    String matricule = JOptionPane.showInputDialog("Entrez votre matricule :");

    // Demander à l'utilisateur le nom du document
    String nomDocument = JOptionPane.showInputDialog("Entrez le nom du document à réserver :");

    // Demander à l'utilisateur la date de début de réservation
    String dateDebutStr = JOptionPane.showInputDialog("Entrez la date de début de réservation (format: YYYY-MM-DD) :");
    
    LocalDate dateDebut;
    try {
        dateDebut = LocalDate.parse(dateDebutStr, DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(null, "Format de date invalide. Veuillez utiliser le format YYYY-MM-DD.");
        return;
    }

    // Calculer la date de fin de réservation
    LocalDate dateFin = dateDebut.plusDays(3);
    
    // Créer la ligne de réservation
    String reservation = matricule + "&&" + nomDocument + "&&" + dateDebut + "&&" + dateFin;

    // Définir le chemin du fichier reservation.txt dans le dossier BaseDeDonnee
    String cheminFichier = Paths.get("BaseDeDonnee", "reservation.txt").toString();

    // Écrire dans le fichier reservation.txt
    try (FileWriter fw = new FileWriter(cheminFichier, true)) {
        fw.write(reservation + "\n");
        JOptionPane.showMessageDialog(null, "Réservation effectuée avec succès !\nDate de fin : " + dateFin);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement de la réservation : " + e.getMessage());
    }
    }
    
        

    public static Object[][] afficherTaxe() {
        ArrayList<Object[]> listTaxe = new ArrayList<>();
        String ligne;
    
        try (BufferedReader lecteur = new BufferedReader(new FileReader("BaseDeDonnee/taxe.txt"))) {
            while ((ligne = lecteur.readLine()) != null) {
                String[] attributs = ligne.split("&&");
                if (attributs.length == 4) { // Vérifier que toutes les informations sont présentes
                    listTaxe.add(attributs);
                }
            }
        } catch (IOException e) {
            System.out.print("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    
        // Convertir la liste en tableau d'objets
        Object[][] donneeTaxe = new Object[listTaxe.size()][];
        for (int i = 0; i < listTaxe.size(); i++) {
            donneeTaxe[i] = listTaxe.get(i);
        }
    
        return donneeTaxe;
    }
    public static void tracer_empreint(String matricule){
        try{
            BufferedWriter ecritTrace = new BufferedWriter(new FileWriter("BaseDeDonnee/traceEmpreinte.txt"));
            ecritTrace.write(matricule+"&&"+LocalDate.now());
            ecritTrace.close();
        }
        catch(IOException err){
            JOptionPane.showMessageDialog(null, err.getMessage(), matricule, 0);
        }
    }
}
