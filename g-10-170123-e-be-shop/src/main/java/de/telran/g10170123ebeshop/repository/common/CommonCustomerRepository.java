package de.telran.g10170123ebeshop.repository.common;

import de.telran.g10170123ebeshop.domain.database.interfaces.Database;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Cart;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.repository.interfaces.CustomerRepository;
import de.telran.g10170123ebeshop.repository.interfaces.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

public class CommonCustomerRepository implements CustomerRepository {


    @Autowired
    private Database database;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Customer> getAll() {
        try {
            return database.select("Select all customers").stream().map(x -> (Customer) x).toList();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        try {
           return (Customer) database.select("Select customer where id = " + id).get(0);
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void addCustomer(String name) {
        try {
            database.execute("Add new customer name = " + name);
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteCustomerById(int id) {
        try{
            database.execute("Delete customer where id = " + id);
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void addToCartById(int customerId, int productId) {

          Product product = productRepository.getProductById(productId);
          getCustomerById(customerId).getShoppingCart().addProduct(product);
    }

    @Override
    public void deleteAllProductsFromCart(int customerId) {

        getCustomerById(customerId).getShoppingCart().deleteAll();
    }

    @Override
    public void deleteProductById(int customerId, int productId){
        Customer customer = getCustomerById(customerId);

        if (customer != null) {
            Cart shoppingCart = customer.getShoppingCart();
            List<Product> cartItems = shoppingCart.getProducts();

            // Удаление товара из корзины по его идентификатору
            cartItems.removeIf(product -> product.getId() == productId);
        } else {
            throw new IllegalArgumentException("Customer not found"); // если покупатель не найден
        }
    }

}
