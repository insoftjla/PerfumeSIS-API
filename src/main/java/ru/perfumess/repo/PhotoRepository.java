package ru.perfumess.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.perfumess.model.Photo;

@RepositoryRestResource
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
