package de.telran.g10170123ebeshop.controller;


import de.telran.g10170123ebeshop.domain.entity.common.CommonCustomer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.repository.common.CommonCustomerRepository;
import de.telran.g10170123ebeshop.repository.interfaces.CustomerRepository;
import de.telran.g10170123ebeshop.service.common.CommonCustomerService;
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
    private CommonCustomerService commonCustomerService;


    /* Задачи Получения, удаления и добавления пользователя
    Получить стоимость корзины покупателя по его идентификатору - .
    Получить среднюю стоимость товара в корзине покупателя по его идентификатору.
    Удалить покупателя по имени.
    Добавить в корзину покупателя товар по их идентификаторам.
    Удалить товар из корзины покупателя по их идентификаторам.
    Очистить корзину покупателя по его идентификатору.*/


 //   1.1 Получить общее количество покупателей (1ый вариант))
//    @GetMapping("/getAll")
//    public ResponseEntity<Long> getAllCustomers() {
//        long totalCustomers = customerRepository.getAll().stream().count();
//        return ResponseEntity.ok(totalCustomers);
//    }


    // 1.2 Получить общее количество покупателей со всеми данными (2ой вариант))
    @GetMapping("/getAll")
    Iterable<Customer> getAllCustomers() {
        return commonCustomerService.getAll();
    }


    // 2. Добавления пользователя
    @PostMapping ("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonCustomer addCustomer(@RequestBody CommonCustomer commonCustomer) {
        commonCustomerService.add(commonCustomer);
        return commonCustomer;

    }

    // 3. Получения пользователя по id
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = commonCustomerService.getById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer); // Возвращаем найденного покупателя
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем статус 404, если покупатель не найден
        }
    }

    // 4. Удаление пользователя по id
       @DeleteMapping("/deleteById/{id}")
       void delete(@PathVariable int id)
      {
          commonCustomerService.deleteById(id);
      }

    //  5. Получить стоимость корзины покупателя по его идентификатору.
        @GetMapping("/{customerId}/cart/total")
        public ResponseEntity<Double> getCartTotalByCustomerId(@PathVariable int customerId) {
        double cartTotal = commonCustomerService.getTotalPriceById(customerId);
        return ResponseEntity.ok(cartTotal);
      }

    //  6. Получить среднюю стоимость товара в корзине покупателя по его идентификатору
    @GetMapping("/{customerId}/cart/average")
    public ResponseEntity<Double> getAverageCartItemPrice(@PathVariable int customerId) {
        double averageCartItemPrice =  commonCustomerService.getAveragePriceById(customerId);
        return ResponseEntity.ok(averageCartItemPrice);
      }


    // 7. Очистить корзину покупателя по его идентификатору
    @DeleteMapping("/{customerId}/cart/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long customerId) {
        commonCustomerService.deleteById(customerId.intValue());
        return ResponseEntity.noContent().build();

    }

    // 8. Удалить товар из корзины покупателя по идентификатору
    @DeleteMapping("/{customerId}/cart/deleteProduct/{productId}")
    public ResponseEntity<Void> deleteProductFromCartByCustomerId(
            @PathVariable int customerId,
            @PathVariable int productId) {
        commonCustomerService.deleteFromCart(customerId, productId);
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
