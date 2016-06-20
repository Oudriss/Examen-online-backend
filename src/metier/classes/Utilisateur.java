package metier.classes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import metier.exceptions.UtilisateurException;
import utils.Validateur;

/**
 * La class utilisateur
 * @author soufiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Utilisateur extends Personne{

    private static final long serialVersionUID = 1L;
    /**
     * L'identifiant d'utilisateur
     */
    @XmlTransient
    private int id;
    /**
     * L'association entre classe Utilisateur et Candidat
     */
    @XmlTransient
    public List<Candidature> candidats;
    /**
     * Login
     */
    @XmlTransient
    private String login;
    /**
     * Password
     */
    @XmlTransient
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    /**
    * Constructeur par defaut
    */
    public Utilisateur() {
        super();
        this.candidats = new ArrayList<>();
    }
    /**
     * Constructeur en utlisant les attributs
     * @param id
     * @param cin
     * @param nom
     * @param prenom
     * @param email
     * @param genre
     * @param adresse
     * @throws UtilisateurException
     */
    public Utilisateur(int id, String cin, String nom, String prenom, String email, Character genre, String adresse) throws UtilisateurException {
        super(cin,nom,prenom,email,genre,adresse);
        if(!Validateur.validerCin(cin) && !Validateur.validerEmail(email)
                && !Validateur.validerNomPrenom(prenom)
                && !Validateur.validerNomPrenom(nom) ) throw new UtilisateurException();
        this.id = id;
        this.candidats = new ArrayList<>();
    }
    /**
     * Constructeur
     * @param id
     * @param cin
     * @param nom
     * @param prenom
     * @param email
     * @param genre
     * @param adresse
     * @param login
     * @param password
     * @throws UtilisateurException
     */
    public Utilisateur(int id, String cin, String nom, String prenom, String email, Character genre, String adresse,String login,String password) throws UtilisateurException {
        super(cin,nom,prenom,email,genre,adresse);
        if(!Validateur.validerCin(cin) && !Validateur.validerEmail(email)
                && !Validateur.validerNomPrenom(prenom)
                && !Validateur.validerNomPrenom(nom) ) throw new UtilisateurException();
        this.id = id;
        this.password = password;
        this.login = login;
        this.candidats = new ArrayList<>();
    }

    /**
     * User1 == User2 si seulement si id et cin des utilisateurs ont egaux
 * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)    return true;
        if (obj == null)    return false;
        if (getClass() != obj.getClass())   return false;
        Utilisateur other = (Utilisateur) obj;
        if (!cin.equals(other.cin))         return false;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", email="
                + email + ", genre=" + genre + ", adresse=" + adresse + ", candidats=\n" + candidats + "]";
    }
    
}