package presentation.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DateConverter;

/**
 * Examen Model for CPanel UI
 * @author EL OUIZ Badr
 */
public class ExamenModel {
	private IntegerProperty code;
    private StringProperty sujet;
    private ObjectProperty<Date> date;
    private ListProperty<CandidatureModel> candidats;
    private ListProperty<QuestionModel> questions;

    public ExamenModel() {
        this.code = new SimpleIntegerProperty(this, "code");
        this.sujet = new SimpleStringProperty(this, "sujet");
        this.date = new SimpleObjectProperty<>(this, "date");
        this.candidats = new SimpleListProperty<>(this, "candidats", FXCollections.observableArrayList());
        this.questions = new SimpleListProperty<>(this, "questions", FXCollections.observableArrayList());
    }

    public ExamenModel(String sujet, Date date, ObservableList<CandidatureModel> candidats, ObservableList<QuestionModel> questions) {
        this.code = new SimpleIntegerProperty(this, "code");
    	this.sujet = new SimpleStringProperty(this, "sujet", sujet);
        this.date = new SimpleObjectProperty<>(this, "date", date);
        this.candidats = new SimpleListProperty<>(this, "candidats", candidats);
        this.questions = new SimpleListProperty<>(this, "questions", questions);
    }

    public ExamenModel(int code, String sujet, Date date, ObservableList<CandidatureModel> candidats, ObservableList<QuestionModel> questions) {
        this.code = new SimpleIntegerProperty(this, "code", code);
    	this.sujet = new SimpleStringProperty(this, "sujet", sujet);
        this.date = new SimpleObjectProperty<>(this, "date", date);
        this.candidats = new SimpleListProperty<>(this, "candidats", candidats);
        this.questions = new SimpleListProperty<>(this, "questions", questions);
    }

    public StringProperty sujetProperty() {
        return sujet;
    }

    public String getSujet() {
        return sujet.get();
    }

    public void setSujet(String sujet) {
        this.sujet.set(sujet);
    }

    @SuppressWarnings("rawtypes")
	public ObjectProperty dateProperty() {
        return date;
    }

    public Date getDate() {
        return date.get();
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    @SuppressWarnings("rawtypes")
	public ListProperty candidatsProperty() {
        return candidats;
    }

    public ObservableList<CandidatureModel> getCandidats() {
        return candidats.get();
    }

    public void setCandidats(ObservableList<CandidatureModel> candidats) {
        this.candidats.set(candidats);
    }

    public ListProperty<QuestionModel> questionsProperty() {
        return questions;
    }

    public ObservableList<QuestionModel> getQuestions() {
        return questions.get();
    }

    public void setQuestions(ObservableList<QuestionModel> questions) {
        this.questions.set(questions);
    }

    public IntegerProperty codeProperty() {
        return code;
    }

    public int getCode() {
        return code.get();
    }

    public void setCode(int code) {
        this.code.set(code);
    }


    @Override
    public String toString() {
        return "["+new DateConverter().toString(DateConverter.fromDate(date.getValue()))+"]: "+sujet.getValue();
    }

}
