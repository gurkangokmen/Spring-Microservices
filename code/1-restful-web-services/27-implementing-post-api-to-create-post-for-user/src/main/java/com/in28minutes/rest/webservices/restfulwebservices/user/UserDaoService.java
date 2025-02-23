package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "John Doe", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Jane Doe", LocalDate.now().minusYears(20)));
    }



    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate= user -> user.getId().equals(id);
        //return users.stream().filter(predicate).findFirst().get();
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate= user -> user.getId().equals(id);
        users.removeIf(predicate);

    }

    public User save(User user) {
        System.out.println(user); //We do not send id, and result => User{id=null, name='Asha', birthDate=1557-04-03}
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
