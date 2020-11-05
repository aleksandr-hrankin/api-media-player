package ua.antibyte.apimediapalyer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.antibyte.apimediapalyer.entity.Song;
import ua.antibyte.apimediapalyer.repository.SongRepository;
import ua.antibyte.apimediapalyer.service.SongService;
import java.io.IOException;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song save(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Song song = new Song(fileName, file.getContentType(), file.getBytes());
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
