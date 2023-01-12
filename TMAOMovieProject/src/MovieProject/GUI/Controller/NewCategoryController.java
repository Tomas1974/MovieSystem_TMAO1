package MovieProject.GUI.Controller;

import MovieProject.GUI.Model.CategoriesModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewCategoryController extends BaseController implements Initializable {

    @FXML
    private Button cancelCategory;
    @FXML
    private Button saveCategory;
    @FXML
    private TextField categoryTextField;

    @Override
    public void setup() {
        categoriesModel = getCategoriesModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage=(Stage) cancelCategory.getScene().getWindow();
        stage.close();
    }

    public void handleSaveCategory(ActionEvent actionEvent) throws Exception{
        try {
            categoriesModel.addCategories(categoryTextField.getText());

            Stage stage = (Stage) saveCategory.getScene().getWindow();
            stage.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}