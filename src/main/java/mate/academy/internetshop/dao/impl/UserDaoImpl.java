package mate.academy.internetshop.dao.impl;

import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.idgenerators.UserIdGenerator;

/**
 * @author Sergey Klunniy
 */
@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        user.setId(UserIdGenerator.getGeneratedId());
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long idUser) {
        return Storage.users
                .stream().filter(b -> b.getId().equals(idUser))
                .findFirst();
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (user.getId().equals(Storage.users.get(i).getId())) {
                Storage.users.remove(i);
            }
        }
        Storage.users.add(user);
        return user;
    }

    @Override
    public boolean delete(Long userId) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (userId.equals(Storage.users.get(i).getId())) {
                Storage.users.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }
}
