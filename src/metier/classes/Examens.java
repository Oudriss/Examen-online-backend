package metier.classes;

import java.util.List;

import persistence.dao.xml.CandidatureDAO;
import persistence.dao.xml.ExamenDAO;

/**
 *
 * @author soufiane
 */
public class Examens {

    /**
     * La liste des examens
     */
    protected List<Examen> examens;
    /**
     * Examen DAO
     */
    private ExamenDAO examenDAO;
    private CandidatureDAO candidatureDAO;

    /**
     * Constructeur
     * @throws Exception
     */
    public Examens() throws Exception {
        super();
        this.examenDAO = new ExamenDAO();
        this.candidatureDAO = new CandidatureDAO();
        this.examens = examenDAO.getAllObject();
        examens.stream().forEach((e) -> {
            e.candidats = candidatureDAO.getAllObjectByCode(e.getCode());
        });
    }

    /**
     * Ajouter un examen
     * @param e
     * @return
     */
    public boolean ajouterExamen(Examen e) {
        if(this.examens.add(e)){
            examenDAO.addObject(e);
            return true;
        }
        return false;
    }

    /**
     * Supprimer un examen
     * @param e
     * @return
     */
    public boolean supprimerExamen(Examen e) {
       if( this.examens.remove(e) ){
    	   return examenDAO.removeObject(e);}
       else return false;
    }

    /**
     * Modifier un examen
     * @param ancien
     * @param nouveau
     * @return
     */
    public boolean modifierExamen(Examen ancien,Examen nouveau) {
        if(this.examens.set(this.examens.indexOf(ancien), nouveau) != null)
        	return examenDAO.addObject(nouveau);
        else return false;
    }

    /**
     * Rechercher un examen en utilisant le Code
     * @param code
     * @return
     */
    public Examen getExamByCode(int code){
        for( int i = 0  ; i < examens.size() ; i++)
            if( examens.get(i).getCode() == code) return examens.get(i);
        return null;
    }

    /**
     * Afficher les examens
     */
    public void afficherExamens(){
        for(int i = 0 ; i < examens.size() ; i++)
            System.out.println( examens.get(i).toString() );
    }

    public List<Examen> getExamens() {
        return examens;
    }

    public void setExamens(List<Examen> examens) {
        this.examens = examens;
    }

}