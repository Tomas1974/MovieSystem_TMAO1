package MovieProject.GUI.Model;

import MovieProject.BE.Categories;
import MovieProject.BE.Movie;
import MovieProject.BLL.CatMovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CatMovieModel {

    private ObservableList<Movie> moviesToBeViewed;
    private CatMovieManager catMovieManager;

    public CatMovieModel() throws Exception {

        catMovieManager=new CatMovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(catMovieManager.getAllMoviesFromCategory(8)); //Vi starter med at vise alle film. Denne kategorie har nummeret 8.
    }

    public ObservableList<Movie> getObservableMovies()
    {
        return moviesToBeViewed;
    }


    public ObservableList<Movie> showList(int categorieNumber) throws Exception {
        //Update the listview
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(catMovieManager.getAllMoviesFromCategory(categorieNumber));
        return moviesToBeViewed;
    }

    public void addMovieToCategory(Movie selectedMovie, Categories selectedCategory) throws SQLException
    {
        catMovieManager.addMovieToCategory(selectedMovie, selectedCategory);
    }
}
