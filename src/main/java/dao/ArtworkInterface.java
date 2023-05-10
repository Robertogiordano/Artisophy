package dao;

public interface ArtworkInterface extends ArtElement {
    public Integer getId();
    public void setId(Integer id);
    String getImgPath();

    void setImgPath(String imgPath);

    String getName();

    void setName(String name);

    Integer getYear();

    void setYear(Integer year);

    Integer getAuthor_id();

    void setAuthor_id(Integer author_id);

    Integer getMuseum_id();

    void setMuseum_id(Integer museum_id);

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

    @Override
    int compareTo(ArtElement o);
}
