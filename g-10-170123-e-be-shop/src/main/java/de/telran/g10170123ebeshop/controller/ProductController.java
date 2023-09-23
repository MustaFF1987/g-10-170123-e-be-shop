package de.telran.g10170123ebeshop.controller;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.service.common.CommonProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {


        @Autowired
        private CommonProductService commonProductService;

    /*
	Функционал кроме получения, удаления * и добавления *:
	Получить общее количество товаров.
	Получить суммарную стоимость товаров *.
	Получить среднюю стоимость товаров *.
	Удалить товар по наименованию *.*/


        // 1. Добавления товара
        @PostMapping("/addProduct")
        @ResponseStatus(HttpStatus.CREATED)
        public Product addProduct(@RequestBody Product product) {
                commonProductService.addProduct(product);
                return product;
        }

        // 2. Получение списка всех товаров (числовое)
        @GetMapping("/totalCount")
        public ResponseEntity<Long> getTotalProductCount() {
                long totalCount = commonProductService.getCount();
                return ResponseEntity.ok(totalCount);
        }

        // 3. Получение списка всех товаров (в виде json)
        @GetMapping("/totalCountList")
        Iterable<Product> getAllProductList() {
                return commonProductService.getAll();
        }

        // 4. Получение товара по Id
        @GetMapping("/getProductById/{id}")
        public ResponseEntity<Product> getProductById(@PathVariable int id) {
                Product product = commonProductService.getById(id);
                if (product != null) {
                        return ResponseEntity.ok(product); // Возвращаем найденный товар
                } else {
                        return ResponseEntity.notFound().build(); // Возвращаем статус 404, если товар не найден
                }
        }

        // 5. Удаление товара по id
        @DeleteMapping("/deleteById/{id}")
        void delete(@PathVariable int id)
        {
                commonProductService.deleteById(id);
        }

        // 6. Получить суммарную стоимость товаров.
        @GetMapping("/totalPrice")
        public ResponseEntity<Double> getTotalProductPrice() {
                double totalProductPrice = commonProductService.getTotalPrice();
                return ResponseEntity.ok(totalProductPrice);
        }

        // 7. Получить среднюю стоимость товаров.
        @GetMapping("/averagePrice")
        public ResponseEntity<Double> getAverageProductPrice() {
                double averageProductPrice = commonProductService.getAveragePrice();
                return ResponseEntity.ok(averageProductPrice);
        }

        // 8. Удалить товар по наименованию.
        @DeleteMapping("/deleteByName/{productName}")
        public ResponseEntity<Void> deleteProductByName(@PathVariable String productName) {
                commonProductService.deleteByName(productName);
                return ResponseEntity.noContent().build();
       }
}
