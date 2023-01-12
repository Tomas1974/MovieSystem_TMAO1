package MovieProject.DAL.db;

import MovieProject.BE.Movie;
import MovieProject.DAL.IMovieDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class MovieDAO_DB implements IMovieDAO {
    private DatabaseConnector databaseConnector;

    public MovieDAO_DB() throws IOException {
        databaseConnector = new DatabaseConnector();
    }


    @Override
    public List<Movie> getAllMovies() throws SQLServerException {
        ArrayList<Movie> allMovies = new ArrayList<>();
        boolean toOld;

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM dbo.Movie;";


            ResultSet rs = stmt.executeQuery(sql);

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

                if (days>=730 && rating<6 )
                    toOld = true;
                else
                    toOld = false;


               Movie movie = new Movie(id, name, description, rating, imdb, movieFile, imageFile, lastview, toOld);

               allMovies.add(movie);
            }

            return allMovies;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not get Movies from database", ex);
        }
    }

    @Override
    public Movie addMovie(String name, String description, double rating, double imdb, String movieFile, String imageFile) throws Exception {
        String sql = "INSERT INTO movie (name, description, rating, imdb, movieFile, imageFile, lastview) VALUES (?,?,?,?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, rating);
            stmt.setDouble(4, imdb);
            stmt.setString(5, movieFile);
            stmt.setString(6, imageFile);
            stmt.setDate(7, Date.valueOf(LocalDate.now()));


            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create Song object and send up the layers
            Movie movie = new Movie(id, name, description, rating, imdb, movieFile, imageFile, LocalDate.now(),false);
            
            return movie;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create song", ex);
        }
    }

    @Override
    public void deletedMovie(Movie deletedMovie) throws Exception {
        try (Connection conn = databaseConnector.getConnection()) {

            String sql = "DELETE CatMovie FROM CatMovie inner join Movie on Movie.Id=CatMovie.MOvieId \n" +
                    "WHERE CatMovie.MOvieId=? DELETE from Movie WHERE Movie.Id=?;";

            PreparedStatement stmt = conn.prepareStatement(sql);

            /*
            Vi sletter fra vores song tabel. Men eftersom vi har foreign keys er vi nød til specificere dem. Derfor skrives inner join.
            Her beskriver bindingen mellem child og parent tabellerne.
            Til sidst beskrives hvilket parameter, her movie nummer, vi sletter efter. Nu vil movie og referencer i krydstabellen
            blive slettet.
            */

            // Bind parameters

            stmt.setInt(1, deletedMovie.getId());
            stmt.setInt(2, deletedMovie.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete song", ex);
        }
    }

    @Override
    public void updateMovieLastview(Movie movie) throws Exception {

        String sql = "UPDATE movie SET  Lastview=? WHERE ID = ?";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
           stmt.setInt(2, movie.getId());


            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }
    }

    public void updateMovieRating(Movie movie, double rating) throws Exception {

        String sql = "UPDATE movie SET  Rating=? WHERE ID = ?";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setDouble(1,rating );
            stmt.setInt(2, movie.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }
    }
}

