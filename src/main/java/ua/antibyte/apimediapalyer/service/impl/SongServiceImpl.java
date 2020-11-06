package ua.antibyte.apimediapalyer.service.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import ua.antibyte.apimediapalyer.entity.Song;
import ua.antibyte.apimediapalyer.repository.SongRepository;
import ua.antibyte.apimediapalyer.service.SongService;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song save(Song song) throws IOException {
        return songRepository.save(song);
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).get();
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }
}
