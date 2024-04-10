package fx.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UIController implements Initializable {

    @FXML
    private ListView<Song> songListView;

    @FXML
    private TextField songTitleField;

    @FXML
    private TextArea lyricsTextArea;

    @FXML
    private TextField artistField;
    
    @FXML
    private TextField artistIDField;

    private ObservableList<Song> songs;

    private HttpClient httpClient;

    private ObjectMapper objectMapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        httpClient = HttpClients.createDefault();
        objectMapper = new ObjectMapper();
        loadSongsFromApi();
        initializeListView();
    }

    private void loadSongsFromApi() {
        HttpGet request = new HttpGet("http://localhost:8080/api/songs");

        try {
            HttpResponse response = httpClient.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            Song[] songArray = objectMapper.readValue(responseContent.toString(), Song[].class);
            List<Song> songList = Arrays.asList(songArray);
//            for (Song song : songList){
//                System.out.print(song.getSongTitle());
//            }
            songs = FXCollections.observableArrayList(songList);

        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    private void initializeListView() {
        songListView.setItems(songs);
        songListView.setCellFactory(param -> new ListCell<Song>() {
        @Override
        protected void updateItem(Song song, boolean empty) {
            super.updateItem(song, empty);

            if (empty || song == null || song.getSongTitle() == null) {
                setText(null);
            } else {
                setText(song.getSongTitle());
            }
        }
        });

        //listener to handle item selection
        songListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displaySongDetails(newValue);
            }
        });

    }

    private void displaySongDetails(Song song) {
        songTitleField.setText(song.getSongTitle());
        if (artistIDField != null) {
            String artistIDString = String.valueOf(song.getArtist().getId());
            artistIDField.setText(artistIDString);
        }
        artistField.setText(song.getArtist().getArtistName());
        lyricsTextArea.setText(song.getLyrics());
    }

    @FXML
    private void addNewSong() {
        String songTitle = songTitleField.getText();
        String lyrics = lyricsTextArea.getText();
        String artistName = artistField.getText();
        String artistId = artistIDField.getText();
        int artistID = Integer.parseInt(artistId);
        Artist artist = new Artist(artistID,artistName);

        Song newSong = new Song(songTitle, lyrics, artist);

        try {
            String jsonBody = "{\"song_id\":\"" + newSong.getSong_id()+ "\", \"songTitle\":\"" + newSong.getSongTitle()+ "\", \"lyrics\":\"" + newSong.getLyrics() + "\", \"artist\":{\"id\":\"" + newSong.getArtist().getId() + "\",\"artistName\":\"" + newSong.getArtist().getArtistName() + "\"}}";
            HttpPost request = new HttpPost("http://localhost:8080/api/songs/");
            StringEntity params = new StringEntity(jsonBody);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
            System.out.print(request.toString());

            // Reload songs after adding new one
            loadSongsFromApi();
            initializeListView();

        } catch (IOException e) {
            e.printStackTrace();
           

        }
    }

    @FXML
    private void updateSong() {
        Song selectedSong = songListView.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            String songTitle = songTitleField.getText();
            String lyrics = lyricsTextArea.getText();
            String artistName = artistField.getText();
            String artistID = artistIDField.getText();
            int Id = Integer.parseInt(artistID);
            Artist artist = new Artist(Id,artistName);

            selectedSong.setSongTitle(songTitle);
            selectedSong.setLyrics(lyrics);
            selectedSong.setArtist(artist);

            try {
                HttpPut request = new HttpPut("http://localhost:8080/api/songs/" + selectedSong.getSong_id());
                StringEntity params = new StringEntity(objectMapper.writeValueAsString(selectedSong));
                request.addHeader("content-type", "application/json");
                request.setEntity(params);
                httpClient.execute(request);
                loadSongsFromApi();
                System.out.print(request.toString());

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            // Show an alert dialog to inform the user to select a song for updating
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Song Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a song to update.");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteSong() {
        Song selectedSong = songListView.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            try {
                HttpDelete request = new HttpDelete("http://localhost:8080/api/songs/" + selectedSong.getSong_id());
                httpClient.execute(request);
                songs.remove(selectedSong);
                initializeListView();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Song Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a song to delete.");
            alert.showAndWait();
        }
    }
}
