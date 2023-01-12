package MovieProject;
import MovieProject.GUI.Controller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/View/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("application.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        MainViewController mainViewController=new MainViewController();

        mainViewController.informationUser("Check if a movie is to old");
    }
}









