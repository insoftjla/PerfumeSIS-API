package ru.perfumess.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {

    Page<T> findAll(Pageable pageable);

    T getOne(Long id);

    T save(T t);

    void delete(T t);

}
