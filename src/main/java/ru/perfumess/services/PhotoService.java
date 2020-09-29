package ru.perfumess.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.perfumess.model.Photo;

public interface PhotoService {

    Page<Photo> findAll(Pageable pageable);

    Photo getOne(Long id);

    Photo save(Photo photo);

    void delete(Photo photo);

}
