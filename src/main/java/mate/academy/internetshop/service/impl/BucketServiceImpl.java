package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BasketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BasketDao basketDao;
    @Inject
    private static ItemDao itemDao;

    @Override
    public List<Basket> getAll() {
        return basketDao.getAll();
    }

    @Override
    public Basket getByUserId(Long userId) {
        if (basketDao.getByUserId(userId).isPresent()) {
            return basketDao.getByUserId(userId).get();
        }
        return new Basket(userId);
    }

    @Override
    public void addItem(Basket basket, Item item) {
        basket.addItem(item);
        basketDao.update(basket);
    }

    @Override
    public Basket addItem(Long bucketId, Long itemId) {
        Basket basket = basketDao.get(bucketId).get();
        Optional<Item> optItem = itemDao.get(itemId);
        if (optItem.isPresent()) {
            Item item = optItem.get();
            basket.getItems().add(item);
            return basketDao.update(basket);
        }
        return null;
    }

    @Override
    public Basket create(Basket basket) {
        return basketDao.create(basket);
    }

    @Override
    public List<Item> getAllItems(Basket basket) {
        return basketDao.get(basket.getUserId()).get().getItems();
    }

    @Override
    public Basket get(Long id) {
        Optional<Basket> optBucket = basketDao.get(id);
        if (optBucket.isPresent()) {
            return optBucket.get();
        }
        return null;
    }

    @Override
    public Basket update(Basket basket) {
        return basketDao.update(basket);
    }

    @Override
    public boolean delete(Long bucketId) {
        return basketDao.deleteById(bucketId);
    }

    @Override
    public boolean delete(Basket basket) {
        return basketDao.delete(basket);
    }

    @Override
    public void deleteItem(Basket basket, Item item) {
        basket.getItems().remove(item);
    }
}
