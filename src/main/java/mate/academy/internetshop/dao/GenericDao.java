package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergey Klunniy
 */
public interface GenericDao<T, V> {
    T create(T entity);

    Optional<T> get(V id);

    T update(T entity);

    boolean deleteById(V entityId);

    boolean delete(T entity);

    List<T> getAll();
}
