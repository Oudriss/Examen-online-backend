package presentation.controller;

import java.io.IOException;

import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import presentation.converter.ConvertExamens;
import presentation.model.ExamenModel;
import presentation.model.QuestionModel;
import utils.PDFGenerator;

/**
 * Controller for CPanel UI
 * @author EL OUIZ Badr
 */
public class CPanelController {
    private ObservableList<ExamenModel> examens;

    public CPanelController() {
        this.examens = FXCollections.observableArrayList();
        this.examens = ConvertExamens.getExamens(MainApp.admin.exams.getExamens()); 
    }

    public void addExamen(ExamenModel examen) {
    	examen.setCode(examens.size()+1);
        this.examens.add(examen);
        	//MainApp.admin.exams.ajouterExamen(ConvertExamens.modelToMetier(examen));
    }

    public void modExamen(ExamenModel examen) {
        int index = this.examens.indexOf(examen);
        this.examens.remove(index);
        this.examens.add(index, examen);
    }

    public void enregistrer(ExamenModel exam) throws Exception{
    	MainApp.admin.exams.ajouterExamen(ConvertExamens.modelToMetier(exam));
    	System.out.println("============"+exam.getCandidats().size());
    	for( int i = 0 ; i < exam.getCandidats().size() ; i ++){
    		System.out.println("Candidat "+i);
    		System.out.println("CIN == "+exam.getCandidats().get(i).getUser().getCin());
    		System.out.println("CIN == "+exam.getCandidats().get(i).getExamen().getCode());
    		MainApp.admin.attribuerExamen(exam.getCandidats().get(i).getExamen().getCode(), exam.getCandidats().get(i).getUser().getCin());
    	}
    }
    
    public void removeExamen(ExamenModel examen){
        if(this.examens.remove(examen)){
        	MainApp.admin.exams.supprimerExamen(ConvertExamens.modelToMetier(examen));
        }
    }

    public ObservableList<ExamenModel> getAllExamens() {
        return this.examens;
    }

    public void addQuestion(ExamenModel selectedExamen, QuestionModel newQst) {
        int indexOf = this.examens.indexOf(selectedExamen);
        ObservableList<QuestionModel> questions = this.examens.get(indexOf).getQuestions();
        questions.add(newQst);
        this.examens.get(indexOf).setQuestions(questions);
    }

    public void removeQuestion(ExamenModel selectedExamen, QuestionModel selectedQst) {
        int indexOf = this.examens.indexOf(selectedExamen);
        ObservableList<QuestionModel> questions = this.examens.get(indexOf).getQuestions();
        questions.remove(selectedQst);
        this.examens.get(indexOf).setQuestions(questions);
    }
    
    
    public void exporterPdf(ExamenModel exam,String destination) throws Exception{
    	MainApp.admin.exams.ajouterExamen(ConvertExamens.modelToMetier(exam));
    	MainApp.admin.exporterExamen(ConvertExamens.modelToMetier(exam),destination);
    }
    
    public void gestionUtilisateur() throws IOException{
    	 MainApp.PrimaryStage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("presentation/view/UsersUI.fxml"))));
				MainApp.PrimaryStage.setTitle("Gestion des Utilisateurs");
				MainApp.PrimaryStage.centerOnScreen();
    }
 

}
