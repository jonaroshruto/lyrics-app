
package fx.frontend;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Jona
 */

public class Artist {

    //private static final AtomicInteger idGenerator = new AtomicInteger(5);
    private int artist_id;

    private String artistName;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }


    private List<Song> songs;

    public Artist() {
    }

    public Artist(int artist_id,String artistName) {
        this.artist_id = artist_id;
        this.artistName = artistName;
        
    }

    public void setId(int id) {
        this.artist_id = id;
    }

    public int getId() {
        return artist_id;
    }
}
