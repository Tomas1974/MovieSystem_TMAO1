package MovieProject.BLL;

import MovieProject.BE.Categories;
import MovieProject.DAL.ICategoriesDAO;
import MovieProject.DAL.db.CategoriesDAO_DB;

import java.util.List;

public class CategoriesManager {
    public ICategoriesDAO categoriesDAO;
    public CategoriesManager() throws Exception {
        categoriesDAO = new CategoriesDAO_DB();
    }
    public List<Categories> getAllCategories() throws Exception {
        return categoriesDAO.getAllCategories();
    }
    public Categories addCategories(String name) throws Exception {
        return categoriesDAO.addCategories(name);
    }
    public void deletedCategories(Categories deletedCategories) throws Exception {
        categoriesDAO.deleteCategories(deletedCategories);
    }
}
