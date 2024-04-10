package lyrics.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song,Long> {
    List<Song> findBySongTitleContaining(String songTitle);

    List<Song> findSongByArtist_id(Long artist_id);
}
