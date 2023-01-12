package MovieProject.GUI.Controller;

import MovieProject.BE.Categories;


import MovieProject.BE.Movie;
import MovieProject.BE.NumberTjek;
import MovieProject.GUI.Model.CatMovieModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import MovieProject.GUI.Model.CategoriesModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import MovieProject.GUI.Model.MovieModel;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewMovieController extends BaseController implements Initializable {

    public TextArea txtDescription;
    //Importing and instantiating different models and FXML elements into the NewMovieController constructor.
    private MovieModel movieModel;
    private MainViewController mainController;
    private CategoriesModel categoriesModel;
    private CatMovieModel catMovieModel;
    @FXML
    private Button cancelMovie,btnSelectCategory,btnImageChooseFile,btnMovieChooseFile, addMovie,btnDeselectCategory;
    @FXML
    private TableView<Categories> lstCategories,lstSelectedCategory;
    @FXML
    private TableColumn<Categories, String> tableCategory,tableSelectedCategory;
    @FXML
    private TextField txtMovieFilePath, txtImageFilePath, txtImdbRating,txtMovieTitle,txtUserRating;

    private boolean movieOk=false,pictureOk=false;

    //Initialize method that opens up the window and feeds data to the table-vies.
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //instantiating the models we need to use.
        try {
            categoriesModel = new CategoriesModel();
            movieModel = new MovieModel();
            catMovieModel = new CatMovieModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //lstCategories is a table view but is called lst due to updates. These lines feed the data to the table-view.
        tableCategory.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        lstCategories.setItems(categoriesModel.getCategoriesToBeViewed());

        tableSelectedCategory.setCellValueFactory(new PropertyValueFactory<>("Categories"));
    }

    //gets our model
    public void setup() {
        MovieModel movieModel = getModel();
    }
    //the method that adds and binds the movie with the categories you selected.
    public void handleAddMovie(ActionEvent actionEvent) throws Exception {

        boolean closeOK=false;
        //getting info from our txt-lines and table-views.
        String title = txtMovieTitle.getText();
        String description = txtDescription.getText();

       String userRating=txtUserRating.getText();
        String imdbRating = txtImdbRating.getText();

        NumberTjek numberTjek=new NumberTjek();


        boolean userRating1=numberTjek.tjekNumberIsOK(userRating);
        boolean imdbRating1=numberTjek.tjekNumberIsOK(imdbRating);

        String mfPath = txtMovieFilePath.getText();
        String ifPath = txtImageFilePath.getText();

        //used in our for loop to generate category and movie matches.
        int sizeOfList = lstSelectedCategory.getItems().size();





        if (userRating1 && imdbRating1) {
            //adding the movie
            movieModel.addMovie(title, description, Double.parseDouble(userRating), Double.parseDouble(imdbRating), mfPath, ifPath);
            closeOK=true; //Vi vil kun lukke vinduet, hvis filmen er gemt. Derfor sættes denne boolean.
        }
        else
            mainController.informationUser("Your rating value needs to be between 1-10");

                /* iterable for loop where I have to be smaller than the size of the list of selected categories.
                  I increase each time it goes through the loop, each pass through the loop connects a category with the movie
                */

                for (int i = 0; i < sizeOfList; i++) {

                    //
                    int sizeofMovie = movieModel.showList().size();
                    Movie movie = movieModel.showList().get(sizeofMovie - 1);
                    Categories categories = lstSelectedCategory.getItems().get(i);
                    catMovieModel.addMovieToCategory(movie ,categories);
                }

                if (closeOK && movieOk && pictureOk) //Da filmen er gemt, kan vi godt lukke vinduet.
                {
                    Stage stage = (Stage) addMovie.getScene().getWindow();
                    stage.close();
                }

            }





    public void handleCancelMovie(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelMovie.getScene().getWindow();
        stage.close();
    }
    public void handleImageChooseFile(ActionEvent actionEvent) {
        try {
            mainController = new MainViewController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        FileChooser fc = new FileChooser();
        Stage fileStage = (Stage) cancelMovie.getScene().getWindow();
        File f = fc.showOpenDialog(fileStage);

        if (f != null)
        if (f.getPath().endsWith(".jpg") || f.getPath().endsWith(".png")) {
            txtImageFilePath.setText(f.getPath());
            pictureOk=true;
        }
        else{
            mainController.informationUser("Your file needs to be in jpg or png format");
        }
    }

    public void handleMovieChooseFile(ActionEvent actionEvent) {
        try {
            mainController = new MainViewController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        FileChooser fc = new FileChooser();
        Stage fileStage = (Stage) cancelMovie.getScene().getWindow();
        File f = fc.showOpenDialog(fileStage);
        if (f != null)
        if (f.getPath().endsWith(".mp4") || f.getPath().endsWith("mpeg4")) {
            txtMovieFilePath.setText(f.getPath());
            movieOk=true;
        }
        else{
            mainController.informationUser("Your file needs to be in mp4 or mpeg4 format");
        }
    }

    public void handleSelectCategory(ActionEvent actionEvent) {
        Categories selectedCategory = lstCategories.getSelectionModel().getSelectedItem();
        lstCategories.getItems().remove(selectedCategory);
        lstSelectedCategory.getItems().add(selectedCategory);

    }

    public void handleDeselectCategory (ActionEvent actionEvent) {
        Categories deselectedCategory = lstSelectedCategory.getSelectionModel().getSelectedItem();
        lstSelectedCategory.getItems().remove(deselectedCategory);
        lstCategories.getItems().add(deselectedCategory);

    }
}