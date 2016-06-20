package test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import metier.classes.Administrateur;
import metier.classes.Candidature;
import metier.classes.Question;
import metier.classes.Utilisateur;
import metier.exceptions.UtilisateurException;

public class Main {
	
	/**
	 * Instance d'adminstrateur de la couche métier qui va nous permet d'accèder aux services de la couche
	 * Soit les services de gestion des exams
	 * Soit les services de gestion des examens
	 * 
	 */
	public static Administrateur admin;
	/**
	 * Scanner pour lire l'entrée clavier
	 */
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * Menu principal
	 * @return le choix d'utilisateur
	 */
	public static int menu(){
		System.out.println("========================== Gestion des examens en lignes =================================");
		System.out.println("\t 1) Gestion des utilisateurs ");
		System.out.println("\t 2) Gestion des examens");
		System.out.println("\t 0) QUITTER");
		return sc.nextInt();
	}
	
	
	
	
	
	
	
	/*
	 * Gestion des utilisateurs
	 */
	
	/**
	 * Menu pour gerer les utilisateurs
	 * @return le choix d'utilisateur
	 */
	public static int gestionUtilisateurs(){
		System.out.println("========================== Gestion des utilisateurs ======================================");
		System.out.println("\t\t 1) Afficher les utilisateurs ");
		System.out.println("\t\t 2) Rechercher par CIN");
		System.out.println("\t\t 3) Ajouter un utilisateur");
		System.out.println("\t\t 4) Modifier un utilisateur");
		System.out.println("\t\t 5) Supprimer un utilisateur");
		System.out.println("\t\t 0) QUITTER");
		return sc.nextInt();
	}
	/**
	 * Ajouter un utilisateur
	 * @return true si l'utilisateur est ajouté , sinon false
	 * @throws UtilisateurException 
	 */
	public static boolean ajouterUtilisateur() throws UtilisateurException{
		System.out.println("\t\t ++ Utilisateur(id, cin, nom, prenom, email, adresse)");
		Utilisateur u = new Utilisateur(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next(),'H', sc.next());
		return admin.utilisateurs.ajouterUtilisateur(u);	
	}
	/**
	 * Supprimer un utilisateur
	 * @return true si l'utilisateur est supprimé , sinon false
	 */
	public static boolean suuprimerUtilisateur(){
		System.out.println("\t\t ++ Entrer le cin d'utilisateur : ");
		return admin.utilisateurs.supprimerUtilisateur(admin.utilisateurs.getByCIN(sc.next()));
	}
	/**
	 * Modifier un utilisateur
	 * @return true si l'utilisateur est modifié , sinon false
	 * @throws UtilisateurException 
	 */
	public static boolean modifierUtilisateur() throws UtilisateurException{
		System.out.println("\t\t ++ Utilisateur(id, cin, nom, prenom, email, adresse)");
		Utilisateur u2 = new Utilisateur(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next(),'H', sc.next());
		return admin.utilisateurs.modifierUtilisateur(u2);
	}
	/**
	 * Afficher les utilisateur
	 */
	public static void afficherUtilisateurs(){
		System.out.println(admin.utilisateurs.toString());
	}
	/**
	 * Rechercher un utilisateur par CIN
	 */
	public static void rechercherUtilisateur(){
		System.out.println("\t\t ++ Entrer CIN : ");
		System.out.println(admin.utilisateurs.getByCIN(sc.next()).toString());
	}
	/**
	 * Appel au menu de la gestion des utilisateurs
	 * @throws UtilisateurException 
	 */
	public static void appelGestionUtilisateur() throws UtilisateurException{
		int c =  0;
		do{
			c = gestionUtilisateurs();
			switch (c) {
			case 1: afficherUtilisateurs(); break;
			case 2: rechercherUtilisateur(); break;
			case 3: ajouterUtilisateur(); break;
			case 4: modifierUtilisateur(); break;
			case 5: suuprimerUtilisateur(); break;
			case 0: System.out.println("Merci ^^");
			default:System.out.println("Choix invalid !! ");break;
		}	
		}while(c != 0);
		
	}
	
	
	
	
	
	
	
	/*
	 * Gestion des examens
	 */
	
