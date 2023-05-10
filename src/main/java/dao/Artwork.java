package dao;

import java.util.Objects;

public class Artwork implements ArtworkInterface {
    private Integer id;
    private String imgPath;
    private String name;
    private Integer year;
    private Integer author_id;
    private Integer museum_id;
    private String description;
    private String wiki;


    public Artwork(Integer id, String imgPath, String name, Integer year, Integer author_id, Integer museum_id, String description, String wiki) {
        this.id = id;
        this.imgPath = imgPath;
        this.name = name;
        this.year = year;
        this.author_id = author_id;
        this.museum_id = museum_id;
        this.description = description;
        this.wiki = wiki;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getImgPath() {
        return imgPath;
    }

    @Override
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getYear() {
        return year;
    }

    @Override
    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public Integer getAuthor_id() {
        return author_id;
    }

    @Override
    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    @Override
    public Integer getMuseum_id() {
        return museum_id;
    }

    @Override
    public void setMuseum_id(Integer museum_id) {
        this.museum_id = museum_id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getWiki() {
        return wiki;
    }

    @Override
    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    @Override
    public String toString() {
        return "Artwork{" +
                "id=" + id +
                ", imgPath='" + imgPath + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", autor_id=" + author_id +
                ", museum_id=" + museum_id +
                ", description='" + description + '\'' +
                ", wiki='" + wiki + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artwork)) return false;
        Artwork artwork = (Artwork) o;
        return Objects.equals(imgPath, artwork.imgPath) && Objects.equals(name, artwork.name) && Objects.equals(year, artwork.year) && Objects.equals(author_id, artwork.author_id) && Objects.equals(museum_id, artwork.museum_id) && Objects.equals(description, artwork.description) && Objects.equals(wiki, artwork.wiki);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgPath, name, year, author_id, museum_id, description, wiki);
    }

    @Override
    public int compareTo(ArtElement o) {
        if (this == o) return 0;
        if (!(o instanceof Artwork)) throw new RuntimeException();
        Artwork artwork = (Artwork) o;
        return getName().compareTo(artwork.getName());
    }
}
