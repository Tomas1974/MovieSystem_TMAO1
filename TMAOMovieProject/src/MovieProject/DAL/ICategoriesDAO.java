package MovieProject.DAL;

import MovieProject.BE.Categories;

import java.util.List;

public interface ICategoriesDAO {

    public List<Categories> getAllCategories() throws Exception;
    public Categories addCategories(String name) throws Exception;
    public void deleteCategories(Categories deleteCategories) throws Exception;
}
