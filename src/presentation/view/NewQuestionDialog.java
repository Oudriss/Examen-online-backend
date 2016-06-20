package presentation.view;

import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import presentation.model.QuestionModel;

/**
 * Dialog for Adding a New Question
 * @author ALBODOR
 */
public class NewQuestionDialog {

    private QuestionModel newQst;

    public NewQuestionDialog(Integer nbrQsts) {
        // Initializing Objects
        Dialog<QuestionModel> dialog = new Dialog<>();

        // Building Dialog UI
        dialog.setTitle("Nouvelle Question");
        dialog.setHeaderText("Veuillez remplir les champs suivants");

        // Set the button types.
        ButtonType applyButtonType = new ButtonType("Save", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, ButtonType.CANCEL);

        // Create the Dialog labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Question TextArea + Note
        TextArea qstArea = new TextArea();
        qstArea.setPromptText("Your question here");
        qstArea.setPrefRowCount(3);
        TextField noteFld = new TextField();
        noteFld.setPromptText("Question mark here");
        grid.add(new Label("Question :"), 0, 0);
        grid.add(qstArea, 1, 0);
        grid.add(new Label("Note :"), 0, 1);
        grid.add(noteFld, 1, 1);
        grid.add(new Label("Vraie?"), 2, 1);

        // Question Reponses TextFields
        ToggleGroup toggleGroup = new ToggleGroup();
        for(int i=1;i<=nbrQsts;i++) {
            grid.add(new Label("Réponse "+i+" :"), 0, 1+i);
            grid.add(new TextField(), 1, 1+i);
            RadioButton button = new RadioButton();
            button.setToggleGroup(toggleGroup);
            grid.add(button, 2, 1+i);
        }
        toggleGroup.selectToggle(toggleGroup.getToggles().get(0));

        // Enable/Disable login button depending on whether a username was entered.
        Node applyButton = dialog.getDialogPane().lookupButton(applyButtonType);
        applyButton.setDisable(true);

        // Some Fields Text validation
        qstArea.textProperty().addListener((observable, oldValue, newValue) -> {
            applyButton.setDisable(newValue.trim().isEmpty());
        });

        // Adding GRID to Dialog Pane
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> qstArea.requestFocus());

        // Convert the result to a Question when the Save button is clicked.
        dialog.setResultConverter((ButtonType dialogButton) -> {
            if (dialogButton == applyButtonType) {
                /* Retrieve Reponse TextFields Values */
                ObservableList<Node> children = grid.getChildren();
                ObservableList<String> reponses = FXCollections.observableArrayList();
                children.stream().filter((node) -> (GridPane.getColumnIndex(node)==1 && GridPane.getRowIndex(node)>1))
                        .map((node) -> (TextField) node).forEach((field) -> {
                            reponses.add(field.getText().trim());
                });
                // Creating a new Question Model Object
                Integer rowIndex = GridPane.getRowIndex((RadioButton) toggleGroup.getSelectedToggle());
                newQst = new QuestionModel(qstArea.getText().trim(), reponses, reponses.get(rowIndex-2),
                                            Float.valueOf(noteFld.getText().trim()));
                return newQst;
            }
            return null;
        });

        Optional<QuestionModel> result = dialog.showAndWait();

        result.ifPresent((QuestionModel str) -> {
            NewQuestionDialog.this.newQst = str;
        });
    }

    public NewQuestionDialog(QuestionModel qst) {
        // Initializing Objects
        Dialog<QuestionModel> dialog = new Dialog<>();

        // Building Dialog UI
        dialog.setTitle(qst.getQuestion());
        dialog.setHeaderText("Vous pouvez modifier les champs suivants");

        // Set the button types.
        ButtonType applyButtonType = new ButtonType("Save", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, ButtonType.CANCEL);

        // Create the Dialog labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Question TextArea + Note
        TextArea qstArea = new TextArea();
        qstArea.setText(qst.getQuestion());
        qstArea.setPrefRowCount(3);
        TextField noteFld = new TextField();
        noteFld.setText(qst.getNote().toString());
        grid.add(new Label("Question :"), 0, 0);
        grid.add(qstArea, 1, 0);
        grid.add(new Label("Note :"), 0, 1);
        grid.add(noteFld, 1, 1);
        grid.add(new Label("Vraie?"), 2, 1);

        // Question Reponses TextFields
        ToggleGroup toggleGroup = new ToggleGroup();
        for(int i=1;i<=qst.getReponses().size();i++) {
            grid.add(new Label("Réponse "+i+" :"), 0, 1+i);
            grid.add(new TextField(qst.getReponses().get(i-1)), 1, 1+i);
            RadioButton button = new RadioButton();
            button.setToggleGroup(toggleGroup);
            grid.add(button, 2, 1+i);
        }
        toggleGroup.selectToggle(toggleGroup.getToggles().get(0));

        // Enable/Disable login button depending on whether a username was entered.
        Node applyButton = dialog.getDialogPane().lookupButton(applyButtonType);
        applyButton.setDisable(true);

        // Some Fields Text validation
        qstArea.textProperty().addListener((observable, oldValue, newValue) -> {
            applyButton.setDisable(newValue.trim().isEmpty());
        });

        // Adding GRID to Dialog Pane
        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> qstArea.requestFocus());

        // Convert the result to a Question when the Save button is clicked.
        dialog.setResultConverter((ButtonType dialogButton) -> {
            if (dialogButton == applyButtonType) {
                /* Retrieve Reponse TextFields Values */
                ObservableList<Node> children = grid.getChildren();
                ObservableList<String> reponses = FXCollections.observableArrayList();
                children.stream().filter((node) -> (GridPane.getColumnIndex(node)==1 && GridPane.getRowIndex(node)>1))
                        .map((node) -> (TextField) node).forEach((field) -> {
                    reponses.add(field.getText().trim());
                });
                // Creating a new Question Model Object
                System.out.println("Size = "+reponses.size());
                Integer rowIndex = GridPane.getRowIndex((RadioButton) toggleGroup.getSelectedToggle());
                System.out.println("rowIndex = "+rowIndex);
                newQst = new QuestionModel(qstArea.getText().trim(), reponses, reponses.get(rowIndex-2),
                                            Float.valueOf(noteFld.getText().trim()));
                return newQst;
            }
            return null;
        });

        Optional<QuestionModel> result = dialog.showAndWait();

        result.ifPresent((QuestionModel str) -> {
            NewQuestionDialog.this.newQst = str;
        });
    }

    public QuestionModel getNewQst() {
        return this.newQst;
    }

}
