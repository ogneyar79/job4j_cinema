package persistencebase;

import model.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserPredicateTest {
    UserPredicate predicate;
    User testUser;
    User inputUser;

    @Before
    public void install() {
        inputUser = new User(0, "Ivan", "Ivanov", "89232345");
        testUser = new User(0, "Ivan", "Ivanov", "89232345");
        predicate = new UserPredicate(inputUser);
    }

    @Test
    public void testEqualsUser() {
        boolean result = predicate.test(testUser);
        assertThat(result, is(true));
    }

    @Test
    public void testNotEqualsUser() {
        User testTwo = new User(0, "I", "Iva", "8976");
        boolean result = predicate.test(testTwo);
        assertThat(result, is(false));
    }
}
