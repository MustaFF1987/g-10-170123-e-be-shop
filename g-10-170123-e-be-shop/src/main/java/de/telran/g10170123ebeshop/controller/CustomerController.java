package de.telran.g10170123ebeshop.controller;


import de.telran.g10170123ebeshop.domain.entity.common.CommonCustomer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.repository.common.CommonCustomerRepository;
import de.telran.g10170123ebeshop.repository.interfaces.CustomerRepository;
import de.telran.g10170123ebeshop.service.interfaces.CustomerService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    /* Получения, удаления и добавления пользователя
    Получить стоимость корзины покупателя по его идентификатору - .
    Получить среднюю стоимость товара в корзине покупателя по его идентификатору.
    Удалить покупателя по имени.
    Добавить в корзину покупателя товар по их идентификаторам.
    Удалить товар из корзины покупателя по их идентификаторам.
    Очистить корзину покупателя по его идентификатору.*/


 //   Получить общее количество покупателей (1ый вариант))
//    @GetMapping("/getAll")
//    public ResponseEntity<Long> getAllCustomers() {
//        long totalCustomers = customerRepository.getAll().stream().count();
//        return ResponseEntity.ok(totalCustomers);
//    }


    // Добавления пользователя
    @PostMapping ("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        customerRepository.addCustomer(customer.getName());
        return customer;

    }

 //    Получения пользователя по id
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable int id) {
        Customer customer = customerRepository.getCustomerById(id);
        return ResponseEntity.notFound().build();
        }

    // Удаление пользователя по id
    @DeleteMapping("/deleteById/{id}")
    void delete(@PathVariable int id)
    {
        customerRepository.deleteCustomerById(id);
    }


    // Получить общее количество покупателей со всеми данными (2ой вариант))
    @GetMapping("/getAll")
    Iterable<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

    // Получить стоимость корзины покупателя по его идентификатору.
        @GetMapping("/{customerId}/cart/total")
        public ResponseEntity<Double> getCartTotalByCustomerId(@PathVariable int customerId) {
        double cartTotal = customerRepository.getCartTotalByCustomerId(customerId);
        return ResponseEntity.ok(cartTotal);
    }

    //  Получить среднюю стоимость товара в корзине покупателя по его идентификатору
    @GetMapping("/{customerId}/cart/average")
    public ResponseEntity<Double> getAverageCartItemPrice(@PathVariable int customerId) {
        double averageCartItemPrice = customerRepository.getAveragePrice(customerId);
        return ResponseEntity.ok(averageCartItemPrice);
    }



    // Очистить корзину покупателя по его идентификатору
    @DeleteMapping("/{customerId}/cart/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long customerId) {
        customerRepository.deleteAllProductsFromCart(customerId.intValue());
        return ResponseEntity.noContent().build();


    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintViolationException(
            ConstraintViolationException ex
    ){
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(
                violation -> {
                    errors.put(
                            violation.getPropertyPath().toString(),
                            violation.getMessage()
                    );
                }
        );
        return errors;
    }

}
