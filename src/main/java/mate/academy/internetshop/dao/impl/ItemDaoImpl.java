package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.idgenerators.ItemIdGenerator;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        item.setId(ItemIdGenerator.getId());
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long index) {
        return Storage.items.stream()
                .filter(x -> x.getId().equals(index))
                .findFirst();
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
        Optional optionalItem = Optional.ofNullable(Storage.items
                .stream()
                .filter(i -> i.getId().equals(entityId))
                .findFirst());
        if (optionalItem.isPresent()) {
            return Storage.items.remove(optionalItem.get());
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        return Storage.items.remove(item);
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }
}
