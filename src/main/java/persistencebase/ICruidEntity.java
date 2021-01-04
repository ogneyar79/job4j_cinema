package persistencebase;

import java.text.ParseException;
import java.util.Collection;
import java.util.Optional;

public interface ICruidEntity<I> {
    I delete(int id) throws ParseException;

    Collection<I> findAll();

    public void save(I entity);

    Optional<I> find(String element);
}
