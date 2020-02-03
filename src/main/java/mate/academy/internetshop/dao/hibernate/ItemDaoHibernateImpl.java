package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    @Override
    public Item create(Item entity) {
        return null;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Item update(Item entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long entityId) {
        return false;
    }

    @Override
    public boolean delete(Item entity) {
        return false;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }
}
