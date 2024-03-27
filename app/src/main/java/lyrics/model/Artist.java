
package lyrics.model;

import jakarta.persistence.*;


import java.util.List;

/**
 *
 * @author Jona
 */
@Entity(name = "artists")
public class Artist {

    @Id
    private Long id;

    private String artistName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "song_id")
    @JoinColumn(name = "songs")
    private List<Song> songs;

    public Artist() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
