package lyrics.app;

import lyrics.app.Song;
import lyrics.app.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/")
    public String getAllSongs(Model model) {
        Iterable<Song> songs = songRepository.findAll();
        List<Song> songList = new ArrayList<>();
        songs.forEach(songList::add);
        model.addAttribute("songs", songList);
        return "songs"; // This will map to songs.html template
    }

    @GetMapping("/{id}")
    public String getSong(@PathVariable Long id, Model model) {
        Optional<Song> song = songRepository.findById(id);
        song.ifPresent(value -> model.addAttribute("song", value));
        return "song"; // This will map to song.html template
    }

    @GetMapping("/artist/{id}")
    public String findByArtist(@PathVariable Long id, Model model) {
        Iterable<Song> songs = songRepository.findSongByArtist_id(id);
        List<Song> songList = new ArrayList<>();
        songs.forEach(songList::add);
        model.addAttribute("songs", songList);
        return "songs";
    }


}
