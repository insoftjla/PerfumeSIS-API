package ru.perfumess.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.perfumess.model.Photo;
import ru.perfumess.repo.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository productPhotoRepository;

    public PhotoServiceImpl(PhotoRepository productPhotoRepository) {
        this.productPhotoRepository = productPhotoRepository;
    }

    @Override
    public Page<Photo> findAll(Pageable pageable) {
        return productPhotoRepository.findAll(pageable);
    }

    @Override
    public Photo getOne(Long id) {
        return productPhotoRepository.getOne(id);
    }

    @Override
    public void save(Photo photo) {
        productPhotoRepository.save(photo);
    }

    @Override
    public void delete(Photo photo) {
        productPhotoRepository.delete(photo);
    }
}
