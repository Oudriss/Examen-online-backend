package presentation.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableMap;

/**
 * Candidate Model for Examen Model
 * @author EL OUIZ Badr
 */
public class CandidatureModel{

    private MapProperty<Integer, String> reponses;
    private ObjectProperty<ExamenModel> examen;
    private ObjectProperty<UtilisateurModel> user;
    private BooleanProperty validation;

    public CandidatureModel() {
        this.reponses = new SimpleMapProperty<>(this, "reponses");
        this.examen = new SimpleObjectProperty<>(this, "examen");
        this.user = new SimpleObjectProperty<>(this, "user");
        this.validation = new SimpleBooleanProperty(this,"validation");
    }

    public CandidatureModel(ObservableMap<Integer, String> rep, ExamenModel exam, UtilisateurModel user) {
        this.reponses = new SimpleMapProperty<>(this, "reponses", rep);
        this.examen = new SimpleObjectProperty<>(this, "examen", exam);
        this.user = new SimpleObjectProperty<>(this, "user", user);
    }

    public MapProperty<Integer, String> reponsesProperty() {
        return reponses;
    }

    public ObservableMap<Integer, String> getReponses() {
        return reponses.get();
    }

    public void setReponses(ObservableMap<Integer, String> value) {
        this.reponses.set(value);
    }

    public ObjectProperty<ExamenModel> examenProperty() {
        return examen;
    }

    public ExamenModel getExamen() {
        return examen.get();
    }

    public void setExamen(ExamenModel value) {
        this.examen.set(value);
    }

    public ObjectProperty<UtilisateurModel> userProperty() {
        return user;
    }

    public UtilisateurModel getUser() {
        return user.get();
    }

    public void setUser(UtilisateurModel value) {
        this.user.set(value);
    }

    
    
    public BooleanProperty getValidation() {
		return validation;
	}

	public void setValidation(BooleanProperty validation) {
		this.validation = validation;
	}

	@Override
    public String toString() {
        return "Candidat{" + "reponses=" + reponses + ", examen=" + examen + ", user=" + user + '}';
    }

}
