package ru.perfumess.model.shopping;

import lombok.*;
import ru.perfumess.model.BaseEntity;
import ru.perfumess.model.Customer;
import ru.perfumess.model.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "baskets")
@NoArgsConstructor
@RequiredArgsConstructor
public class Basket extends BaseEntity<String> {

    @NonNull
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) return;
        products.add(product);
    }

    public void deleteProduct(Product product){
        if (product == null) return;
        products.remove(product);
    }
}
