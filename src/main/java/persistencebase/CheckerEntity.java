package persistencebase;

import java.util.function.Predicate;

public class CheckerEntity<T> {

    private final ICruidEntity<T> entities;

    public CheckerEntity(ICruidEntity<T> entities) {
        this.entities = entities;
    }

    public ICruidEntity<T> getEntities() {
        return entities;
    }

    public boolean isEntityHere(Predicate<T> predicate) {
        return this.entities.findAll().stream().filter(stEntity -> predicate.test(stEntity))
                .findAny().isPresent() ? true : false;
    }

    public boolean isEntityHereTwo(String field) {
        return entities.find(field).isPresent();
    }
}
