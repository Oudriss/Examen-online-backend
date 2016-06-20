package presentation.controller;


import java.io.IOException;

import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import metier.exceptions.UtilisateurException;
import presentation.converter.ConvertUtilisateurs;
import presentation.model.UtilisateurModel;

/**
 * 
 * @author EL OUIZ Badr
 */
public class UsersController {
	
	private ObservableList<UtilisateurModel> users;
    

    public UsersController() throws Exception {
        this.users = FXCollections.observableArrayList();
        users = ConvertUtilisateurs.metierToModel(MainApp.admin.utilisateurs.getUtilisateurs());
    }
    
    public void addUser(UtilisateurModel user) throws UtilisateurException {
        if( users.add(user) ){
        	MainApp.admin.utilisateurs.ajouterUtilisateur(ConvertUtilisateurs.modelToMetier(user));  
        }
    }
    
    public void removeUser(UtilisateurModel user) throws UtilisateurException {
       if(users.remove(user))
    	   MainApp.admin.utilisateurs.supprimerUtilisateur(ConvertUtilisateurs.modelToMetier(user));
    }
    
    
    
    public ObservableList<UtilisateurModel> getUsers() {
        return users;
    }
    
    public void enregistrer(ObservableList<UtilisateurModel> users) throws UtilisateurException{
    	for(UtilisateurModel u : users){
    		MainApp.admin.utilisateurs.modifierUtilisateur(ConvertUtilisateurs.modelToMetier(u));
    	}
    }
    
    
    public void gestionExamens() throws IOException{
    	 MainApp.PrimaryStage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader()
                 .getResource("presentation/view/CPanel.fxml"))));
         MainApp.PrimaryStage.setTitle("Gestion des examens");
         MainApp.PrimaryStage.centerOnScreen();
    }
  
    
    
}
