import Auth.Login;
import Auth.Register;
import Task.Task;
import User.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static User currentUser;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option;

        if (currentUser == null) {
            System.out.println("Welcome there, Start Your Journey with JustDoIt \n 1-Create Account | 2-Login to my Account \n Please choose one of the options");
            option = input.nextInt();
            currentUser = Authenticate(option);
        }
        System.out.println("Welcome Back " + currentUser.getName());


        //Menu
        User user = currentUser;
        System.out.println("------------MyInfo------------");
        System.out.println(" 1-Edit my Name \n 2-Edit my Password \n 3-Logout \n 4-Delete Account");
        System.out.println("------------Tasks------------");
        System.out.println(" 6-MyTasks \n 7-Add Task \n 8-Update Task \n 9-Delete Task");
//        System.out.println("------------Filter Tasks------------");
//        System.out.println(" 10-search a task by title \n 7-Filter task by title  \n 8-sort tasks by deadline \n 9-sort by status");
        option = input.nextInt();
        if (5 < option && option < 10) {
            manageTasks(user, option);
        } else {
            editInfo(user, option);
        }


    }


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
//            user.setAuthenticated(true);
            return user;
        } else if (option == 2) {
            System.out.println("Welcome Dear ! \n please enter your name");
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
//            user.setAuthenticated(true);
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
            System.out.println("are you sure you want to delete your account , all your data will be vanished (yes/no)");
            String decision = input.nextLine();
            if (decision.equals("yes")) {
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

    public static void manageTasks(User user, int option) {
        Scanner input = new Scanner(System.in);


        if (option == 6) {
            ArrayList<Task> tasks = user.getTasks();
            if (tasks == null) {
                System.out.println("There is no tasks yet ! , do you want to create your first task now (Yes/Nop)");
                String choice = input.nextLine();
                if (choice.equals("Yes")) {
                    manageTasks(user, 7);
                }
            } else {
                int i = 0;

                for (Task task : tasks) {
                    i++;
                    System.out.println("-----Task" + i + "-----");
                    System.out.println("Title: " + task.getTitle());
                    System.out.println("Description: " + task.getDescription());
                    //format date
                    LocalDateTime deadline = task.getDeadLine();
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd HH:mm:ss");
                    String formattedDateTime = deadline.format(outputFormatter);
                    System.out.println("Deadline: " + formattedDateTime);
                    System.out.println("Status: " + task.getStatus());
                    System.out.println();
                }
            }

            System.out.println("return to menu...");
            main(null);
        }
        //add task
        else if (option == 7) {
            System.out.println("Lets add your task");
            System.out.println("Enter Task title");
            String title = input.nextLine();
            System.out.println("Enter Task Description");
            String description = input.nextLine();
            System.out.println("Enter Task time(format : yyyy-MM-dd HH:mm:ss)");
            String deadline = input.nextLine();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.parse(deadline, format);
            System.out.println("Enter Task ToDo: 1 , Doing : 2  , Done : 3");
            int Status = input.nextInt();
            Task newTask = new Task();
            newTask.add(user, title, description, time, Status);
            System.out.println("Congratulations Task add successfully , return to menu...");
            main(null);

        }
    }
}