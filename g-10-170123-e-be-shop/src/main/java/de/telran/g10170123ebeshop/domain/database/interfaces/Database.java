package de.telran.g10170123ebeshop.domain.database.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Database {

    void execute(String query) throws SQLException; // внести изменения

    List<Object> select(String query) throws SQLException; // выбрать лист товаров
}
