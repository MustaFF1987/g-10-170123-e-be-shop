package de.telran.g10170123ebeshop.domain.entity.interfaces;

import de.telran.g10170123ebeshop.domain.entity.jpa.JpaCart;

public interface Customer {

    int getId();
    String getName();
    Cart getShoppingCart();

    JpaCart getCart();
}
