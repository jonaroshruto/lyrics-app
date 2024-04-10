package fx.frontend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
    //private static final AtomicInteger idGenerator = new AtomicInteger(2500);
    private int song_id;

    private String songTitle;

    private String lyrics;


    private Artist artist;

    @Nullable
    private Artist ftArtist;

    public Song() {

    }

    public Song( int song_id,String songTitle, String lyrics, Artist artist) {
        this.song_id = song_id;
        this.songTitle = songTitle;
        this.lyrics = lyrics;
        this.artist = artist;
        //this.ftArtist = ftArtist;
    }
     public Song( String songTitle, String lyrics, Artist artist) {
        this.song_id = song_id;
        this.songTitle = songTitle;
        this.lyrics = lyrics;
        this.artist = artist;
        //this.ftArtist = ftArtist;
    }

    public void setId(int song_id) {
        this.song_id = song_id;
    }

    public int getSong_id() {
        return song_id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Artist getFtArtist() {
        return ftArtist;
    }

    public void setFtArtist(Artist ftArtist) {
        this.ftArtist = ftArtist;
    }
    
}
