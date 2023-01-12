package MovieProject.BE;

import java.time.LocalDate;

public class Movie {
    private int id;
    private String name;
    private String description;
    private double rating,imdb;
    private String movieFile,imageFile;
    private LocalDate lastview;
    private boolean toOld;
    public Movie(int id, String name, String description, double rating, double imdb, String movieFile, String imageFile, LocalDate lastview, boolean toOld) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.imdb = imdb;
        this.movieFile = movieFile;
        this.imageFile = imageFile;
        this.lastview = lastview;
        this.toOld = toOld;
    }
    public String getImageFile() {
        return imageFile;
    }
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
    public double getImdb() {
        return imdb;
    }
    public void setImdb(double imdb) {
        this.imdb = imdb;
    }
    public boolean getToOld() {
        return toOld;
    }
    public void setToOld(boolean toOld) {
        this.toOld = toOld;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getMovieFile() {
        return movieFile;
    }
    public void setMovieFile(String movieFile) {
        this.movieFile = movieFile;
    }
    public LocalDate getLastview() {
        return lastview;
    }
    public void setLastview(LocalDate lastview) {
        this.lastview = lastview;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "Movie{" +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", imdb=" + imdb +
                ", toOld='" + toOld + '\'' +
                '}';
    }


}