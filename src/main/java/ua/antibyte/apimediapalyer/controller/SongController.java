package ua.antibyte.apimediapalyer.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.antibyte.apimediapalyer.dto.request.SongRequestDto;
import ua.antibyte.apimediapalyer.dto.response.MessageResponseDto;
import ua.antibyte.apimediapalyer.dto.response.SongResponseDto;
import ua.antibyte.apimediapalyer.entity.Song;
import ua.antibyte.apimediapalyer.service.SongService;
import ua.antibyte.apimediapalyer.service.mapper.SongMapper;
import ua.antibyte.apimediapalyer.service.mapper.SongResponseMapper;

@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final SongMapper songMapper;
    private final SongResponseMapper songResponseMapper;

    public SongController(SongService songService,
                          SongMapper songMapper,
                          SongResponseMapper songResponseMapper) {
        this.songService = songService;
        this.songMapper = songMapper;
        this.songResponseMapper = songResponseMapper;
    }

    @GetMapping
    public ResponseEntity<List<SongResponseDto>> getAll() {
        List<SongResponseDto> songResponseDtos = songService.findAll().stream()
                .map(songResponseMapper::mapSongToResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(songResponseDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> findById(@PathVariable("id") Long id) {
        Song song = songService.findById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + song.getName() + "\"")
                .body(song.getFile());
    }

    @PostMapping
    public ResponseEntity<MessageResponseDto> upload(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("name") String name,
                                                     @RequestParam("singer") String singer,
                                                     @RequestParam("squad") String group,
                                                     @RequestParam("genre") String genre,
                                                     @RequestParam("country") String country,
                                                     @RequestParam("year") String year) {
        SongRequestDto songRequestDto = SongRequestDto.builder()
                .file(file)
                .name(name)
                .singer(singer)
                .squad(group)
                .gender(genre)
                .country(country)
                .year(year)
                .build();
        String responseMessage = "";
        try {
            Song song = songMapper.mapSongRequestDtoToSong(songRequestDto);
            songService.save(song);
            responseMessage = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new MessageResponseDto(responseMessage));
        } catch (Exception e) {
            responseMessage = "Could not upload the file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new MessageResponseDto(responseMessage));
        }
    }
}
