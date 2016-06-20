package metier.interfaces;

/**
 * @author ALBODOR
 */
public interface ICandidature {

    /**
     * Methode pour Saisir une Reponses
     * @param code Code de la Question
     * @param reponse Le Reponses
     * @return True si la Reponses est bien saisie, sinon False
     */
    public boolean saisirReponse(int code, String reponse);

    /**
     * Exporter les Reponses
     * @return  True si les Reponses sont bien exportes, sinon False
     */
    public boolean exporterReponses();

    /**
     * Calculer la note
     * @return Note
     */
    public float calculerNote();

}