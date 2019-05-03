package TestUtils;

import com.authenticate.loginservice.models.User;

import java.util.ArrayList;
import java.util.List;

public class TestUsers {

    public static List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("User1First", "User1Last", "user1@email.com", "user1"));
        users.add(new User("User2First", "User2Last", "user2@email.com", "user2"));
        users.add(new User("User3First", "User3Last", "user3@email.com", "user3"));
        users.add(new User("User4First", "User4Last", "user4@email.com", "user4"));

        return users;
    }


}
