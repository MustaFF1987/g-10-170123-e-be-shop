package de.telran.g10170123ebeshop.repository.interfaces;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository {

    List<Product> getAll();

    Product getById(int id);

    void add(String name, double price);

    void delete(int id);

    @Query("SELECT p FROM Product p WHERE p.name = :name ORDER BY p.timestamp DESC")
    List<Product> findTopByNameOrderByTimestampDesc(String name);
}

