package presentation.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.MainApp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

import metier.exceptions.UtilisateurException;
import presentation.controller.UsersController;
import presentation.model.UtilisateurModel;

/**
 * UsersUI FXML Controller
 * @author ALBODOR
 */
public class UsersUI implements Initializable {

    @FXML private VBox root;
    @FXML private Label usersLbl;
    @FXML private TableView usersList;
    @FXML private Button nvBtn, supBtn, enregistrerBtn, retourBtn;
    private Alert alert;


    private UsersController controller;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    @SuppressWarnings("Convert2Lambda")
    public void initialize(URL url, ResourceBundle rb) {
        this.alert = new Alert(AlertType.INFORMATION);

    	
        // Defining Table Columns
        TableColumn cinCol = new TableColumn("CIN");
        cinCol.prefWidthProperty().bind(usersList.widthProperty().multiply(0.1));
        TableColumn nomCol = new TableColumn("Nom");
        nomCol.prefWidthProperty().bind(usersList.widthProperty().multiply(0.2));
        TableColumn prenomCol = new TableColumn("Prenom");
        prenomCol.prefWidthProperty().bind(usersList.widthProperty().multiply(0.2));
        TableColumn emailCol = new TableColumn("Email");
        emailCol.prefWidthProperty().bind(usersList.widthProperty().multiply(0.1));
        TableColumn adresseCol = new TableColumn("Adresse");
        adresseCol.prefWidthProperty().bind(usersList.widthProperty().multiply(0.2));
        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.prefWidthProperty().bind(usersList.widthProperty().multiply(0.1));
        TableColumn passwordCol = new TableColumn("Password");
        passwordCol.prefWidthProperty().bind(usersList.widthProperty().multiply(0.1));
        

        //Selection multiple
        usersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Adding Columns to Table
        this.usersList.getColumns().addAll(cinCol, nomCol, prenomCol, emailCol, adresseCol,
                usernameCol, passwordCol);

        // Generating Data
        try {
            controller = new UsersController();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        final ObservableList<UtilisateurModel> data = controller.getUsers();

        // Linking Table Columns To Model Fields
        cinCol.setCellValueFactory(
            new PropertyValueFactory<>("cin")
        );
        nomCol.setCellValueFactory(
            new PropertyValueFactory<>("nom")
        );
        prenomCol.setCellValueFactory(
            new PropertyValueFactory<>("prenom")
        );
        emailCol.setCellValueFactory(
            new PropertyValueFactory<>("email")
        );
        adresseCol.setCellValueFactory(
            new PropertyValueFactory<>("adresse")
        );
        usernameCol.setCellValueFactory(
            new PropertyValueFactory<>("username")
        );
        passwordCol.setCellValueFactory(
            new PropertyValueFactory<>("password")
        );

        // Adding Data To Table
        this.usersList.getItems().addAll(data);

        // Preparing Columns for Editing
        this.usersList.setEditable(true);

        TextFieldTableCell.forTableColumn();
        cinCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<UtilisateurModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<UtilisateurModel, String> t) {
                    ((UtilisateurModel)t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setCin(t.getNewValue());
                }
            }
        );

        nomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nomCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<UtilisateurModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<UtilisateurModel, String> t) {
                    ((UtilisateurModel)t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setNom(t.getNewValue());
                }
            }
        );

        prenomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<UtilisateurModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<UtilisateurModel, String> t) {
                    ((UtilisateurModel)t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setPrenom(t.getNewValue());
                }
            }
        );

        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<UtilisateurModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<UtilisateurModel, String> t) {
                    ((UtilisateurModel)t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setEmail(t.getNewValue());
                }
            }
        );

        adresseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        adresseCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<UtilisateurModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<UtilisateurModel, String> t) {
                    ((UtilisateurModel)t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setAdresse(t.getNewValue());
                }
            }
        );

        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        usernameCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<UtilisateurModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<UtilisateurModel, String> t) {
                    ((UtilisateurModel)t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setUsername(t.getNewValue());
                }
            }
        );

        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<UtilisateurModel, String>>() {
                @Override public void handle(TableColumn.CellEditEvent<UtilisateurModel, String> t) {
                    ((UtilisateurModel) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                            .setPassword(t.getNewValue());
                }
            }
        );

    }

    @FXML
    private void nvBtnAction(ActionEvent event) {
        NewUserDialog newUserDialog = new NewUserDialog();

        try {
            controller.addUser(newUserDialog.getNewUser());
            // Refresh Data on Table
            this.usersList.getItems().clear();
            this.usersList.getItems().addAll(controller.getUsers());
        } catch (UtilisateurException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void supBtnAction(ActionEvent event) {
        try {
            controller.removeUser((UtilisateurModel) this.usersList.getSelectionModel().getSelectedItem());
            // Refresh Data on Table
            this.usersList.getItems().clear();
            this.usersList.getItems().addAll(controller.getUsers());
            alert.setTitle("Suppression");
            alert.setHeaderText("Utilisateur");
            alert.setContentText("L'utilisateur est supprimé");
            alert.showAndWait();
        } catch (UtilisateurException e) {
        	alert.setTitle("Suppression");
            alert.setHeaderText("Utilisateur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void enregistrerBtnAction(ActionEvent event) {
        try {
            controller.enregistrer( usersList.getSelectionModel().getSelectedItems() );
            // Refresh Data on Table
            this.usersList.getItems().clear();
            this.usersList.getItems().addAll(controller.getUsers());
            alert.setTitle("Enregistrer");
            alert.setHeaderText("Utilisateurs");
            alert.setContentText("Les utilisateurs sont enregistré");
            alert.showAndWait();
        } catch (UtilisateurException e) {
        	alert.setTitle("Enregistrer");
            alert.setHeaderText("Utilisateurs");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            e.getMessage();
        }
    }

    @FXML
    private void retourBtnAction(ActionEvent event) {
    	try {
            controller.gestionExamens();
    	} catch (IOException e) {
    		alert.setTitle("Enregistrer");
            alert.setHeaderText("Utilisateurs");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.err.println(e.getMessage());
        }
    }

}
