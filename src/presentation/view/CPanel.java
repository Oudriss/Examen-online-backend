package presentation.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import application.MainApp;
import presentation.controller.CPanelController;
import presentation.controller.UsersController;
import presentation.model.CandidatureModel;
import presentation.model.ExamenModel;
import presentation.model.QuestionModel;
import presentation.model.UtilisateurModel;

/**
 * Interface Graphique Principal (CPanel) FXML Controller
 * @author ALBODOR
 */
public class CPanel implements Initializable {
    /**
     * Declaration des Composants FXML
     */
    @FXML private VBox root;
    @FXML private Label examensLbl, questionsLbl, selectedUsersLbl, allUsersLbl;
    @FXML private ListView examensList, selectedUsersList, allUsersList;
    @FXML private TreeView questionsList;
    @FXML private Button    enregistrerBtn,nvExamenBtn, modExamenBtn, supExamenBtn,pdfBtn,
                            nvQuestionBtn, modQuestionBtn, supQuestionBtn, upBtn, downBtn;
    @FXML private MenuItem gestionItem;
    private TreeItem<QuestionModel> rootItem;
    private Alert alert;
    /**
     * Declaration des Controleurs
     */
    private CPanelController cPanelController;
    private UsersController usersController;

    /**
     * Initialiser les Composants de l'Interface Graphique (Composants FXML)
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cPanelController = new CPanelController();
        this.alert = new Alert(AlertType.INFORMATION);

        try {
            this.usersController = new UsersController();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Remplissage de la Liste des Examens
        examensList.getItems().addAll(cPanelController.getAllExamens());

        // Remplissage de la Liste de tous les Utilisateurs
        allUsersList.getItems().addAll(usersController.getUsers());

        // Après qu'un Examen est sélectionné, changer ses Questions dans la Liste des Questions
        examensList.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue,
            Object newValue) -> {
                initQstList((ExamenModel) newValue);
                initSelectedUsersList((ExamenModel) newValue);
        });

    }

    /**
     * Initialiser la liste des Questions par ceux de l'Examen selectionne
     * @param selectedExam Examen selectionne
     */
    private void initQstList(ExamenModel selectedExam) {
        rootItem = new TreeItem<>(new QuestionModel());
        rootItem.setExpanded(true);
        this.questionsList.setShowRoot(false);

        selectedExam.getQuestions().stream().forEach((qst) -> {
            TreeItem<QuestionModel> qstItem = new TreeItem<>(qst);
            qstItem.setExpanded(true);
            rootItem.getChildren().add(qstItem);
        });

        this.questionsList.setRoot(rootItem);
    }

    /**
     * Initialiser la liste des Utilisateur selectionnes par ceux de l'Examen choisi
     * @param selectedExam Examen selectionne
     */
    private void initSelectedUsersList(ExamenModel selectedExam) {
        this.selectedUsersList.getItems().clear();

        selectedExam.getCandidats().stream().forEach((candidat) -> {
            this.selectedUsersList.getItems().add(candidat.getUser());
        });
    }


    /**
     * Handler de click sur la Boutton "Nouveau" du Panneau des Examens
     * @param event Evennement de Click
     */
    @FXML
    private void nvExamenBtnAction(ActionEvent event) {
        ExamenModel newExam = new NewExamDialog().getNewExam();
        if(newExam != null) {
            cPanelController.addExamen(newExam);
            examensList.setItems(cPanelController.getAllExamens());
            alert.setTitle("Ajout d'examen");
            alert.setHeaderText("Examen");
            alert.setContentText("L'examenest ajout�");
            alert.showAndWait();
        }
    }

    /**
     * Handler de clique sur la Boutton "Modifier" du Panneau des Examens
     * @param event Evennement de Click
     */
    @FXML
    private void modExamenBtnAction(ActionEvent event) {
        ExamenModel newExam = (ExamenModel) this.examensList.getSelectionModel().getSelectedItem();
        if(newExam != null) {
            new NewExamDialog(newExam);
            cPanelController.modExamen(newExam);
            examensList.getItems().setAll(cPanelController.getAllExamens());
        }
    }

    /**
     * Handler de clique sur la Boutton "Supprimer" du Panneau des Examens
     * @param event Evennement de Click
     */
    @FXML
    private void supExamenBtnAction(ActionEvent event) {
        ExamenModel selectedExam = (ExamenModel) this.examensList.getSelectionModel().getSelectedItem();
        if(selectedExam != null) {
            cPanelController.removeExamen(selectedExam);
            examensList.setItems(cPanelController.getAllExamens());
            alert.setTitle("Suppression");
            alert.setHeaderText("Examen");
            alert.setContentText("L'examen est supprim�");
            alert.showAndWait();
        }
    }

    /**
     * Handler de clique sur la Boutton "Nouvelle" du Panneau des Questions
     * @param event Evennement de Click
     */
    @FXML
    private void nvQuestionBtnAction(ActionEvent event) {
        QuestionModel newQst = new NewQuestionDialog(new NbreReponsesDialog().getNbreReponses()).getNewQst();
        ExamenModel selectedExamen = (ExamenModel) examensList.getSelectionModel().getSelectedItem();

        if(newQst != null && selectedExamen != null) {
            cPanelController.addQuestion(selectedExamen, newQst);
            initQstList(selectedExamen);
        }
    }

