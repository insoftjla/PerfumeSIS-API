package ru.perfumess.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perfumess.model.Customer;
import ru.perfumess.model.shopping.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Basket getBasketByCustomer(Customer customer);
}
