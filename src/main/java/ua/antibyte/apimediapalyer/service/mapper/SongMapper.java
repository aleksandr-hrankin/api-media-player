package ua.antibyte.apimediapalyer.service.mapper;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.antibyte.apimediapalyer.dto.request.SongRequestDto;
import ua.antibyte.apimediapalyer.entity.Song;

@Component
public class SongMapper {
    public Song mapSongRequestDtoToSong(SongRequestDto songRequestDto) throws IOException {
        Song song = new Song();
        song.setFile(songRequestDto.getFile().getBytes());
        song.setName(songRequestDto.getName());
        song.setSinger(songRequestDto.getSinger());
        song.setSquad(songRequestDto.getSquad());
        song.setGenre(songRequestDto.getGender());
        song.setCountry(songRequestDto.getCountry());
        song.setYear(songRequestDto.getYear());

        String originName = StringUtils.cleanPath(songRequestDto.getFile().getOriginalFilename());
        song.setOriginName(originName);
        song.setType(songRequestDto.getFile().getContentType());
        return song;
    }
}
