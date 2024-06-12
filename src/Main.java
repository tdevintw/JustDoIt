import Auth.Login;
import Auth.Register;
import User.User;
import database.Database;

import java.util.Scanner;

public class Main {

    private static User currentUser;


    public static User Authenticate(int option) {
        Scanner input = new Scanner(System.in);

        if (option == 1) {
            System.out.println("Lets Create Your Account");
            User user = new User();
            Register register = new Register(user);
            System.out.println("What is your name , if you already have an account type login");
            String name = input.nextLine();
            if (name.equals("login")) {
                Authenticate(2);
            }
            System.out.println("what is your password");
            String password = input.nextLine();
            register.create(name, password);
            System.out.println("please confirm your password");
            String confirmPassword = input.nextLine();

            while (!register.confirmPassword(confirmPassword)) {
                System.out.println("Confirm Password is Incorrect , give it another shot , or exit by writing exit");
                confirmPassword = input.nextLine();
                if (confirmPassword.equals("exit")) {
                    main(null);
                }
                register.confirmPassword(confirmPassword);
            }
            System.out.println("Congratulations Your account has been created !!");
            user.setAuthenticated(true);
            return user;
        } else if (option == 2) {
            System.out.println("Welcome Dear !");
            System.out.println("please enter your name");
            Login login = new Login();
            String name = input.nextLine();
            while (login.verifyName(name) == null) {
                System.out.println("name not found, try again , or exit by writing exit");
                name = input.nextLine();
                if (name.equals("exit")) {
                    main(null);
                }
                login.verifyName(name);
            }
            User user = login.verifyName(name);
            System.out.println("Enter your password");
            String password = input.nextLine();
            while (!login.verifyPassword(user, password)) {
                System.out.println("Password incorrect , please try again");
                password = input.nextLine();
                login.verifyPassword(user, password);
            }
            user.setAuthenticated(true);
            return user;
        } else {
            System.out.println("Please Enter A valid options , or exit by entering 0");
            int newOption = input.nextInt();
            if (newOption == 0) {
                main(null);
            }
            Authenticate(newOption);
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option = 0;

        if (currentUser == null) {
            System.out.println("Welcome there, Start Your Journey with JustDoIt");
            System.out.println("1-Create Account | 2-Login to my Account");
            System.out.println("Please choose one of the options");
            option = input.nextInt();
//            User user = new User();
//            currentUser = user;
//            user.setName("yasser");
//            user.setPassword("0668");
//            Database database = Database.getDatabase();
//            database.addUser(user);
//        for(User usertest : database.getUsers()){
//            System.out.println("name is "+usertest.getName());
//        }
            currentUser = Authenticate(option);
        }
        System.out.println("Welcome Back " + currentUser.getName());


        //Menu
        User user = currentUser;
        System.out.println("1-Edit my Name");
        System.out.println("2-Edit my Password");
        System.out.println("3-Logout");
        System.out.println("4-Delete Account");
        option = input.nextInt();
        editInfo(user, option);


    }

    public static void editInfo(User user, int option) {
        Scanner input = new Scanner(System.in);
        if (option == 1) {
            System.out.println("Please Enter Your New Name , or  type return to return ");
            String name = input.nextLine();
            if (name.equals("return")) {
                main(null);
            }
            user.setName(name);
            System.out.println("Name changed successfully");
            main(null);
        } else if (option == 2) {
            System.out.println("Enter your current password , or  type return to return ");
            String password = input.nextLine();
            if (password.equals("return")) {
                System.out.println("gggg");
                main(null);
            }

            Register register = new Register(user);
            while (!register.confirmPassword(password)) {
                System.out.println("Password Incorrect ,give it another shot , or  type return to return ");
                password = input.nextLine();
                if (password.equals("return")) {
                    main(null);
                }
                register.confirmPassword(password);
            }
            System.out.println("Enter Your new password");
            password = input.nextLine();
            user.setPassword(password);
            System.out.println("Password changed successfully");
            main(null);
        } else if (option == 3) {
            currentUser = null;
            main(null);
        } else if (option == 4) {
            System.out.println("areyou sure you want to delete your account , all your data will be vanished (yes/no)");
            String decesion = input.nextLine();
            if (decesion.equals("yes")) {
                currentUser.deleteAccount();
                currentUser = null;

            }
            main(null);


        } else {
            System.out.println("choose an available option ");
            option = input.nextInt();
            editInfo(user, option);
        }
    }
}