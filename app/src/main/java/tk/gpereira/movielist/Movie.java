package tk.gpereira.movielist;

public class Movie {
    private String poster;
    private String description;
    private String title;
    private String rating;
    private String date;

    public Movie() {
        this.title = "";
        this.description = "";
        this.poster = "";
        this.date = "";
        this.rating = "";
    }

    String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    String getPoster() {
        return poster;
    }

    String getDate() {
        return date;
    }

    String getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
