package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Basket;

import java.util.Optional;

public interface BasketDao extends GenericDao<Basket, Long> {

    Optional<Basket> getByUserId(Long userId);
}
