package ua.antibyte.apimediapalyer.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import ua.antibyte.apimediapalyer.entity.Song;

public interface SongService {
    Song save(MultipartFile file) throws IOException;

    Song findById(Long id);

    List<Song> findAll();
}
