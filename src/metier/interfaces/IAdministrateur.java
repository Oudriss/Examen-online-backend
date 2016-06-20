package metier.interfaces;

import java.util.ArrayList;
import java.util.Date;

import metier.classes.Examen;
import metier.classes.Question;

/**
 * @author ALBODOR
 */
public interface IAdministrateur {

    /**
     * Methode pour la Saisie d'un Examen
     * @param sujet
     * @param date
     * @param questions
     * @return True si l'Examen est saisie correctement, sinon False
     * @throws Exception
     */
    public boolean saisirExamen(String sujet, Date date,ArrayList<Question> questions) throws Exception;

    /**
     * Methode pour l'Attribution d'un Examen
     * @param code
     * @param cin
     * @return True si l'Examen est attribue, sinon False
     * @throws Exception
     */
    public boolean attribuerExamen(int code,String cin) throws Exception;

    /**
     * Methode pour Exporter un Examen en PDF
     */
    public void exporterExamen(Examen examen,String destination)throws Exception;

}