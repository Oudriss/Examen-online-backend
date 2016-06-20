package metier.classes;

import java.util.*;

import javax.xml.bind.JAXBException;

import persistence.dao.mysql.UtilisateurDAO;

/**
 * Classe des Utilisateurs
 * @author Soufiane
 */
public class Utilisateurs {

    /**
     * La liste des utilisateurs
     */
    protected ArrayList<Utilisateur> utilisateurs;
    /**
     * DAO utilisateur
     */
    private UtilisateurDAO utilisateurDAO;

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    /**
     * Constructeur par d�faut
     * @throws JAXBException
     */
    public Utilisateurs() throws JAXBException {
        super();
        this.utilisateurDAO = new UtilisateurDAO();
        this.utilisateurs = utilisateurDAO.getAllObject();
    }

   /**
    * Ajouter un utilisateur
     * @param u
    * @return true si l'utilisateur est ajout�, sinon false
    */
   public boolean ajouterUtilisateur(Utilisateur u) {
        if( this.utilisateurs.add(u)){
            utilisateurDAO.addObject(u);
            System.out.println("OKKK");
            return true;
        }
        return false;
   }

   /**
    * modifier un utilisateur
    * @param nouveau
    * @return true si l'utilisateur est modifi�, sinon false
    */
   public boolean modifierUtilisateur(Utilisateur nouveau){
        if( this.utilisateurs.set(this.utilisateurs.indexOf(nouveau), nouveau) != null ){
            System.out.println(nouveau.toString()+"**********************************************************");
            return utilisateurDAO.modifyObject(nouveau);
        }
        return false;
   }

   /**
    * Supprimer un utilisateur
    * @param u
    * @return true si l'utilisateur est supprim�, sinon false
    */
   public boolean supprimerUtilisateur(Utilisateur u) {
        if( this.utilisateurs.remove(u)) {
            return utilisateurDAO.removeObject(u);
        }
        return false;
   }

   /**
    * Rechercher un utilisateur par CIN
    * @param cin
    * @return Utilisateur ou bien null
    */
   public Utilisateur getByCIN(String cin){
        for(int i = 0 ; i < utilisateurs.size() ; i++)
            if( utilisateurs.get(i).getCin().equals(cin)) return utilisateurs.get(i);
        return null;
    }

    /**
     * Afficher les utilisateurs
     */
    public void afficherUtilisateurs(){
        for( int i = 0 ; i < utilisateurs.size() ; i++){
            System.out.println(utilisateurs.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "Utilisateurs [utilisateurs=" + utilisateurs + "]";
    }
}