package ru.perfumess.mappers;

import org.mapstruct.Mapper;
import ru.perfumess.dto.PhotoDto;
import ru.perfumess.model.Photo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    PhotoDto toDto(Photo photo);

    Photo toPhoto(PhotoDto photoDto);

    List<PhotoDto> toDtos(List<Photo> photos);

}
