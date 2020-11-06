package ua.antibyte.apimediapalyer.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class SongRequestDto {
    private MultipartFile file;
    private String name;
    private String singer;
    private String squad;
    private String gender;
    private String country;
    private String year;
}
