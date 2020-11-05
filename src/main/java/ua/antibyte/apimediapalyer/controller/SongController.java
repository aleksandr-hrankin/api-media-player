package ua.antibyte.apimediapalyer.controller;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.antibyte.apimediapalyer.dto.ResponseMessageDto;
import ua.antibyte.apimediapalyer.dto.SongResponseDto;
import ua.antibyte.apimediapalyer.entity.Song;
import ua.antibyte.apimediapalyer.service.SongService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<List<SongResponseDto>> getAll() {
        List<SongResponseDto> songResponseDtos = songService.findAll().stream()
                .map(song -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/songs/")
                            .path(String.valueOf(song.getId()))
                            .toUriString();
                    SongResponseDto responseDto = new SongResponseDto();
                    responseDto.setId(song.getId());
                    responseDto.setName(song.getName());
                    responseDto.setType(song.getType());
                    responseDto.setSize(song.getData().length);
                    responseDto.setUrl(fileDownloadUri);
                    return responseDto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(songResponseDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> findById(@PathVariable("id") Long id) {
        Song song = songService.findById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + song.getName() + "\"")
                .body(song.getData());
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDto> upload(@RequestParam("file") MultipartFile file) {
        String responseMessage = "";
        try {
            songService.save(file);
            responseMessage = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDto(responseMessage));
        } catch (Exception e) {
            responseMessage = "Could not upload the file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageDto(responseMessage));
        }
    }
}
