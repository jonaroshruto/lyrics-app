CREATE TABLE artists(
                        artist_id BIGINT NOT NULL PRIMARY KEY ,
                        artist_name VARCHAR

);
CREATE TABLE songs(
                      song_id BIGINT NOT NULL primary key,
                      song_title varchar(50),
                      song_lyrics varchar,
                      artist_id varchar(50) NOT NULL ,
                      ft_artist_id varchar(50)

);
ALTER TABLE songs ADD CONSTRAINT fk_song_artist FOREIGN KEY (artist_id) REFERENCES artists (artist_id);