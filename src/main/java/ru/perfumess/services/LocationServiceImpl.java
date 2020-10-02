package ru.perfumess.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Location> findAll(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }

    @Override
    public Location getOne(Long id) {
        return locationRepository.getOne(id);
    }

    @Override
    public Location save(Location location) {
        location.setStatus(Status.ACTIVE);
        return locationRepository.save(location);
    }

    @Override
    public void delete(Location location) {
        locationRepository.delete(location);
    }

    @Override
    public void deleteById(Long id) {
        locationRepository.deleteById(id);
    }
}
