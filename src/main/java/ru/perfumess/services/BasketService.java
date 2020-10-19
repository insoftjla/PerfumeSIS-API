package ru.perfumess.services;

import ru.perfumess.model.Customer;
import ru.perfumess.model.shopping.Basket;

public interface BasketService extends BaseService<Basket> {

    Basket getByCustomer(Customer customer);
}
