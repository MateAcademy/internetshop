package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }



    @Override
    public Item update(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (item.getId().equals(Storage.items.get(i).getId())) {
                Storage.items.remove(i);
            }
        }
        Storage.items.add(item);
        return item;
    }

    @Override
    public boolean deleteById(Long entityId) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (entityId.equals(Storage.items.get(i).getId())) {
                Storage.items.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (item.equals(Storage.items.get(i))) {
                Storage.items.remove(i);
                return true;
            }
        }
        return false;
    }
}

