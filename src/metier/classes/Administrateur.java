package metier.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import metier.exceptions.AdminstrateurException;
import metier.interfaces.IAdministrateur;
import persistence.dao.mysql.AdministrateurDAO;
import persistence.dao.xml.CandidatureDAO;
import persistence.dao.xml.ExamenDAO;
import utils.PDFGenerator;
import metier.interfaces.IAuthentification;

/**
 * Classe Administrateur
 * @author soufiane
 *
 */
public class Administrateur extends Personne implements IAuthentification,IAdministrateur{

    private static final long serialVersionUID = 1L;
    /**
     * Identifiant de l'Admin
     */
    private int id;
    /**
     * Si l'Admin est connecte ou pas
     */
    private boolean connecte;
    /**
     * Login de l'Admin
     */
    private String login;
    /**
     * Mot de Passe
     */
    private String password;
    /**
     * Les utilisateurs
     */
    public Utilisateurs utilisateurs;
    /**
     * Les examens
     */
    public Examens exams;
    /**
     * DAO Administrateur
     */
    private AdministrateurDAO adminDAO;
    /**
     * DAO examen
     */
    private ExamenDAO examenDAO;
    /**
     * Candidature DAO
     */
    private CandidatureDAO candidatureDAO;

    /**
	 * Constructeur par d�faut
	 * @throws Exception
	 */
    public Administrateur() throws Exception {
        super();
        adminDAO = new AdministrateurDAO();
        examenDAO = new ExamenDAO();
        candidatureDAO = new CandidatureDAO();

    }

    /**
	 * Ajouter un nouveau examen de type QCM
	 * @param sujet
	 * @param date
	 * @param questions
	 * @return true si l'examen est bien saisi, sinon false
     * @throws java.lang.Exception
	 */
    @Override
    public boolean saisirExamen(String sujet, Date date,ArrayList<Question> questions) throws Exception {
        if( this.connecte != true ) throw new AdminstrateurException();
        Examen exam = new Examen(exams.examens.size(),sujet, date);
        exam.setQuestions(questions);
 
        if(this.exams.ajouterExamen(exam))
        	return  examenDAO.addObject(exam);
        else return false;
    }

    /**
     * Attribuer un examen � un utilisateur
     * @param code
     * @param cin
     * @return true si l'examen est attribu� � l'utilisateur, sinon false
      * @throws java.lang.Exception
     */
    @Override
    public boolean attribuerExamen(int code,String cin) throws Exception{
        if( this.connecte != true ) throw new AdminstrateurException();
        Candidature c = new Candidature();
        c.utilisateur =  utilisateurs.getByCIN(cin);
        if( c.utilisateur != null){
            c.examen = exams.getExamByCode(code);
            this.utilisateurs.getByCIN(c.utilisateur.getCin()).candidats.add(c);
            this.exams.getExamByCode(code).candidats.add(c);
            candidatureDAO.addObject(c);
            return true;
        }else return false;
    }

    /**
     * Verficiation  si l'admin est dans la base de donn�es
      * @return
      * @throws java.lang.Exception
     */
    @Override
    public boolean connecter() throws Exception{
        if(adminDAO.isExisted(this) == true){
            utilisateurs = new Utilisateurs();
            exams = new Examens();
            for( int i = 0 ; i < exams.examens.size() ; i++){
                List<Candidature> cands = exams.examens.get(i).candidats;
                cands.stream().forEach((cand) -> {
                    cand.utilisateur = this.utilisateurs.getByCIN(cand.utilisateur.getCin());
                });
            }
            this.connecte = true;
        } else this.connecte = false;
        return this.connecte;
    }

    /**
     * Deconnexion
     * @return
     */
    @Override
    public boolean deconnecter(){
        if(this.connecte == true) connecte = false;
        return !connecte;
    }
    /**
     * Exporter l'Examen en PDF
     * @throws Exception 
     */
    @Override
    public void exporterExamen(Examen examen,String destination) throws Exception{
    	PDFGenerator.xmlToPdf(examen, destination);
    }

    public boolean isConnecte() {
        return connecte;
    }

    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}