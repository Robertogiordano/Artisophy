package dao;

public interface MuseumInterface extends ArtElement {
    public Integer getId();
    public void setId(Integer id);
    String getName();

    void setName(String name);

    String getStreet();

    void setStreet(String street);

    String getOpeningHour();

    void setOpeningHour(String openingHour);

    String getClosingHour();

    void setClosingHour(String closingHour);

    String getPhone();

    void setPhone(String phone);

    String getDescription();

    void setDescription(String description);

    Double getPrice();

    void setPrice(Double price);

    String getWebpageUrl();

    void setWebpageUrl(String webpageUrl);

    String getWiki();

    void setWiki(String wiki);

    String getGoogleMaps();

    void setGoogleMaps(String googleMaps);

    @Override
    String toString();
}
