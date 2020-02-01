package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.model.Basket;

public interface BasketDao extends GenericDao<Basket, Long> {
    Optional<Basket> getByUserId(Long userId);
}
