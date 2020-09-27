package ru.perfumess.mappers;

import org.mapstruct.Mapper;
import ru.perfumess.dto.LocationDto;
import ru.perfumess.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto toDto(Location location);

    Location toLocation(LocationDto locationDto);

}
