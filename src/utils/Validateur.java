package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validateur {

    public static Pattern pattern ;
    public static Matcher controler ;
    
    /**
     * Renvoi true si l'email est valide, sinon flase
     * @param email
     * @return
     */
    public static boolean validerEmail(String email){
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        pattern = Pattern.compile(masque);
        controler = pattern.matcher(email);
        return true;
    }
    /**
     * Renvoi true si CIN est valide, sinon false
     * @param cin
     * @return
     */
    public static boolean validerCin(String cin){
        String masque = "([A-Z]{1,2})[:digits:]{1,6}";
        pattern = Pattern.compile(masque);
        controler = pattern.matcher(cin);
        return true;
    }

    /**
     * Renvoi true si le nom/prenom est valide, sinon false
     * @param nom
     * @return
     */
    public static boolean validerNomPrenom(String nom){
        String masque = "[A-Z]{1,}";
        pattern = Pattern.compile(masque);
        controler = pattern.matcher(nom);
        return true;
    }
}
