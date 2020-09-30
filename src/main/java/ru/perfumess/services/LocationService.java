package ru.perfumess.services;

import ru.perfumess.model.Location;

public interface LocationService extends BaseService<Location>{

    void deleteById(Long id);
}
