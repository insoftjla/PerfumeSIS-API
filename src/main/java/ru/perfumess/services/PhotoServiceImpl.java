package ru.perfumess.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.perfumess.model.Photo;
import ru.perfumess.model.Status;
import ru.perfumess.repo.PhotoRepository;

import java.util.Date;

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
    public Photo save(Photo photo) {
        photo.setStatus(Status.ACTIVE);
        return productPhotoRepository.save(photo);
    }

    @Override
    public void delete(Photo photo) {
        productPhotoRepository.delete(photo);
    }
}