	/**
	 * Menu pour gestion des examen
	 * @return
	 */
	public static int gestionExamens(){
		System.out.println("========================== Gestion des utilisateurs ======================================");
		System.out.println("\t\t 1) Afficher les examens ");
		System.out.println("\t\t 2) Rechercher par CODE");
		System.out.println("\t\t 3) Ajouter un examen");
		System.out.println("\t\t 4) Modifier un examen");
		System.out.println("\t\t 5) Supprimer un examen");
		System.out.println("\t\t 6) Attribuer un examen à un utilisateur");
		System.out.println("\t\t 7) Afficher un examen et les utilisateurs l'affectés");
		System.out.println("\t\t 8) Afficher un utilisateur et les examens lui affecté");
		System.out.println("\t\t 0) QUITTER");
		return sc.nextInt();
	}
	/**
	 * Afficher les examens
	 */
	public static void afficherExamens(){
		admin.exams.afficherExamens();
	}
	/**
	 * Rechercher examen
	 */
	public static void rechercherExam(){
		System.out.print("\n\t\t ++ Entrer le code d'examen : ");
		System.out.println(admin.exams.getExamByCode(sc.nextInt()).toString());
	}
	/**
	 * Creer une question
	 * @return
	 */
	public static Question creerQuestion(){
		Question question = new Question();
		System.out.print("\n\t\t ++ Entrer la question : ");
		question.setQuestion(sc.next());
		System.out.print("\n\t\t ++ Entrer le nombre des reponses : ");
		int n = sc.nextInt();
		ArrayList<String> reponses = new ArrayList<String>();
		for( int i = 0 ; i < n ; i++){
			System.out.print("\n\t\t ++ Entrer reponse "+i+" : ");
			reponses.add(sc.next());
		}
		question.setReponses(reponses);
		System.out.print("\n\t\t ++ Entrer le numero de la vrai reponse : ");
		question.setVraiReponse(reponses.get(sc.nextInt()));
		System.out.print("\n\t\t ++ Entrer la note de la question : ");
		question.setNote(sc.nextFloat());
		question.setCode(question.hashCode());
		return question;
	}
	/**
	 * Creer des question pour les affecter un examen
	 * @return
	 */
	public static ArrayList<Question> creerQuestions(){
		ArrayList<Question> questions = new ArrayList<Question>();
		System.out.print("\n\t\t ++ Entrer le nombre des questions : ");
		int n = sc.nextInt();
		for( int i = 0 ; i < n ; i++ ){
			questions.add(creerQuestion());
		}
		return questions;
	} 
	/**
	 * Ajouter un examen
	 * @return
	 */
	public static boolean ajouterExamen(){
		System.out.print("\n\t\t ++ Entrer le sujet d'examen : ");
		try {
			return admin.saisirExamen(sc.next(), new Date(), creerQuestions());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Supprimer un examen
	 * @return
	 */
	public static boolean supprimerExamen(){
		System.out.print("\n\t\t ++ Entrer le code d'examen à supprimer : ");
		return admin.exams.supprimerExamen(admin.exams.getExamByCode(sc.nextInt()));
	}
	/**
	 * Attribuer Examen à un utilisateur
	 * @return
	 */
	public static boolean attribuerExamen(){
		System.out.println("\t\t ++ Entrer Code d'examen : ");
		System.out.println("\t\t ++ Entrer CIN d'utilisateur : ");
		try {
			return admin.attribuerExamen(sc.nextInt(), sc.next());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Afficher un utilisateur et les examens lui affectés
	 */
	public static void afficherUtilisateurExamens(){
		System.out.println("\t\t Entrer CIN d'utilisateur : ");
		List<Candidature> cs = admin.utilisateurs.getByCIN(sc.next()).candidats;
		for( int i = 0 ; i < cs.size() ; i++ )
			System.out.println(cs.get(i).examen.toString());
	}
	/**
	 * Modifier une examen
	 * @return
	 */
	public static boolean modifierExamen(){
		return false;
	}
	
	/**
	 * Appel de menu de gestion des examens
	 */
	public static void appelGestionExamens(){
		int c =  0;
		do{
			c = gestionExamens();
			switch (c) {
			case 1: afficherExamens();break;
			case 2: rechercherExam();break;
			case 3: ajouterExamen(); break;
			case 4:                  break;
			case 5: supprimerExamen(); break;
			case 6: attribuerExamen();break;
			case 8: afficherUtilisateurExamens();break;
			case 0: System.out.println("Merci ^^");
			default:System.out.println("Choix invalid !! ");break;
		}	
		}while(c != 0);
		
	}
	
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		try {
			admin = new Administrateur();
			admin.setLogin("admin");
			admin.setPassword("admin");
			
			int c = 0;
			do{
				c = menu();
				switch (c) {
				case 1: appelGestionUtilisateur(); break;
				case 2: appelGestionExamens(); break;
				case 0: System.out.println("Merci ^^"); break;
				default: System.out.println("Choix invalide !! "); break;
				}
			}while( c != 0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		Administrateur admin = new Administrateur();
		admin.setLogin("admin");
		admin.setPassword("admin");
		try {
			System.out.println(admin.connecter());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		/*
		Utilisateur u1 = new Utilisateur(1, "u1", "user1", "user1", "user1@user1", 'F', "user1");
		u1.setLogin("user1");
		u1.setPassword("user1");
		Utilisateur u2 = new Utilisateur(2, "u2", "user2", "user2", "user1@user1", 'F', "user1");
		u2.setLogin("user2");
		u2.setPassword("user2");
		Utilisateur u3 = new Utilisateur(3, "u3", "user3", "user3", "user1@user1", 'F', "user1");
		u3.setLogin("user3");
		u3.setPassword("user3");
		Utilisateur u4 = new Utilisateur(4, "u4", "user4", "user4", "user1@user1", 'F', "user1");
		u4.setLogin("user4");
		u4.setPassword("user4");
		
		
		
		Administrateur admin = new Administrateur(1, "VA121", "Admin", "Admin", "email@email", 'H', "Adresse AV ...");
		admin.setLogin("soufiane");
		admin.setPassword("soufiane");
		System.out.println(admin.connecter("soufiane","soufiane"));
		
		
		admin.utilisateurs.ajouterUtilisateur(u1);
		admin.utilisateurs.ajouterUtilisateur(u2);
		admin.utilisateurs.ajouterUtilisateur(u3);
		admin.utilisateurs.ajouterUtilisateur(u4);
		ArrayList<String> reponses1 = new ArrayList<String>();
		reponses1.add("reponse 1");
		reponses1.add("reponse 2");
		reponses1.add("reponse 3");
		Question q1 = new Question("Question 1 ?",reponses1 ,"reponse1", 2);
		ArrayList<String> reponses2 = new ArrayList<String>();
		reponses2.add("reponse 1");
		reponses2.add("reponse 2");
		reponses2.add("reponse 3");
		Question q2 = new Question("Question 1 ?",reponses2 ,"reponse1", 2);
		
		
		ArrayList<Question> questions = new ArrayList<Question>();
		questions.add(q1);questions.add(q2);
		admin.saisirExamen("test 1", new Date(), questions);
		admin.attribuerExamen(0, "u1");
		admin.attribuerExamen(0, "u2");
		admin.utilisateurs.afficherUtilisateurs();
		*/

	
		
	}
	
	

}
