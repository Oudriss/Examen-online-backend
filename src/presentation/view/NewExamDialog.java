package presentation.view;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import utils.DateConverter;
import presentation.model.QuestionModel;
import presentation.model.ExamenModel;

/**
 * Dialog for Adding a New Exam
 * @author ALBODOR
 */
public class NewExamDialog {

    private ExamenModel newExam;
    private Dialog<ExamenModel> dialog;
    private DateConverter converter;

    public NewExamDialog(ExamenModel exam) {
        // Initializing Objects
        dialog = new Dialog<>();
        converter = new DateConverter();
        newExam = new ExamenModel();

        // Building Dialog UI
        dialog.setTitle("Modification d'Examen");
        dialog.setHeaderText("Vous pouvez modifier les champs suivants");

        // Set the button types.
        ButtonType applyButtonType = new ButtonType("Save", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, ButtonType.CANCEL);

        // Create the Dialog labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField sujetField = new TextField();
        sujetField.setText(exam.getSujet());

        grid.add(new Label("Sujet :"), 0, 0);
        grid.add(sujetField, 1, 0);

        // DatePicker Configuration
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText(converter.toString(DateConverter.fromDate(exam.getDate())));
        datePicker.setConverter(converter);

        grid.add(new Label("Date :"), 0, 1);
        grid.add(datePicker, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node applyButton = dialog.getDialogPane().lookupButton(applyButtonType);
        applyButton.setDisable(true);

        // Some Fields Text validation
        sujetField.textProperty().addListener((observable, oldValue, newValue) -> {
            applyButton.setDisable(newValue.trim().isEmpty());
        });

        // Adding GRID to Dialog Pane
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> sujetField.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter((ButtonType dialogButton) -> {
            if (dialogButton == applyButtonType) {
                exam.setSujet(sujetField.getText().trim());
                exam.setDate(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                return exam;
            }
            return null;
        });

        Optional<ExamenModel> result = dialog.showAndWait();

        result.ifPresent((ExamenModel str) -> {
            newExam.setCandidats(str.getCandidats());
            newExam.setSujet(str.getSujet());
            newExam.setQuestions(str.getQuestions());
            newExam.setDate(str.getDate());
        });
    }

    public NewExamDialog() {
        // Initializing Objects
        dialog = new Dialog<>();
        converter = new DateConverter();

        // Building Dialog UI
        dialog.setTitle("Nouveau Examen");
        dialog.setHeaderText("Veuillez remplir les champs suivants");

        // Set the button types.
        ButtonType applyButtonType = new ButtonType("Save", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, ButtonType.CANCEL);

        // Create the Dialog labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField sujetField = new TextField();
        sujetField.setPromptText("Sujet");

        grid.add(new Label("Sujet :"), 0, 0);
        grid.add(sujetField, 1, 0);
        // DatePicker Configuration
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("dd/MM/yyyy");
        datePicker.setConverter(converter);

        grid.add(new Label("Date :"), 0, 1);
        grid.add(datePicker, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node applyButton = dialog.getDialogPane().lookupButton(applyButtonType);
        applyButton.setDisable(true);

        // Some Fields Text validation
        sujetField.textProperty().addListener((observable, oldValue, newValue) -> {
            applyButton.setDisable(newValue.trim().isEmpty());
        });

        // Adding GRID to Dialog Pane
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> sujetField.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter((ButtonType dialogButton) -> {
            if (dialogButton == applyButtonType) {
                ObservableList<QuestionModel> questions = FXCollections.observableArrayList();
                newExam = new ExamenModel(sujetField.getText().trim(),
                        Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        FXCollections.observableArrayList(),
                        questions);
                return newExam;
            }
            return null;
        });

        Optional<ExamenModel> result = dialog.showAndWait();

        result.ifPresent((ExamenModel str) -> {
            NewExamDialog.this.newExam = str;
        });
    }

    public ExamenModel getNewExam() {
        return this.newExam;
    }

}
