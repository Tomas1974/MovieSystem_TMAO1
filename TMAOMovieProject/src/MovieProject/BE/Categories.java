package MovieProject.BE;

public class Categories {

    private int id;
    private String Categories;

    public Categories(int id, String name) {
        this.id = id;
        Categories = name;
    }
    public int getId() {
        return id;
    }

    public String getCategories() {
        return Categories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", Name='" + Categories + '\'' +
                '}';
    }
}
