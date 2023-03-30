package dao;

import java.util.Objects;

public class Artist implements ArtElement{
    private Integer id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    private String description;

    private String wiki;

    public Artist(Integer id, String name, Integer birthYear, Integer deathYear, String description, String wiki) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.description = description;
        this.wiki = wiki;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Artist(String name, Integer birthYear, String description, String wiki) {
        this.name = name;
        this.birthYear = birthYear;
        this.description = description;
        this.wiki=wiki;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
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
        return "Artist: " +
                "id =" + id +
                ", name '=" + name + '\'' +
                ", birthYear =" + birthYear +
                ", deathYear =" + deathYear +
                ", description ='" + description + '\'' +
                ", wiki ='" + wiki + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) && Objects.equals(birthYear, artist.birthYear) && Objects.equals(deathYear, artist.deathYear) && Objects.equals(description, artist.description) && Objects.equals(wiki, artist.wiki);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthYear, deathYear, description, wiki);
    }

    public int compareTo(ArtElement o) {
        if (this == o) return 0;
        if (!(o instanceof Artist)) throw new RuntimeException();
        Artist artist = (Artist) o;
        return getName().compareTo(artist.getName());
    }
}
