package dao;

import java.util.Objects;

public class Artwork implements ArtElement{
    private Integer id;
    private String imgPath;
    private String name;
    private Integer year;
    private Integer autor_id;
    private Integer museum_id;
    private String description;
    private String wiki;

    public Artwork(Integer id, String imgPath, String name, Integer year, Integer autor_id, Integer museum_id, String description, String wiki) {
        this.id = id;
        this.imgPath = imgPath;
        this.name = name;
        this.year = year;
        this.autor_id = autor_id;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(Integer autor_id) {
        this.autor_id = autor_id;
    }

    public Integer getMuseum_id() {
        return museum_id;
    }

    public void setMuseum_id(Integer museum_id) {
        this.museum_id = museum_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWiki() {
        return wiki;
    }

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
                ", autor_id=" + autor_id +
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
        return Objects.equals(imgPath, artwork.imgPath) && Objects.equals(name, artwork.name) && Objects.equals(year, artwork.year) && Objects.equals(autor_id, artwork.autor_id) && Objects.equals(museum_id, artwork.museum_id) && Objects.equals(description, artwork.description) && Objects.equals(wiki, artwork.wiki);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgPath, name, year, autor_id, museum_id, description, wiki);
    }

    @Override
    public int compareTo(ArtElement o) {
        if (this == o) return 0;
        if (!(o instanceof Artwork)) throw new RuntimeException();
        Artwork artwork = (Artwork) o;
        return getName().compareTo(artwork.getName());
    }
}
