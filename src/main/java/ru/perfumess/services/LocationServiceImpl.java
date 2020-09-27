package ru.perfumess.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.perfumess.model.Location;
import ru.perfumess.model.Status;
import ru.perfumess.repo.LocationRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;

    @Override
    public Location getById(Long id) {
        return locationRepository.getOne(id);
    }

    @Override
    public Location add(Location location) {
        location.setCreated(new Date());
        location.setUpdated(new Date());
        location.setStatus(Status.ACTIVE);
        return locationRepository.save(location);
    }

    @Override
    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
