/*
 * JAVA Project
 */
package presentation.converter;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.classes.Utilisateur;
import metier.exceptions.UtilisateurException;
import presentation.model.UtilisateurModel;

/**
 * Classe qui implemente le Façade Design Pattern
 * @author EL OUIZ Badr
 */
public class ConvertUtilisateurs {
 
    public static ObservableList<UtilisateurModel> metierToModel( List<Utilisateur> metierUsers ){
    	ObservableList<UtilisateurModel> modelUsers = FXCollections.observableArrayList();
    	for( Utilisateur u:metierUsers){
    		modelUsers.add(metierToModel(u));
    	}
    	return modelUsers;
    }
    /**
     * Convertir un Utilisateur du Metier vers un Utilisateur du Model
     * @param utilisateur Utilisateur du Metier
     * @return Utilisateur du Model
     */
    public static UtilisateurModel metierToModel(Utilisateur utilisateur) {
        return new UtilisateurModel(utilisateur.getId(),utilisateur.getCin(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getAdresse(), utilisateur.getLogin(), utilisateur.getPassword());
    }
    /**
     * Convertir un Utilisateur du Model vers un Utilisateur du Metier
     * @param user Utilisateur du Model
     * @return Utilisateur du Metier
     * @throws UtilisateurException 
     */
    public static Utilisateur modelToMetier(UtilisateurModel user) throws UtilisateurException {
        return new Utilisateur(user.getId(), user.getCin(), user.getNom(), user.getPrenom(), user.getEmail(), 'C', user.getAdresse(), user.getUsername() , user.getPassword());
    }

}
