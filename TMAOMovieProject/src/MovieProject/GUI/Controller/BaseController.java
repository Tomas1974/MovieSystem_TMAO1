package MovieProject.GUI.Controller;

import MovieProject.GUI.Model.CategoriesModel;
import MovieProject.GUI.Model.MovieModel;

public abstract class BaseController {

        private MovieModel model;
        public CategoriesModel categoriesModel;

        public void setModel(MovieModel model){this.model = model;}

        public MovieModel getModel(){
            return model;
        }
        
    public void setCategoriesModel(CategoriesModel categoriesModel) {
        this.categoriesModel = categoriesModel;
    }

    public CategoriesModel getCategoriesModel() {
        return categoriesModel;
    }

    public abstract void setup();

    }
