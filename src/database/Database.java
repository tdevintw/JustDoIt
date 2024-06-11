package database;
import User.User;
import  java.util.ArrayList;


// implementing the Singleton Design Pattern to enable that only one instance can
//be created that store the users values.

public class Database {
    private static Database database;
    private ArrayList<User> users;

    private Database(){};

    public static Database getDatabase(){
        if(database ==null){
            database = new Database();
        }
        return database;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }
}
