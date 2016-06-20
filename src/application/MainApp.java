package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import metier.classes.Administrateur;

/**
 *
 * @author Badr ELOUIZ
 */
public class MainApp extends Application{

    public static Stage PrimaryStage;
    public static Administrateur admin;


    @Override
    public void start(Stage stage) throws Exception {
    	PrimaryStage = stage;
        // Main UI
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("presentation/view/LoginUI.fxml"));
        // Users UI
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("presentation/view/UsersUI.fxml"));

        Scene scene = new Scene(root);
        PrimaryStage.setScene(scene);
        PrimaryStage.setTitle("Authentification");
        PrimaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
