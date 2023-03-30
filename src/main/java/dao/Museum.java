package dao;

import java.util.Objects;

public class Museum implements ArtElement{
    private Integer id;
    private String name;
    private String street;
    private String openingHour;
    private String closingHour;
    private String phone;
    private String description;
    private Double price;
    private String webpageUrl;
    private String wiki;
    private String googleMaps;


    public Museum(Integer id, String name, String street, String openingHour, String closingHour, String phone, String description, Double price, String webpageUrl, String wiki, String googleMaps) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.phone = phone;
        this.description = description;
        this.price = price;
        this.webpageUrl = webpageUrl;
        this.wiki = wiki;
        this.googleMaps = googleMaps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(String openingHour) {
        this.openingHour = openingHour;
    }

    public String getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(String closingHour) {
        this.closingHour = closingHour;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getWebpageUrl() {
        return webpageUrl;
    }

    public void setWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getGoogleMaps() {
        return googleMaps;
    }

    public void setGoogleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
    }

    @Override
    public String toString() {
        return "Museum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", openingHour=" + openingHour +
                ", closingHour=" + closingHour +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", webpageUrl='" + webpageUrl + '\'' +
                ", wiki='" + wiki + '\'' +
                ", googleMaps='" + googleMaps + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Museum)) return false;
        Museum museum = (Museum) o;
        return Objects.equals(name, museum.name) && Objects.equals(street, museum.street) && Objects.equals(openingHour, museum.openingHour) && Objects.equals(closingHour, museum.closingHour) && Objects.equals(phone, museum.phone) && Objects.equals(description, museum.description) && Objects.equals(price, museum.price) && Objects.equals(webpageUrl, museum.webpageUrl) && Objects.equals(wiki, museum.wiki) && Objects.equals(googleMaps, museum.googleMaps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, street, openingHour, closingHour, phone, description, price, webpageUrl, wiki, googleMaps);
    }

    @Override
    public int compareTo(ArtElement o) {
        if (this == o) return 0;
        if (!(o instanceof Museum)) throw new RuntimeException();
        Museum museum = (Museum) o;
        return getName().compareTo(museum.getName());
    }
}
