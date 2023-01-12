package MovieProject.DAL;

import MovieProject.BE.Movie;


import java.util.List;


public interface IMovieDAO {
    public List<Movie> getAllMovies() throws Exception;

    public Movie addMovie(String name, String description, double rating, double imdb, String movieFile, String imageFile) throws Exception;

    public void deletedMovie(Movie deletedMovie) throws Exception;

    public void updateMovieLastview(Movie movie) throws Exception;

    public void updateMovieRating(Movie movie, double rating) throws Exception;


}

