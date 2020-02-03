package mate.academy.internetshop.dao.hibernate;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.naming.AuthenticationException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Sergey Klunniy
 */
@Dao
public class UserDaoHibernateImpl implements UserDao {

    private static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);

    @Override
    public User create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            transaction.commit();
            logger.info("Stored the item = " + user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't save item", e);
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public String getPassword(String login) {
        return null;
    }

    @Override
    public byte[] getSalt(String login) {
        return new byte[0];
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        return null;
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Optional.empty();
    }

    @Override
    public Set<Role> getUserRole(User user) {
        return null;
    }

    @Override
    public Set<String> getUserRoleName(User user) {
        return null;
    }


    @Override
    public Optional<User> get(Long id) {
        return Optional.empty();
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long entityId) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
