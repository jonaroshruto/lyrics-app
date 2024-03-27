package lyrics.controller;

import lyrics.model.Song;
import lyrics.repository.SongRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongController {

    SongRepository songRepository;

    public void createSong(@RequestBody Song song){
        songRepository.save(song);

    }
    public Iterable<Song> findAll(){
        return songRepository.findAll();
    }

    

}
