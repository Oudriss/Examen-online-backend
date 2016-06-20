package presentation.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 * Question Model for Examen Model
 * @author EL OUIZ Badr
 */
public class QuestionModel {

    private SimpleStringProperty question;
    private ListProperty<String> reponses;
    private SimpleStringProperty vraiReponse;
    private SimpleFloatProperty note;

    public QuestionModel() {
        this.question = new SimpleStringProperty(this, "question");
        this.reponses = new SimpleListProperty<>(this, "reponses");
        this.vraiReponse = new SimpleStringProperty(this, "vraiReponse");
        this.note = new SimpleFloatProperty(this, "note");
    }

    public QuestionModel(String qst, ObservableList<String> rep, String vrai, Float note) {
        this.question = new SimpleStringProperty(this, "question", qst);
        this.reponses = new SimpleListProperty<>(this, "reponses", rep);
        this.vraiReponse = new SimpleStringProperty(this, "vraiReponse", vrai);
        this.note = new SimpleFloatProperty(this, "note", note);
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public ListProperty<String> reponsesProperty() {
        return reponses;
    }

    public ObservableList<String> getReponses() {
        return reponses.get();
    }

    public void setReponses(ObservableList<String> reponses) {
        this.reponses.set(reponses);
    }

    public SimpleStringProperty vraiReponseProperty() {
        return vraiReponse;
    }

    public String getVraiReponse() {
        return vraiReponse.get();
    }

    public void setVraiReponse(String vraiReponse) {
        this.vraiReponse.set(vraiReponse);
    }

    public SimpleFloatProperty noteProperty() {
        return note;
    }

    public Float getNote() {
        return note.get();
    }

    public void setNote(Float note) {
        this.note.set(note);
    }


    @Override
	public String toString() {
		String s="";

		s = s + question.getValue() + "\n";
		for( int i = 0 ; i < reponses.getSize() ; i++)
			s = s + "\t "+ (i+1) + ") " + reponses.get(i).toString() + "\n";
		s = s + "Note : " + note.getValue() + "\n";
		s = s + "Vraie Reponse : "+vraiReponse.getValue() + "\n";
		return s;
	}

}
