package metier.classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * La classe Question
 * @author soufiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Question implements Serializable {

    private static final long serialVersionUID = 8073176576732791389L;
    /**
     * Code de la question
     */
    @XmlAttribute
    private int code;
    /**
     * Le contenu de la question
     */
    private String question;
    /**
     * Liste des reponses possible de la question
     */
    @XmlElement(name="reponse")
    private List<String> reponses;
    /**
     * La vrai reponse
     */
    @XmlElement
    private String vraiReponse;
    /**
     * La note affecter � cette question
     */
    @XmlAttribute
    private float note;

    /**
    * Constructeur par defaut
    */
    public Question() {
            super();
    }
    /**
     * Constructeur en utilisant les attributs
     * @param question
     * @param reponses
     * @param vraiReponse
     * @param note
     */
    public Question(String question, List<String> reponses, String vraiReponse, float note) {
        super();
        this.question = question;
        this.reponses = reponses;
        this.vraiReponse = vraiReponse;
        this.note = note;
    }

    @Override
    public String toString() {
        String s="";
        s = s + question + "\n";
        for( int i = 0 ; i < reponses.size() ; i++)
            s = s + "\t "+ i + ") " + reponses.get(i) + "\n";
        return s;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getReponses() {
        return reponses;
    }

    public void setReponses(List<String> reponses) {
        this.reponses = reponses;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getVraiReponse() {
        return vraiReponse;
    }

    public void setVraiReponse(String vraiReponse) {
        this.vraiReponse = vraiReponse;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((question == null) ? 0 : question.hashCode());
        result = prime * result + ((reponses == null) ? 0 : reponses.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)    return true;
        if (obj == null)    return false;
        if (getClass() != obj.getClass())   return false;
        final Question other = (Question) obj;
        if (!Objects.equals(this.question, other.question))
            return false;
        return Objects.equals(this.reponses, other.reponses);
    }

}