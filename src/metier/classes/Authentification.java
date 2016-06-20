package metier.classes;

/**
 * La classe Authentification
 * @author Soufiane
 */
public abstract class  Authentification {

    /**
     * Login
     */
    private String login;
    /**
     * Password
     */
    private String password;
    /**
     * connect� egal � true si l'utilisateur est connect�, sinon false
     */
    private boolean connecte;

    /**
     * Cette methode permet de connecter l'utilisateur en verfiant son mot de passe et login
     * @return
      * @throws java.lang.Exception
     */
    public abstract boolean connecter() throws Exception;

    /**
     * Si l'utilisateur est connect� , cette methode sert � deconnecter l'utilisateur
     * @return
     */
    public Boolean deconnecter(){
        if( this.connecte == true) connecte = false;
        return !connecte;
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

    public boolean isConnecte() {
        return connecte;
    }

    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }

}