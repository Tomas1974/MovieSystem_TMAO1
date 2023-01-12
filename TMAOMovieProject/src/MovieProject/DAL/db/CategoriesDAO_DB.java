package MovieProject.DAL.db;

import MovieProject.BE.Categories;
import MovieProject.BE.Movie;
import MovieProject.DAL.ICategoriesDAO;

import java.io.IOException;
import java.security.cert.Extension;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO_DB implements ICategoriesDAO {

    private DatabaseConnector databaseConnector;

    public CategoriesDAO_DB() throws IOException {
        databaseConnector = new DatabaseConnector();
    }


    @Override
    public List<Categories> getAllCategories() throws Exception {

        ArrayList<Categories> allCategories = new ArrayList<>();


        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM dbo.Category;";


            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Song object
                int id = rs.getInt("Id");
                String name = rs.getString("Name");

                Categories categories = new Categories(id, name);

                allCategories.add(categories);
            }

            return allCategories;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not get Songs from database", ex);
        }

    }

    @Override
    public Categories addCategories(String name) throws Exception {
        String sql = "INSERT INTO  Category (name) VALUES (?);";

        try (Connection conn = databaseConnector.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters

            stmt.setString(1, name);


            // Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from the DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            // Create Song object and send up the layers
            Categories categories = new Categories(id, name);

            return categories;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not create song", ex);
        }

    }

    @Override
    public void deleteCategories(Categories deleteCategories) throws Exception {
        try (Connection conn = databaseConnector.getConnection()) {

            String sql = "DELETE CatMovie FROM CatMovie inner join Category on Category.id =CatMovie.CategoryId " +
                    "WHERE CatMovie.CategoryId=? DELETE from Category WHERE Category.Id=?;";

            PreparedStatement stmt = conn.prepareStatement(sql);


            //Vi sletter fra vores song tabel. Men eftersom vi har foreign keys er vi nød til specificere dem. Derfor skrives inner join.
            //Her beskriver bindingen mellem child og parent tabellerne.
            // Til sidst beskrives hvilket parameter, her movie nummer, vi sletter efter. Nu vil movie og referencer i krydstabellen
            //blive slettet.

            //Bind parameters

            stmt.setInt(1, deleteCategories.getId());
            stmt.setInt(2, deleteCategories.getId());

            stmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not delete song", ex);
        }

    }
}