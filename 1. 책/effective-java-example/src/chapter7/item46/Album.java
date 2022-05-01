package chapter7.item46;

public class Album {
    private Artist artist;
    private int sales;

    public Album(Artist artist, int sales) {
        this.artist = artist;
        this.sales = sales;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return "Album{" +
                "artist=" + artist +
                ", sales=" + sales +
                '}';
    }
}
