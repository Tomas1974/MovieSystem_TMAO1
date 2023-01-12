package MovieProject.GUI.Model;

// Java imports
import MovieProject.BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Project imports
import MovieProject.BE.Movie;

public class MovieModel {
    private ObservableList<Movie> moviesToBeViewed;
    private MovieManager movieManager;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }

    public ObservableList<Movie> getObservableMovies() {
        return moviesToBeViewed;
    }


    public ObservableList<Movie> searchedMovie(String search) {
        ObservableList<Movie> searchedMovie = FXCollections.observableArrayList();
        for (Movie movie : moviesToBeViewed) {
            if (movie.getName().toLowerCase().contains(search)) {
                searchedMovie.add(movie);
            }
        }
        return searchedMovie;
    }

    public ObservableList<Movie> showList() throws Exception {
        //Update the listview
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
        return moviesToBeViewed;
    }


    public void addMovie(String name, String description, double rating, double imdb, String movieFile, String imageFile) throws Exception {
        movieManager.addMovie(name, description, rating, imdb, movieFile, imageFile);
    }

    public void deletedMovie(Movie deletedMovie) throws Exception {
        movieManager.deletedMovie(deletedMovie);
    }

    public void updateMovie(Movie updateMovieLastview) throws Exception {
        movieManager.updateMovieLastview(updateMovieLastview);
    }

    public void updateMovieRating(Movie movie, double rating) throws Exception {
        movieManager.updateMovieRating(movie, rating);
    }
}
