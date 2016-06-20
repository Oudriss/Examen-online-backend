package metier.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import metier.interfaces.ICandidature;

/**
 * Classe Candidat
 * @author soufiane
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Candidature implements Serializable, ICandidature{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Liste des reponses selectionner par le condidat
     */
    @XmlElement
    private Map<Integer,String> reponses;
    /**
     * Validation des reponses
     * Si les reponses sont validés pour la premiere fois la variables validation sera true
     * Sinon false
     */
    private boolean validation;
    /**
     * Association entre Examen et Candidat
     */
    @XmlElement
    public Examen examen;
    /**
     * Association Utilisateur et Candidat
     */
    @XmlElement
    public Utilisateur utilisateur;


    /**
    * Constructeur par dï¿½faut
    */
    public Candidature() {
        super();
        this.reponses = new HashMap<>();
        this.validation = false;
    }

    public Map<Integer, String> getReponses() {
        return reponses;
    }

    public void setReponses(Map<Integer, String> reponses) {
            this.reponses = reponses;
    }
    /**
     * La methode pour saisir les reponses
     * @param code
     * @param reponse
     * @return
     */
    @Override
    public boolean saisirReponse(int code,String reponse) {
        return this.reponses.put(code, reponse) !=  null;
    }

    @Override
    public boolean exporterReponses() {
        //ToDo
        return false;
    }

    @Override
    public float calculerNote(){
        // ToDo
        return 0;
    }

    @Override
    public String toString() {
        return examen.toString();
    }

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

    
}