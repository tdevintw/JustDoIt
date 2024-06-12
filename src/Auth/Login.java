package Auth;
import User.User;
import database.Database;


// verify if the name exist if yes check password

public class Login {

    public User verifyName(String name){
        Database database = Database.getDatabase();
        for(User user : database.getUsers()){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    public boolean verifyPassword(User user , String passord){
        if(user.getPassword().equals(passord)){
            return true;
        }else{
            return false;
        }
    }
}

