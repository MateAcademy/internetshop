package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
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
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long idUser) {
        return Optional.ofNullable(Storage.users
                .stream()
                .filter(b -> b.getId().equals(idUser))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find user with id: "
                        + idUser)));
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
    public boolean deleteById(Long entityId) {
        Optional <User> optUser = get(entityId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (Storage.users.remove(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User findByEmail(String email) {
        return Storage.users.stream().filter(o -> o.getEmail().equals(email)).findFirst().get();
    }
}
