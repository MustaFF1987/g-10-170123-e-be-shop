package de.telran.g10170123ebeshop.repository.interfaces;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAll();

    Product getProductById(int id);

    void addProduct(String name, double price);

    void deleteProductById(int id);

}
