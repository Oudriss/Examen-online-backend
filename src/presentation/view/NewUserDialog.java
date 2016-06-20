package presentation.view;

import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import presentation.model.UtilisateurModel;

/**
 * Dialog for Adding a New User
 * @author ALBODOR
 */
public class NewUserDialog {

    private UtilisateurModel newUser;
    private Dialog<UtilisateurModel> dialog;

    public NewUserDialog() {
        // Initializing Objects
        dialog = new Dialog<>();

        // Building Dialog UI
        dialog.setTitle("Nouveau Utilisateur");
        dialog.setHeaderText("Veuillez remplir les champs suivants");

        // Set the button types.
        ButtonType applyButtonType = new ButtonType("Save", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, ButtonType.CANCEL);

        // Create the Dialog labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField cinField = new TextField();
        cinField.setPromptText("CIN");
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");
        TextField prenomField = new TextField();
        prenomField.setPromptText("Prenom");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        TextField adresseField = new TextField();
        adresseField.setPromptText("Adresse");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        grid.add(new Label("CIN :"), 0, 0);
        grid.add(cinField, 1, 0);
        grid.add(new Label("Nom :"), 0, 1);
        grid.add(nomField, 1, 1);
        grid.add(new Label("Prenom :"), 0, 2);
        grid.add(prenomField, 1, 2);
        grid.add(new Label("Email:"), 0, 3);
        grid.add(emailField, 1, 3);
        grid.add(new Label("Adresse :"), 0, 4);
        grid.add(adresseField, 1, 4);
        grid.add(new Label("Username :"), 0, 5);
        grid.add(usernameField, 1, 5);
        grid.add(new Label("Password:"), 0, 6);
        grid.add(passwordField, 1, 6);

        // Enable/Disable login button depending on whether a username was entered.
        Node applyButton = dialog.getDialogPane().lookupButton(applyButtonType);
        applyButton.setDisable(true);

        // Some Fields Text validation
        cinField.textProperty().addListener((observable, oldValue, newValue) -> {
            applyButton.setDisable(newValue.trim().isEmpty());
        });

        // Adding GRID to Dialog Pane
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> cinField.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter((ButtonType dialogButton) -> {
            if (dialogButton == applyButtonType) {
                newUser = new UtilisateurModel(cinField.getText().trim(),  nomField.getText().trim(),
                        prenomField.getText().trim(), emailField.getText().trim(), adresseField.getText().trim(),
                        usernameField.getText().trim(), passwordField.getText());
                return newUser;
            }
            return null;
        });

        Optional<UtilisateurModel> result = dialog.showAndWait();

        result.ifPresent((UtilisateurModel str) -> {
            NewUserDialog.this.newUser = str;
        });
    }

    public UtilisateurModel getNewUser() {
        return this.newUser;
    }

}
