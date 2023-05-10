package com.example.artisophy.dao.elements;

public interface ArtistInterface extends ArtElement {

    public String getNameArtist();

    public void setNameArtist(String nameArtist);

    public String getSurname();

    public void setSurname(String surname);
    public Integer getId();
    public void setId(Integer id);
    String getName();

    void setName(String name);

    Integer getBirthYear();

    void setBirthYear(Integer birthYear);

    Integer getDeathYear();

    void setDeathYear(Integer deathYear);

    String getDescription();

    void setDescription(String description);

    String getWiki();

    void setWiki(String wiki);

    @Override
    String toString();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    int compareTo(ArtElement o);
}
