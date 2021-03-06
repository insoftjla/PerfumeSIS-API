package ru.perfumess.controllers.rest.v1.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.dto.BasketDto;
import ru.perfumess.mappers.BasketMapper;
import ru.perfumess.model.Customer;
import ru.perfumess.model.product.Product;
import ru.perfumess.model.shopping.Basket;
import ru.perfumess.services.BasketService;
import ru.perfumess.services.CustomerService;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/basket")
public class BasketController {
    private final BasketService basketService;
    private final BasketMapper basketMapper;
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<BasketDto> getBasket(Principal principal){
        Basket basket = getBasketOfPrincipal(principal);
        BasketDto basketDto = basketMapper.toDto(basket);
        if (basketDto == null) {
            log.info("[getBasket] Basket NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(basketDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BasketDto> addProduct(
            @RequestParam("productId") Product product,
            Principal principal
    ){
        Basket basket = getBasketOfPrincipal(principal);
        if (basket == null) {
            log.info("[addProduct] Basket NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        basket.addProduct(product);
        basket = basketService.save(basket);
        return new ResponseEntity<>(basketMapper.toDto(basket), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BasketDto> deleteProduct(
            @RequestParam("productId") Product product,
            Principal principal
    ){
        Basket basket = getBasketOfPrincipal(principal);
        if (product == null || basket == null){
            log.info("[addProduct] Product or Basket NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        basket.deleteProduct(product);
        basket = basketService.save(basket);
        return new ResponseEntity<>(basketMapper.toDto(basket), HttpStatus.OK);
    }

    private Basket getBasketOfPrincipal(Principal principal){
        Customer customer = customerService.getByUsername(principal.getName());
        return basketService.getByCustomer(customer);
    }
}
