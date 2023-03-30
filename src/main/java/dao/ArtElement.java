package dao;

public interface ArtElement extends Comparable<ArtElement> {
    public String toString();
    public boolean equals(Object o);
    public int hashCode();
    public Integer getId();
    public void setId(Integer id);

}
