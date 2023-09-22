package de.telran.g10170123ebeshop.service.common;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.repository.interfaces.ProductRepository;
import de.telran.g10170123ebeshop.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class CommonProductService implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product.getName(), product.getPrice());
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public int getCount() {
        return productRepository.getAll().size();
    }

    @Override
    public double getTotalPrice() {
        return productRepository.getAll().stream().mapToDouble(x -> x.getPrice()).sum();
    }

    @Override
    public double getAveragePrice() {
        int count = getCount();
        if (count == 0) {
            return 0;
        }
        return getTotalPrice() / count;
    }

    @Override
    public void deleteByName(String name) {
        getAll().removeIf(x -> x.getName().equals(name));
    }
}