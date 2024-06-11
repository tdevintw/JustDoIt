package User;
import Task.Task;
import java.util.ArrayList;
import database.Database;

//user class represent the identity User , each user may have 0 or many tasks
//all the users are stored in teh database instance to ensure auth operations

public class User {
    private int id;
    private String name;
    private String password;
    private ArrayList<Task> tasks;


    public User(){

        id = (int)(Math.random()*1000000);
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String Name){
        name = Name;
    }

    public void setPassword(String Password){
        password = Password;
    }





}


