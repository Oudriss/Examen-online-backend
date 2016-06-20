package metier.interfaces;

import metier.classes.Question;

/**
 * @author ALBODOR
 */
public interface IExamen {

    /**
     * Methode pour Ajouter une question
     * @param question la Question a ajouter
     * @return True si la Question est ajoutee, sinon False
     */
    public boolean ajouterQuestion(Question question);

    /**
     * Methode pour Modifier une Question
     * @param nouveau Nouvelle Question
     * @param ancien Ancienne Question
     * @return True si la Question est modifiee, sinon False
     */
    public boolean modifierQuestion(Question nouveau,Question ancien);

    /**
     * Methode pour Supprimer une question
     * @param question la Question a supprimer
     * @return True si la Question est supprimee, sinon False
     */
    public boolean supprimerQuestion(Question question);

}