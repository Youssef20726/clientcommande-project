import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;

public class CommandApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML
            URL fxmlURL = new File("C:\\Users\\User\\Desktop\\java projet youssef\\cli\\src\\saisieArticle.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(fxmlURL);
            Parent root = loader.load();

            // Ajouter la feuille de style
            Scene scene = new Scene(root);
            scene.getStylesheets().add(new File("C:\\Users\\User\\Desktop\\java projet youssef\\cli\\src\\styles.css").toURI().toString());

            // Définir la scène et afficher la fenêtre principale
            primaryStage.setTitle("Gestion d'Articles");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
