package metier.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import metier.interfaces.IExamen;

/**
 * La classe Examen
 * @author soufiane
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Examen implements Serializable,IExamen{

  
	private static final long serialVersionUID = 5027919204853647846L;
    /**
     * Code d'examen
     */
    @XmlAttribute
    protected int code;
    /**
    * Le sujet d'examen
    */
    @XmlElement(name="sujet")
    protected String sujet;
    /**
     * La date d'examen
     */
    protected Date date;
    /**
     * La liste des questions d'examen
     */
    @XmlElement(name="questions")
    private List<Question> questions;
    /**
     * La duree d'examen
     */
    private long duree;
    /**
     * La listes des candidatures
     */
    @XmlTransient
    public List<Candidature> candidats;

    /**
     * Constructeur par d�faut
     */
    public Examen() {
        super();
        this.questions = new ArrayList<>();
        this.candidats = new ArrayList<>();
    }
    /**
     * Constructeur en utilisant les attributs sujet et date
     * @param code
     * @param sujet
     * @param date
     */
    public Examen(int code,String sujet, Date date) {
        this.code = code;
        this.sujet = sujet;
        this.date = date;
        this.questions = new ArrayList<>();
        this.candidats = new ArrayList<Candidature>();
    }
    /**
     * La methode qui ajoute une question
     * @param question
     * @return
     */
    @Override
    public boolean ajouterQuestion(Question question) {
        return this.questions.add(question);
    }

    /**
     * La methode qui supprime une question
     * @param question
     * @return
     */
    @Override
    public boolean supprimerQuestion(Question question) {
        return this.questions.remove(question);
    }

    /**
     * La methode qui modifie une question
     * @param nouveau
     * @param ancien
     * @return
     */
    @Override
    public boolean modifierQuestion(Question nouveau,Question ancien) {
        return this.questions.set(questions.indexOf(ancien), nouveau) != null;
    }
    
  	@Override
  	public boolean equals(Object obj) {
  		if (this == obj)
  			return true;
  		if (obj == null)
  			return false;
  		if (getClass() != obj.getClass())
  			return false;
  		Examen other = (Examen) obj;
  		if (code != other.code)
  			return false;
  		return true;
  	}
  	
    @Override
    public String toString() {
        String s = "";
        s = s + "++" + sujet + "++\n";
        s = s + date.toString() + "\n";
        for( int i = 0 ; i < questions.size() ; i++ )
            s = s + questions.get(i).toString() + "\n";
        return s;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
	public long getDuree() {
		return duree;
	}
	public void setDuree(long duree) {
		this.duree = duree;
	}
    
    

}