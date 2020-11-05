package ua.antibyte.apimediapalyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.antibyte.apimediapalyer.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
