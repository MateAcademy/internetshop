package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long itemId) {
        return itemDao.get(itemId).get();
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAll();
    }

    @Override
    public void deleteByID(Long itemId) {
        itemDao.deleteById(itemId);
    }

    @Override
    public void delete(Item item) {
        itemDao.delete(item);
    }
}
