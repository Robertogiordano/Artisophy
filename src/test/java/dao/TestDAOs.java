/*package dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDAOs {
    Artist artist;
    Museum museum;
    Artwork artwork;

    @Before
    public void initialize(){
        this.artist=new Artist(1,"Pablo Picasso",18811025,1973048,"Pablo Ruiz y Picasso, simply known as Pablo Picasso, was a Spanish painter, sculptor and lithographer, among the most influential of the 20th century","https://en.wikipedia.org/wiki/Pablo_Picasso");
        this.museum=new Museum(1,"Museum of Modern Art","11 West 53rd Street", "10:30","17:30","123456789","The Museum of Modern Art (MoMA) is an art museum located in Midtown Manhattan, New York City, on 53rd Street between Fifth and Sixth Avenues.",50.50,"www.moma.org","https://en.wikipedia.org/wiki/Museum_of_Modern_Art","https://www.google.com/maps/place/Museum+of+Modern+Art/@40.7614367,-73.9798156,17z/data=!3m2!4b1!5s0x89c258fbd5ec7547:0x7edf0a3ade84ad50!4m6!3m5!1s0x89c258f97bdb102b:0xea9f8fc0b3ffff55!8m2!3d40.7614327!4d-73.9776216!16zL20vMGhoams");
        this.artwork=new Artwork(1,"img1","La persistencia de la memoria",1931,1,1,"The Persistence of Memory (Catalan: La persistència de la memòria) is a 1931 painting by artist Salvador Dalí and one of the most recognizable works of Surrealism. First shown at the Julien Levy Gallery in 1932, since 1934 the painting has been in the collection of the Museum of Modern Art (MoMA) in New York City, which received it from an anonymous donor.","https://en.wikipedia.org/wiki/The_Persistence_of_Memory");
    }

    @Test
    public void printArtistTest(){
        System.out.println(artist);
    }

    @Test
    public void equalsArtistTest(){
        Artist testArtist1=new Artist(2,"Pablo Picasso",18811025,1973048,"Pablo Ruiz y Picasso, simply known as Pablo Picasso, was a Spanish painter, sculptor and lithographer, among the most influential of the 20th century","https://en.wikipedia.org/wiki/Pablo_Picasso");
        Assert.assertEquals(artist,testArtist1);

        Artist testArtist2=new Artist(1,"Pablito Picasso",18811025,1973048,"Pablo Ruiz y Picasso, simply known as Pablo Picasso, was a Spanish painter, sculptor and lithographer, among the most influential of the 20th century","https://en.wikipedia.org/wiki/Pablo_Picasso");
        Assert.assertNotEquals(artist,testArtist2);
    }

    @Test
    public void compareArtistTest(){
        Artist testArtist2=new Artist(2,"Salvador Dalí",19040511,19890123,"Salvador Dalí, Marquis of Pùbol, born Salvador Domingo Felipe Jacinto Dalí i Domènech, was a Spanish painter, sculptor, writer, photographer, filmmaker, designer, screenwriter and mystic","https://en.wikipedia.org/wiki/Salvador_Dal%C3%AD");
        Assert.assertEquals(artist.compareTo(testArtist2),-1);
    }

    @Test
    public void printMuseumTest(){
        System.out.println(museum);
    }

    @Test
    public void equalsMuseumTest(){
        Museum testArtist1=new Museum(1,"Museum of Modern Art","11 West 53rd Street", "10:30","17:30","123456789","The Museum of Modern Art (MoMA) is an art museum located in Midtown Manhattan, New York City, on 53rd Street between Fifth and Sixth Avenues.",50.50,"www.moma.org","https://en.wikipedia.org/wiki/Museum_of_Modern_Art","https://www.google.com/maps/place/Museum+of+Modern+Art/@40.7614367,-73.9798156,17z/data=!3m2!4b1!5s0x89c258fbd5ec7547:0x7edf0a3ade84ad50!4m6!3m5!1s0x89c258f97bdb102b:0xea9f8fc0b3ffff55!8m2!3d40.7614327!4d-73.9776216!16zL20vMGhoams");;
        Assert.assertEquals(museum,testArtist1);

        Artist testArtist2=new Artist(1,"Pablito Picasso",18811025,1973048,"Pablo Ruiz y Picasso, simply known as Pablo Picasso, was a Spanish painter, sculptor and lithographer, among the most influential of the 20th century","https://en.wikipedia.org/wiki/Pablo_Picasso");
        Assert.assertNotEquals(museum,testArtist2);
    }

    @Test
    public void compareMuseumTest(){
        Museum testMuseum2=new Museum(2,"Museum of Modern Art","11 West 53rd Street", "10:30","17:30","123456789","The Museum of Modern Art (MoMA) is an art museum located in Midtown Manhattan, New York City, on 53rd Street between Fifth and Sixth Avenues.",50.50,"www.moma.org","https://en.wikipedia.org/wiki/Museum_of_Modern_Art","https://www.google.com/maps/place/Museum+of+Modern+Art/@40.7614367,-73.9798156,17z/data=!3m2!4b1!5s0x89c258fbd5ec7547:0x7edf0a3ade84ad50!4m6!3m5!1s0x89c258f97bdb102b:0xea9f8fc0b3ffff55!8m2!3d40.7614327!4d-73.9776216!16zL20vMGhoams");;
        Assert.assertEquals(museum.compareTo(testMuseum2),-1);
    }

    @Test
    public void printArtworkTest(){
        System.out.println(artwork);
    }

    @Test
    public void equalsArtworkTest(){
        Artwork testArtwork1=new Artwork(1,"img1","La persistencia de la memoria",1931,1,1,"The Persistence of Memory (Catalan: La persistència de la memòria) is a 1931 painting by artist Salvador Dalí and one of the most recognizable works of Surrealism. First shown at the Julien Levy Gallery in 1932, since 1934 the painting has been in the collection of the Museum of Modern Art (MoMA) in New York City, which received it from an anonymous donor.","https://en.wikipedia.org/wiki/The_Persistence_of_Memory");;
        Assert.assertEquals(artwork,testArtwork1);

        Artwork testArtwork2=new Artwork(1,"img2","La persistencia de la memoria",1931,1,1,"The Persistence of Memory (Catalan: La persistència de la memòria) is a 1931 painting by artist Salvador Dalí and one of the most recognizable works of Surrealism. First shown at the Julien Levy Gallery in 1932, since 1934 the painting has been in the collection of the Museum of Modern Art (MoMA) in New York City, which received it from an anonymous donor.","https://en.wikipedia.org/wiki/The_Persistence_of_Memory");
        Assert.assertNotEquals(artwork,testArtwork2);
    }

    @Test
    public void compareArtworkTest(){
        Artwork testArtwork2=new Artwork(2,"img1","La persistencia de la memoria",1931,1,1,"The Persistence of Memory (Catalan: La persistència de la memòria) is a 1931 painting by artist Salvador Dalí and one of the most recognizable works of Surrealism. First shown at the Julien Levy Gallery in 1932, since 1934 the painting has been in the collection of the Museum of Modern Art (MoMA) in New York City, which received it from an anonymous donor.","https://en.wikipedia.org/wiki/The_Persistence_of_Memory");;
        Assert.assertEquals(artwork.compareTo(testArtwork2),-1);
    }
}*/
