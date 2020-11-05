package ua.antibyte.apimediapalyer.dto;

import lombok.Data;

@Data
public class SongResponseDto {
    private Long id;
    private String name;
    private String type;
    private int size;
    private String url;
}
