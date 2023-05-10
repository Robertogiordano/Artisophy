package dao;

import java.util.Objects;

public class Artist implements ArtistInterface {
    private Integer id;

    private String nameArtist;
    private String name;
    private String surname;
    private Integer birthYear;
    private Integer deathYear;
    private String description;
    private String wiki;

    public Artist(Integer id, String nameArtist, String name, String surname, Integer birthYear, Integer deathYear, String description, String wiki) {
        this.id = id;
        this.nameArtist = nameArtist;
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.description = description;
        this.wiki = wiki;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getBirthYear() {
        return birthYear;
    }

    @Override
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public Integer getDeathYear() {
        return deathYear;
    }

    @Override
    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
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
        return "Artist{" +
                "id=" + id +
                ", nameArtist='" + nameArtist + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                ", description='" + description + '\'' +
                ", wiki='" + wiki + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id) && Objects.equals(nameArtist, artist.nameArtist) && Objects.equals(name, artist.name) && Objects.equals(surname, artist.surname) && Objects.equals(birthYear, artist.birthYear) && Objects.equals(deathYear, artist.deathYear) && Objects.equals(description, artist.description) && Objects.equals(wiki, artist.wiki);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameArtist, name, surname, birthYear, deathYear, description, wiki);
    }

    @Override
    public int compareTo(ArtElement o) {
        if (this == o) return 0;
        if (!(o instanceof Artist)) throw new RuntimeException();
        Artist artist = (Artist) o;
        return getNameArtist().compareTo(artist.getNameArtist());
    }
}
