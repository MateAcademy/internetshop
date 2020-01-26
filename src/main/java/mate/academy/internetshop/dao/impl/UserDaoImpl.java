package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.model.User;

import javax.naming.AuthenticationException;

/**
 * @author Sergey Klunniy
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User create(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
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
        Optional<User> optUser = get(entityId);
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
    public Optional<User> findByEmail(String email) {
        return Storage.users.stream()
                .filter(o -> o.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException {
        Optional<User> user = Storage.users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("incorrect username or password");
        }
        return user.get();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Storage.users.stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();
    }
}
