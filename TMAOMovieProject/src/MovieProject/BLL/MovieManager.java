package MovieProject.BLL;

import MovieProject.BE.Movie;
import MovieProject.DAL.IMovieDAO;
import MovieProject.DAL.db.MovieDAO_DB;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MovieManager {
    private IMovieDAO movieDAO;
    public MovieManager() throws IOException {
        movieDAO = new MovieDAO_DB();
    }
    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }
    public Movie addMovie(String name, String description, double rating, double imdb, String movieFile, String imageFile) throws Exception {

        return movieDAO.addMovie(name, description, rating, imdb, movieFile, imageFile);
    }
    public void deletedMovie(Movie deletedMovie) throws Exception {
        movieDAO.deletedMovie(deletedMovie);
    }

        public void updateMovieLastview(Movie updateMovieLastview) throws Exception
        {
            movieDAO.updateMovieLastview(updateMovieLastview);
        }

    public void updateMovieRating(Movie movie, double rating) throws Exception
    {
        movieDAO.updateMovieRating(movie,rating);
    }


    }
