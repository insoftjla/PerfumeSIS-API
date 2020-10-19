package ru.perfumess.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.perfumess.model.Customer;
import ru.perfumess.model.shopping.Basket;
import ru.perfumess.repo.BasketRepository;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    @Override
    public Page<Basket> findAll(Pageable pageable) {
        return basketRepository.findAll(pageable);
    }

    @Override
    public Basket getOne(Long id) {
        return basketRepository.getOne(id);
    }

    @Override
    public Basket save(Basket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public void delete(Basket basket) {
        basketRepository.delete(basket);
    }


    @Override
    public Basket getByCustomer(Customer customer) {
        return basketRepository.getBasketByCustomer(customer);
    }
}
