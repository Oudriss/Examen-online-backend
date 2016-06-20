package presentation.view;

import java.net.URL;
import java.util.ResourceBundle;
import application.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import presentation.controller.LoginController;

/**
 * LoginUI FXML Controller
 * @author ALBODOR
 */
public class LoginUI implements Initializable {
    @FXML private AnchorPane root;
    @FXML private GridPane gridPane;
    @FXML private Label usernameLbl, passwordLbl;
    @FXML private TextField usernameFld;
    @FXML private PasswordField passwordFld;
    @FXML private Button loginBtn, annulerBtn;

    private Alert alert = new Alert(AlertType.WARNING);
    private LoginController controller;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	controller = new LoginController();
    	alert.setTitle("Erreur d'authentification");
        alert.setHeaderText("Impossible de s'authentifier");
    }

    /**
     * L'action listner du button loginBtn
     * @param event
     */
    @FXML
    private void loginBtnAction(ActionEvent event) {
    	try {
            if( !controller.authentifier(usernameFld.getText().trim(), passwordFld.getText()) ){
                alert.setContentText("Password ou username est erron� !!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
    /**
     * Action Listner du button annulerBtn
     * @param e
     */
    @FXML
    private void annulerBtnAction(ActionEvent e) {
        this.usernameFld.setText("");
        this.passwordFld.setText("");
    }

}
