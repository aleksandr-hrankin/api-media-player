package ua.antibyte.apimediapalyer.service.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.antibyte.apimediapalyer.dto.response.SongResponseDto;
import ua.antibyte.apimediapalyer.entity.Song;

@Component
public class SongResponseMapper {
    public SongResponseDto mapSongToResponseDto(Song song) {
        String name = song.getName().isEmpty() ? song.getOriginName() : song.getName();
        String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/songs/")
                .path(String.valueOf(song.getId()))
                .toUriString();

        return SongResponseDto.builder()
                .id(song.getId())
                .name(name)
                .singer(song.getSinger())
                .squad(song.getSquad())
                .genre(song.getGenre())
                .country(song.getCountry())
                .year(song.getYear())
                .type(song.getType())
                .size(song.getFile().length)
                .url(url)
                .build();
    }
}
