package lyrics.repository;

import lyrics.model.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Long> {

}
