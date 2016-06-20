package metier.classes;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Personne implements Serializable {

    private static final long serialVersionUID = 4583378700780736039L;
    /**
    * CIN
    */
    @XmlElement
    protected String cin;
    /**
    * Nom
    */
    @XmlTransient
    protected java.lang.String nom;
    /**
    * Prenom
    */
    @XmlTransient
    protected String prenom;
    /**
    * Email
    */
    @XmlTransient
    protected String email;
    /**
    * Genre
    */
    @XmlTransient
    protected Character genre;
    /**
    * Adresse
    */
    @XmlTransient
    protected String adresse;

    public Personne() {
        super();
    }

    /**
     * Constructeur
     * @param cin
     * @param nom
     * @param prenom
     * @param email
     * @param genre
     * @param adresse
     */
    public Personne(String cin, String nom, String prenom, String email, Character genre, String adresse) {
        super();
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.genre = genre;
        this.adresse = adresse;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getGenre() {
        return genre;
    }

    public void setGenre(Character genre) {
        this.genre = genre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}