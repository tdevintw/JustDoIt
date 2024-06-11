package Auth;

import User.User;
import database.Database;

//using getters and setters to encapsulate data
//plus using the single responsibility principle from teh solid principles
// to ensure that one class can only have one role and this is done by
//creating an auth class that is responsible for auth operations

public class Register {

    private User user;

    public Register(User user) {
        this.user = user;
    }

    public void setName(String name) {
        user.setName(name);
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }

    public void confirmPassword(String ConnfirmPassword) {
        if (ConnfirmPassword == user.getPassword()) {
            Database database = Database.getDatabase();
            database.addUser(user);
        } else {
            System.out.println("Confirm Password is Incorrect");
        }
    }
}
