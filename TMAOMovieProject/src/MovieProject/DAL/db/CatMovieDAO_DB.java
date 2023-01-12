package MovieProject.DAL.db;

import MovieProject.BE.Categories;
import MovieProject.BE.Movie;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class CatMovieDAO_DB {

    private DatabaseConnector databaseConnector;


    public CatMovieDAO_DB() throws SQLException, IOException {
        databaseConnector = new DatabaseConnector();
    }


    public List<Movie> getAllMoviesFromCategory(int categoriesId) throws Exception {
        ArrayList<Movie> allMovies = new ArrayList<>();
        boolean toOld;

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM Movie M,CatMovie CM, Category C  WHERE M.Id = CM.MOvieId and C.Id = CM.CategoryId and CM.CategoryId=" + categoriesId + ";";

            // SQL koden henter fra vores tre tabeller, hvor movie id i krydstabellen og movie skal være ens
            // og categorise nummer skal være ens i categorise og i krydstabellen.


            ResultSet rs = stmt.executeQuery(sql);  //Her vises resultat settet

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Movie object
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String description = rs.getString("description");
                double rating = rs.getDouble("Rating");
                double imdb = rs.getDouble("imdb");
                String movieFile = rs.getString("MovieFile");
                String imageFile = rs.getString("ImageFile");
                LocalDate lastview = rs.getDate("Lastview").toLocalDate();

                int days = (int) DAYS.between(lastview, LocalDate.now());

                if (days >= 730 && rating < 6)
                    toOld = true;
                else
                    toOld = false;


                Movie movie = new Movie(id, name, description, rating, imdb, movieFile, imageFile, lastview, toOld);

                allMovies.add(movie);
            }

            return allMovies;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get Movies from database", ex);
        }

        }

    public void addMovieToCategory(Movie  selectedMovie, Categories selectedCategory) throws SQLException {

        String sql = "INSERT INTO CatMovie (CategoryId, MOvieId) VALUES (?,?);";

        //Metoden hvor man bruger en preparedStatement sikre mod SQL injection. Således at man ikke kan ødelægge databasen.
        // Ved spørgsmåltegnene indsættes data her tal. Data er tilføjet metoden og indsættes i setInt nedenunder.


        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int category = selectedCategory.getId();
            int movie = selectedMovie.getId();
            stmt.setInt(1, category);
            stmt.setInt(2, movie);
            stmt.executeUpdate();       //Her opdateres databasen.

        }


    }






    }
