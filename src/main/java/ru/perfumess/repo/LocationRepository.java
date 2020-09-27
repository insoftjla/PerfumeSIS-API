package ru.perfumess.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perfumess.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
