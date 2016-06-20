package presentation.view;

import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Dialog to get the Number of Answer
 * @author ALBODOR
 */
public class NbreReponsesDialog {

    private Integer nbreReponses = 0;

    public NbreReponsesDialog() {
        // Initializing Objects
        Dialog<Integer> dialog = new Dialog<>();

        // Building Dialog UI
        dialog.setTitle("Nombre de Réponses");
        dialog.setHeaderText("Veuillez fournir le nombre de Réponses souhaités");

        // Set the button types.
        ButtonType applyButtonType = new ButtonType("OK", ButtonBar.ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, ButtonType.CANCEL);

        // Create the Dialog labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField nbrField = new TextField();
        nbrField.setPromptText("Your number here");

        grid.add(new Label("Nombre de Réponses :"), 0, 0);
        grid.add(nbrField, 1, 0);

        // Enable/Disable login button depending on whether a username was entered.
        Node applyButton = dialog.getDialogPane().lookupButton(applyButtonType);
        applyButton.setDisable(true);

        // Some Fields Text validation
        nbrField.textProperty().addListener((observable, oldValue, newValue) -> {
            applyButton.setDisable(newValue.trim().isEmpty());
        });

        // Adding GRID to Dialog Pane
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> nbrField.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter((ButtonType dialogButton) -> {
            if (dialogButton == applyButtonType) {
                return Integer.valueOf(nbrField.getText().trim());
            }
            return 0;
        });

        Optional<Integer> result = dialog.showAndWait();

        result.ifPresent((Integer nbr) -> {
            NbreReponsesDialog.this.nbreReponses = nbr;
        });
    }

    public Integer getNbreReponses() {
        return this.nbreReponses;
    }

}
