package de.telran.g10170123ebeshop.service.common;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Cart;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.repository.interfaces.CustomerRepository;
import de.telran.g10170123ebeshop.service.interfaces.CustomerService;
import de.telran.g10170123ebeshop.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class CommonCustomerService implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ProductService productService;

    @Override
    public List<Customer> getAll() {
        return repository.getAll();
    }

    @Override
    public Customer getById(int id) {
        return repository.getCustomerById(id);
    }

    @Override
    public void add(Customer customer) {
        repository.addCustomer(customer.getName());
    }

    @Override
    public void deleteById(int id) {
        repository.deleteCustomerById(id);
    }

    @Override
    public void deleteByName(String name) {
        int idToDelete = repository.getAll().stream().filter(x -> x.getName().equals(name)).findFirst().get().getId();
        repository.getCustomerById(idToDelete);
    }

    @Override
    public int getCount() {
        return repository.getAll().size();
    }

    @Override
    public double getTotalPriceById(int id) {
        return repository.getCustomerById(id).getShoppingCart().getTotalPrice();
    }

    @Override
    public double getAveragePriceById(int id) {
        Cart cart = repository.getCustomerById(id).getShoppingCart();
        return cart.getTotalPrice() / cart.getProducts().size();
    }

    @Override
    public void addToCartById(int customerId, int productId) {
        repository.addToCartById(customerId, productId);
    }

        @Override
        public void deleteFromCart(int customerId, int productId) {
            repository.deleteProductById(customerId, productId);
        }

    @Override
    public void clearCart(int customerId) {
        repository.deleteAllProductsFromCart(customerId);
    }
}