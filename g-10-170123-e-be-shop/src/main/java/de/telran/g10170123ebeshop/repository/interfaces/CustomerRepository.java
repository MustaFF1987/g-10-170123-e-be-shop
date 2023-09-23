package de.telran.g10170123ebeshop.repository.interfaces;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getAll();

    Customer getCustomerById(int id);

    void addCustomer(String name);

    void deleteCustomerById(int id);

    void addToCartById(int customerId, int productId);

    void deleteAllProductsFromCart(int customerId);

    void deleteProductById(int customerId, int productId);


}
