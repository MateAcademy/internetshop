package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BasketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.Basket;

public class BasketDaoImpl implements BasketDao {

    @Override
    public Optional<Basket> getByUserId(Long userId) {
        return Storage.BASKETS
                .stream()
                .filter(i -> i.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public Basket create(Basket basket) {
        Storage.BASKETS.add(basket);
        return basket;
    }

    @Override
    public Optional<Basket> get(Long id) {
        return Storage.BASKETS
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public Basket update(Basket basket) {
        for (int i = 0; i < Storage.BASKETS.size(); i++) {
            if (basket.getId().equals(Storage.BASKETS.get(i).getId())) {
                Storage.BASKETS.remove(i);
            }
        }
        Storage.BASKETS.add(basket);
        return basket;
    }

    @Override
    public boolean deleteById(Long bucketId) {
        for (int i = 0; i < Storage.BASKETS.size(); i++) {
            if (bucketId.equals(Storage.BASKETS.get(i).getUserId())) {
                Storage.BASKETS.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Basket basket) {
        return Storage.BASKETS.remove(basket);
    }

    @Override
    public List<Basket> getAll() {
        return Storage.BASKETS;
    }
}
