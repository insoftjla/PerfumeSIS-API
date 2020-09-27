package ru.perfumess.services;

import ru.perfumess.model.Location;

public interface LocationService {

    Location getById(Long id);

    Location add(Location location);

    void deleteById(Long id);
}
