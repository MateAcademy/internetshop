package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.naming.AuthenticationException;

import mate.academy.internetshop.dao.BasketDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;

import mate.academy.internetshop.model.Basket;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

/**
 * @author Sergey Klunniy
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private static UserDao userDao;

    @Inject
    private static BasketDao basketDao;

    @Override
    public String getPassword(String login) {
        return userDao.getPassword(login);
    }

    @Override
    public byte[] getSalt(String login) {
        return userDao.getSalt(login);
    }

    @Override
    public User create(User user) {
        user.setToken(getToken());
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.create(user);
    }

    @Override
    public Set<String> getUserRoleName(User user) {
        return userDao.getUserRoleName(user);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public User get(Long idUser) {
        Optional<User> optUser = userDao.get(idUser);
        if (optUser.isPresent()) {
            return optUser.get();
        }
        return null;
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long userId) {
        return userDao.deleteById(userId);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException {
        byte[] salt = getSalt(login);

        if (HashUtil.hashPassword(password, salt).equals(getPassword(login))) {
            User user = userDao.login(login, HashUtil.hashPassword(password, salt));
            Set<Role> userRole = getUserRole(user);
            user.setRoles(userRole);
            Optional<Basket> optBasket = basketDao.getByUserId(user.getId());
            if (!optBasket.isPresent()) {
                Basket basket = new Basket(user.getId());
                basketDao.create(basket);
            }
            return user;
        }
        return null;
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }

    @Override
    public Set<Role> getUserRole(User user) {
        return userDao.getUserRole(user);
    }
}
