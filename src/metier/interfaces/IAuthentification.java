package metier.interfaces;

/**
 * @author ALBODOR
 */
public interface IAuthentification {
    /**
     * Methode pour connecter l'Admibnistrateur
     * @return True s'il est connecte, sinon False
     * @throws Exception
     */
    public boolean connecter() throws Exception;

    /**
     * Methode de deconnecter l'Administrateur
     * @return  True si l'Admin est deconnecte, sinon False
     */
    public boolean deconnecter();

}