    /**
     * Handler de clique sur la Boutton "Modifier" du Panneau des Questions
     * @param event Evennement de Click
     */
    @FXML
    private void modQuestionBtnAction(ActionEvent event) {
        TreeItem<QuestionModel> selectedItem = (TreeItem<QuestionModel>) this.questionsList.getSelectionModel().getSelectedItem();
        QuestionModel selectedQst = selectedItem.getValue();

        if(selectedQst != null) {
            QuestionModel modifiedQst = new NewQuestionDialog(selectedQst).getNewQst();
            ExamenModel selectedExamen = (ExamenModel) examensList.getSelectionModel().getSelectedItem();

            if(modifiedQst != null && selectedExamen != null) {
                cPanelController.removeQuestion(selectedExamen, selectedQst);
                cPanelController.addQuestion(selectedExamen, modifiedQst);
                initQstList(selectedExamen);
            }
        }
    }

    /**
     * Handler de clique sur la Boutton "Supprimer" du Panneau des Examens
     * @param event Evennement de Click
     */
    @FXML
    private void supQuestionBtnAction(ActionEvent event) {
        TreeItem<QuestionModel> selectedItem = (TreeItem<QuestionModel>) this.questionsList.getSelectionModel()
                                                                                        .getSelectedItem();
        ExamenModel selectedExamen = (ExamenModel) this.examensList.getSelectionModel().getSelectedItem();
        
        if(this.questionsList.getRoot().getChildren().remove(selectedItem)) {
            selectedExamen.getQuestions().remove(selectedItem.getValue());
            initQstList(selectedExamen);
            alert.setTitle("Suppression");
            alert.setHeaderText("Question");
            alert.setContentText("La question est supprim�");
            alert.showAndWait();
        }

    }

    /**
     * Handler de clique sur la Boutton "Up" du Panneau des Utilisateurs
     * @param event Evennement de Click
     */
    @FXML
    private void upBtnAction(ActionEvent event) {
        UtilisateurModel selectedAllUser = (UtilisateurModel) allUsersList.getSelectionModel().getSelectedItem();

        if(!this.selectedUsersList.getItems().contains(selectedAllUser)) {
            ExamenModel selectedExam = (ExamenModel) this.examensList.getSelectionModel().getSelectedItem();

            if(selectedExam != null) {
                CandidatureModel candidat = new CandidatureModel(FXCollections.observableHashMap(),
                                                    selectedExam, selectedAllUser);
                selectedExam.getCandidats().add(candidat);
                this.selectedUsersList.getItems().add(selectedAllUser);
            } else {
                alert.setTitle("Erreur");
                alert.setHeaderText("Pas d'Examen séléctionné!");
                alert.setContentText("SVP choisissez d'abord un examen!");
                alert.showAndWait();
            }
        } else {
            alert.setTitle("Erreur");
            alert.setHeaderText("Ooops,");
            alert.setContentText("Utilisateur choisi déjà fait partie des utilisateurs séléctionnés!");
            alert.showAndWait();
        }

    }

    /**
     * Handler de clique sur la Boutton "Down" du Panneau des Utilisateurs
     * @param event Evennement de Click
     */
    @FXML
    private void downBtnAction(ActionEvent event) {
        UtilisateurModel selectedUser = (UtilisateurModel) selectedUsersList.getSelectionModel().getSelectedItem();
        if(selectedUser != null) {
            ExamenModel selectedExam = (ExamenModel) this.examensList.getSelectionModel().getSelectedItem();
            if(selectedExam != null) {
                selectedExam.getCandidats().remove(selectedUsersList.getItems().indexOf(selectedUser));
                this.selectedUsersList.getItems().remove(selectedUser);
            } else {
                alert.setTitle("Erreur");
                alert.setHeaderText("Pas d'Examen séléctionné!");
                alert.setContentText("SVP choisissez d'abord un examen!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void enregistrerBtnAction(ActionEvent event){
        ExamenModel selectedExam = (ExamenModel) this.examensList.getSelectionModel().getSelectedItem();

        if( selectedExam != null){
            try {
                cPanelController.enregistrer(selectedExam);
                alert.setTitle("Enregistrement");
                alert.setHeaderText("Commit");
                alert.setContentText("L'examen est enregistr�!");
                alert.showAndWait();
            } catch (Exception e) {
            	alert.setTitle("Enregistrement");
                alert.setHeaderText("Commit");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                System.err.println(e.getMessage());
            }
        } else {
            alert.setTitle("Erreur");
            alert.setHeaderText("Pas d'Examen séléctionné!");
            alert.setContentText("SVP choisissez d'abord un examen!");
            alert.showAndWait();
        }
    }
    
    
    @FXML
    private void pdfBtnAction(ActionEvent event){
        ExamenModel selectedExam = (ExamenModel) this.examensList.getSelectionModel().getSelectedItem();

        if( selectedExam != null){
            try {
                cPanelController.exporterPdf(selectedExam,"pdfs");
                alert.setTitle("PDF");
                alert.setHeaderText("Exporter");
                alert.setContentText("PDF est enregistr�!");
                alert.showAndWait();
            } catch (Exception e) {
            	alert.setTitle("Enregistrement");
                alert.setHeaderText("Commit");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                System.err.println(e.getMessage());
            }
        } else {
            alert.setTitle("Erreur");
            alert.setHeaderText("Pas d'Examen séléctionné!");
            alert.setContentText("SVP choisissez d'abord un examen!");
            alert.showAndWait();
        }
    }

    @FXML
    private void gestionItemAction(ActionEvent event){
    	try {
            cPanelController.gestionUtilisateur();
        } catch (IOException e) {
        	alert.setTitle("Erreur");
            alert.setHeaderText("PGestion d'utilisateur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.err.println(e.getMessage());
        }
    }

}
