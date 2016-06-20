package presentation.converter;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metier.classes.Candidature;
import metier.classes.Examen;
import metier.classes.Question;
import presentation.model.CandidatureModel;
import presentation.model.ExamenModel;
import presentation.model.QuestionModel;

public class ConvertExamens {

	public static ObservableList<ExamenModel> getExamens(List<Examen> metierExams){
		ObservableList<ExamenModel> listExamens = FXCollections.observableArrayList();
		for( Examen e : metierExams ){
			listExamens.add(metierToModel(e));
		}
		return listExamens;
	}
	
	public static ExamenModel metierToModel(Examen examen){
		return new ExamenModel(examen.getCode(),examen.getSujet(),examen.getDate(),candidatureMetierToModel(examen.candidats),metierToModel(examen.getQuestions()));
	}
	
	public static ObservableList<CandidatureModel> candidatureMetierToModel(List<Candidature> cands){
		ObservableList<CandidatureModel> list = FXCollections.observableArrayList();
		for( Candidature cand : cands )
			list.add(candidatureMetierToModel(cand));
		return list;
	}
	
	
	private static CandidatureModel candidatureMetierToModel(Candidature cand) {
		return new CandidatureModel(FXCollections.observableHashMap(),metierToModel(cand.examen),ConvertUtilisateurs.metierToModel(cand.utilisateur));
	}

	public static ObservableList<QuestionModel> metierToModel(List<Question> qsts){
		ObservableList<QuestionModel> list = FXCollections.observableArrayList();
		for( Question qst : qsts )
			list.add(metierToModel(qst));
		return list;
	}
	
	public static QuestionModel metierToModel(Question qst){
		return new QuestionModel(qst.getQuestion(), FXCollections.observableArrayList(qst.getReponses()), qst.getVraiReponse(), qst.getNote());
	}
	
	public static Examen modelToMetier(ExamenModel exam){
		Examen examen = new Examen(exam.getCode(),exam.getSujet(),exam.getDate());
		examen.setQuestions(modelToMetier(exam.getQuestions()));
		return examen;
	}
	
	public static List<Question> modelToMetier(ObservableList<QuestionModel> qsts){
		List<Question> list = new ArrayList<Question>();
		for( QuestionModel qstModel : qsts ){
			Question qstMetier = modelToMetier(qstModel);
			qstMetier.setCode(list.size());
			list.add(qstMetier);
		}	
		return list;
	}
	
	public static Question modelToMetier(QuestionModel qst){
		return new Question(qst.getQuestion(), qst.getReponses() , qst.getVraiReponse(), qst.getNote());
	}
	
	
}
