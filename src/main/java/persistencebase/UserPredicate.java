package persistencebase;

import model.User;

public class UserPredicate extends EntityPredicateAbstract<User> {
    private final User input;

    public UserPredicate(User input) {
        this.input = input;
    }

    @Override
    public boolean test(User test) {
        return this.input.getPhone().equals(test.getPhone()) ? true : false;
    }

    @Override
    public User get() {
        return this.input;
    }
}
