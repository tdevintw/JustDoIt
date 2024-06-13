package Task;
import Task.Enums.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import User.User;
import com.sun.tools.javac.Main;

public class Task {
    private final int id ;
    private String title;
    private String description;
    private LocalDateTime deadLine ;
    private Status status;

    public Task(){
        id = (int) (Math.random()*1000000);
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public LocalDateTime getDeadLine(){
        return deadLine;
    }

    public Status getStatus(){
        return status;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setDeadLine(LocalDateTime deadLine){
        this.deadLine = deadLine;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public void add(User user , String title , String description , LocalDateTime deadLine , int option){
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        switch(option){
            case 1 : setStatus(Status.ToDo); break ;
            case 2 : setStatus(Status.Doing);break;
            case 3 : setStatus(Status.Done);break;
        }

        user.setTasks(this);
    }

    public static void delete(User user , int id){
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = user.getTasks();
        boolean findIt = false;
        for(int i  = 0 ; i<tasks.size() ; i++){
            if((tasks.get(i).getId())==id){
                findIt = true;
                System.out.println("Are you sure you want to delete this Task (Yes/No)");
                String choice = input.nextLine();
                if(choice.equals("Yes")){
                    tasks.remove(i);
                    break;
                }
            }
        }
        if(!findIt){
            System.out.println("The id doesnt exist , hmmm...");
        }

    }
}
