package ua.antibyte.apimediapalyer.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongResponseDto {
    private Long id;
    private String name;
    private String singer;
    private String squad;
    private String genre;
    private String country;
    private String year;
    private String originName;
    private String type;
    private int size;
    private String url;
}
