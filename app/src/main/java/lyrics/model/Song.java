package lyrics.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "songs") public class Song {
    @Id
    private Long song_id;

    private String songTitle;

    private String lyrics;

    @ManyToOne private Artist artist;

    @ManyToOne
    @Nullable
    private Artist ftArtist;

    public Song() {

    }


    public void setId(Long song_id) {
        this.song_id = song_id;
    }

    public Long getSong_id() {
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
}
