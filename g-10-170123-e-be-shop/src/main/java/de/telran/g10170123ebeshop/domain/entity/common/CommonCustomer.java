package de.telran.g10170123ebeshop.domain.entity.common;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Cart;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;

public class CommonCustomer implements Customer {

    private int id;
    private String name;
    private Cart shoppingCart;

    public CommonCustomer(int id, String name, Cart shoppingCart) {
        this.id = id;
        this.name = name;
        this.shoppingCart = shoppingCart;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Cart getShoppingCart() {
        return shoppingCart;
    }
}
