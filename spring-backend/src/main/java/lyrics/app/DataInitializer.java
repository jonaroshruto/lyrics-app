/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lyrics.app;

import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jona
 */
@Component
public class DataInitializer implements CommandLineRunner{
    
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create and save artists
        Artist artist1 = new Artist(3579001, "Sauti Sol");
        Artist artist2 = new Artist(3579002, "Ben Soul");
        Artist artist3 = new Artist(3579003, "Bien");
        Artist artist4 = new Artist(3579004, "Nyash");
        Artist artist5 = new Artist(3579005, "Khaligragh");
        Artist artist6 = new Artist(3579006, "21");

        List<Artist> artists = Arrays.asList(artist1, artist2, artist3, artist4, artist5, artist6);
        artistRepository.saveAll(artists);

        // Create and save songs
        Song song1 = new Song(1, "Navutishwa", "Navutishwa, oh, navutishwa\nMoshi pia napuliziwa\nNavutishwa, oh, navutishwa\nNaishi kama paka, nalishwa\nNavutishwa, oh, navutishwa\nHaya mapenzi sijalipishwa\nNavutishwa, oh, navutishwa mama\nNavutishwa, oh, navutishwa mama", artist2, artist3);
        Song song2 = new Song(2, "Inauma", "Kukosana na wewe, sikutarajia Kuwa mbali na wewe, aki umeniacha pabaya\nKutengana na wewe, imenibadilisha sana\nNimetamani nilewe, aki nakunywa nasazamana", artist3, null);
        Song song3 = new Song(3, "Lifestyle", "?heki dame Haga bigi imeyaa kwa dera", artist3, null);

        List<Song> songs = Arrays.asList(song1, song2, song3);
        songRepository.saveAll(songs);
    }
}
