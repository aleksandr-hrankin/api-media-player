package ua.antibyte.apimediapalyer.service;

import java.io.IOException;
import java.util.List;
import ua.antibyte.apimediapalyer.entity.Song;

public interface SongService {
    Song save(Song song) throws IOException;

    Song findById(Long id);

    List<Song> findAll();
}
