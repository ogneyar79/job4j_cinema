package persistencebase;

import java.util.function.Predicate;

abstract public class EntityPredicateAbstract<T> implements Predicate<T> {
    abstract public T get();

}
