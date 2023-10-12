package de.telran.g10170123ebeshop.config;

import de.telran.g10170123ebeshop.domain.database.common.CommonDatabase;
import de.telran.g10170123ebeshop.domain.database.interfaces.Database;
import de.telran.g10170123ebeshop.repository.interfaces.CustomerRepository;
import de.telran.g10170123ebeshop.repository.interfaces.ProductRepository;
import de.telran.g10170123ebeshop.repository.mysql.MySqlCustomerRepository;
import de.telran.g10170123ebeshop.repository.mysql.MySqlProductRepository;
import de.telran.g10170123ebeshop.service.interfaces.ProductService;
import de.telran.g10170123ebeshop.service.jpa.JpaProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Database database() {

        return new CommonDatabase();
    }

    @Bean
    public CustomerRepository customerRepository() {

        return new MySqlCustomerRepository();
    }

    @Bean
    public ProductRepository productRepository() {

        return new MySqlProductRepository();
    }

//    @Bean
//    public ProductService productService()
//    {
//        return new JpaProductService();
//    }


//    @Bean
//    public CustomerService customerService() {
//        return new JpaCustomerService();
//    }

//    @Bean
//    public CustomerService customerService() {
//        return new CommonCustomerService();
//    }

//    @Bean
//    public ProductService productService() {
//        return new CommonProductService();
//    }


}
