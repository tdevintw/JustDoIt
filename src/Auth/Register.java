package Auth;

import User.User;
import database.Database;

import java.awt.desktop.SystemEventListener;

//using getters and setters to encapsulate data
//plus using the single responsibility principle from teh solid principles
// to ensure that one class can only have one role and this is done by
//creating an auth class that is responsible for auth operations

public class Register {

    private User user;

    public Register(User user) {
        this.user = user;
    }

    public void create(String name , String password){
        user.setName(name);
        user.setPassword(password);
    }


    public boolean confirmPassword(String ConfirmPassword) {
        if (ConfirmPassword.equals(user.getPassword())) {
            Database database = Database.getDatabase();
            database.addUser(user);
            return true;
        } else {
            return false;
        }
    }
}
