
package lyrics.app;

import jakarta.persistence.*;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Jona
 */
@Entity
@Table(name = "artists")
public class Artist {
    private static final AtomicInteger idGenerator = new AtomicInteger(372000);
    @Id
    private int artist_id;

    private String artistName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "song_id")
    private List<Song> songs;

    public Artist() {
    }

    public Artist(int artist_id, String artistName) {
        this.artist_id = idGenerator.getAndIncrement();
        this.artistName = artistName;
        
    }

    public void setId(int id) {
        this.artist_id = id;
    }

    public int getId() {
        return artist_id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
