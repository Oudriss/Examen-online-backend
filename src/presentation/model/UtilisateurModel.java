package presentation.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * User for Users UI TableView
 * @author EL OUIZ Badr
 */
public class UtilisateurModel {
    
    private SimpleStringProperty cin, nom, prenom, email, adresse, username, password;
    private SimpleIntegerProperty id;

    public UtilisateurModel(int id,String cin, String nom, String prenom, String email, String adresse, String username, String password) {
        this.cin = new SimpleStringProperty(cin);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.adresse = new SimpleStringProperty(adresse);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.id = new SimpleIntegerProperty(id);
    }
    
    public UtilisateurModel(String cin, String nom, String prenom, String email, String adresse, String username, String password) {
        this.cin = new SimpleStringProperty(cin);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.adresse = new SimpleStringProperty(adresse);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.id = new SimpleIntegerProperty();
    }


    public String getCin() {
        return cin.get();
    }

    public void setCin(String cin) {
        this.cin.set(cin);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
    

    public int getId() {
		return id.getValue();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	@Override
    public String toString() {
        return "[" + cin.get() + "] : " + nom.get() + " " + prenom.get();
    }

}
