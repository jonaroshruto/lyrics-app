package lyrics.app;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
@Table(name = "songs")
public class Song {
    private static final AtomicInteger idGenerator = new AtomicInteger(10);
    @Id
    private int song_id;

    private String songTitle;

    private String lyrics;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "ft_artist_id")
    @Nullable
    private Artist ftArtist;

    public Song() {

    }

    public Song(int song_id, String songTitle, String lyrics, Artist artist, @Nullable Artist ftArtist) {
        this.song_id = idGenerator.getAndIncrement();
        this.songTitle = songTitle;
        this.lyrics = lyrics;
        this.artist = artist;
        this.ftArtist = ftArtist;
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

    @Nullable
    public Artist getFtArtist() {
        return ftArtist;
    }

    public void setFtArtist(@Nullable Artist ftArtist) {
        this.ftArtist = ftArtist;
    }
}
