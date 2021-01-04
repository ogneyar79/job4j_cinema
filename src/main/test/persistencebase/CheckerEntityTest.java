package persistencebase;

import model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckerEntityTest {

    User testUserFirst;
    User testUserTwo;
    User testUserThree;

    User sameInputUser;

    UserPredicate predicate;
    CheckerEntity<User> checkerEntityUser;
    ICruidEntity<User> entities;


    List<User> userList = new ArrayList<>();

    @Before
    public void install() {
        sameInputUser = new User(0, "Ivan", "Ivanov", "89232345");
        testUserFirst = new User(0, "Ivan", "Ivanov", "89232345");
        testUserTwo = new User(0, "Petr", "Petrov", "895498998");
        testUserThree = new User(0, "Ivan", "Ivanov", "892323458");
        userList.add(testUserFirst);
        userList.add(testUserTwo);
        userList.add(testUserThree);
        entities = mock(UserEntity.class);
        checkerEntityUser = new CheckerEntity<>(entities);
        predicate = new UserPredicate(sameInputUser);
    }

    @Test
    public void checkTheSameUserAtList() {
        when(entities.findAll()).thenReturn(userList);
        boolean result = checkerEntityUser.isEntityHere(predicate);
        assertThat(result, is(true));
    }

    @Test
    public void checkNotSameUserAtList() {
        User inputUser = new User(0, "Galina", "Coby","892678");
        UserPredicate predicateB = new UserPredicate(inputUser);
        when(entities.findAll()).thenReturn(userList);
        boolean result = checkerEntityUser.isEntityHere(predicateB);
        assertThat(result, is(false));
    }
}