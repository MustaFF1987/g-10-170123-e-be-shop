package de.telran.g10170123ebeshop.repository.interfaces;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getAll();

    Customer getCustomerById(int id);

    Customer getCustomerByName(String name);

    void addCustomer(String name);

    void deleteCustomerById(int id);

    void deleteCustomerByName(String name);

    void addToCartById(int customerId, int productId);

    void deleteFromCartById(int customerId, int productId);

    void deleteAllProductsFromCart(int customerId);

    double getCartTotalByCustomerId(int customerId);

    double getAveragePrice(int customerId);
}
