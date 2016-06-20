package presentation.controller;

import application.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import metier.classes.Administrateur;

public class LoginController {
	
	
	public boolean authentifier( String login,String password) throws Exception{
		MainApp.admin = new Administrateur();
		MainApp.admin.setLogin(login);
		MainApp.admin.setPassword(password);
		if( MainApp.admin.connecter()){
			MainApp.PrimaryStage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("presentation/view/CPanel.fxml"))));
            MainApp.PrimaryStage.setTitle("Gestion des examens");
            MainApp.PrimaryStage.centerOnScreen();
            return true;
		}else return false;
	}

}
