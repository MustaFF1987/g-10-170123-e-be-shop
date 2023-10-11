package de.telran.g10170123ebeshop.domain.entity.interfaces;

import java.util.List;

public interface Cart {

    List<Product> getProducts();

    double getTotalPrice();

    double getAveragePrice();

    void addProduct(Product product);

    void deleteProduct(int productId);

    void deleteAll();

    double calculateTotal();

    void clear();
}
