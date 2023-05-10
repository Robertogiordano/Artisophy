package com.example.artisophy;

import com.example.artisophy.dao.elements.Artist;
import com.example.artisophy.dao.elements.ArtistInterface;
import com.example.artisophy.dao.elements.Artwork;
import com.example.artisophy.dao.elements.ArtworkInterface;
import com.example.artisophy.dao.elements.Museum;
import com.example.artisophy.dao.elements.MuseumInterface;
import com.example.artisophy.dao.elements.User;

import java.util.List;

public class Cache {
    private User user;
    private Artist currentArtist;
    private Artwork currentArtwork;
    private Museum currentMuseum;

    private List<ArtistInterface> artists;
    private List<ArtworkInterface> artworks;
    private List<MuseumInterface> museums;

    public static Cache instance;

    private Cache(){}

    public static Cache getInstance(){
        if(instance==null){
            instance=new Cache();
        }

        return instance;
    }

    public static void closeInstance(){
        instance=null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artist getCurrentArtist() {
        return currentArtist;
    }

    public void setCurrentArtist(Artist currentArtist) {
        this.currentArtist = currentArtist;
    }

    public Artwork getCurrentArtwork() {
        return currentArtwork;
    }

    public void setCurrentArtwork(Artwork currentArtwork) {
        this.currentArtwork = currentArtwork;
    }

    public Museum getCurrentMuseum() {
        return currentMuseum;
    }

    public void setCurrentMuseum(Museum currentMuseum) {
        this.currentMuseum = currentMuseum;
    }

    public List<ArtistInterface> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistInterface> artists) {
        this.artists = artists;
    }

    public List<ArtworkInterface> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtworkInterface> artworks) {
        this.artworks = artworks;
    }

    public List<MuseumInterface> getMuseums() {
        return museums;
    }

    public void setMuseums(List<MuseumInterface> museums) {
        this.museums = museums;
    }
}
