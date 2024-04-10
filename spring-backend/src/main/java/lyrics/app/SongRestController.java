package lyrics.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class SongRestController {


    @Autowired SongRepository songRepository;

    //public SongRestController(SongRepository songRepository) {
       // this.songRepository = songRepository;
    //}

    @PostMapping("/songs/")
    public ResponseEntity<Song> createSong(@RequestBody Song song){
        System.out.print(song.getSongTitle());
        try {
//            Song newSong = new Song();
//            newSong.setId(song.getSong_id());
//            newSong.setSongTitle(song.getSongTitle());
//            newSong.setLyrics(song.getLyrics());
//            newSong.setArtist(song.getArtist());
//            if (song.getFtArtist() == null){
//                newSong.setFtArtist(null);
//            }else
//                newSong.setFtArtist(song.getFtArtist());
            songRepository.save(song);
            
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/songs/{id}")
    public ResponseEntity<Optional<Song>> getSong(@RequestParam (required = false) @PathVariable Long id){
        try {
            Optional<Song> song;
            song = songRepository.findById(id);
            return ResponseEntity.ok(song);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getAllSongs(@RequestParam (required = false) String songTitle){
        try {
            List<Song> songs = new ArrayList<>();
            if(songTitle == null){
                songRepository.findAll().forEach(songs::add);

            }else {
                songs.addAll(songRepository.findBySongTitleContaining(songTitle));
            }
            if(songs.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(songs,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/songs/{id}")
    public ResponseEntity<HttpStatus> deleteSong(@PathVariable("id") Long id){
        try {
            songRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/songs/")
    public ResponseEntity<HttpStatus> deleteAllSongs(){
        try {
            songRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id,@RequestBody Song song){
        Optional<Song> songOpt = songRepository.findById(id);

        if(songOpt.isPresent()){
            Song song1 = songOpt.get();
            song1.setSongTitle(song.getSongTitle());
            song1.setLyrics(song.getLyrics());
            songRepository.save(song1);
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/songs/artist/{id}")
    public ResponseEntity<List<Song>> findByArtist(@PathVariable Long id){
        try {
                List<Song> songs = songRepository.findSongByArtist_id(id);
                return new ResponseEntity<>(songs,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
