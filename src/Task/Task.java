package Task;
import Task.Enums.Status;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import User.User;

public class Task {
    private final int id;
    private String title;
    private String description;
    private LocalDateTime deadLine;
    private Status status;

    public Task() {
        id = (int) (Math.random() * 1000000);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public Status getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void add(User user, String title, String description, LocalDateTime deadLine, int option) {
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        switch (option) {
            case 1:
                setStatus(Status.ToDo);
                break;
            case 2:
                setStatus(Status.Doing);
                break;
            case 3:
                setStatus(Status.Done);
                break;
        }

        user.setTasks(this);
    }

    public static void edit(User user, String title, String description, String deadLine, int status, int id) {
        Task task = null;
        LocalDateTime newDeadLine;
        Status newStatus;
        int index = -1;
        for (int i = 0; i < user.getTasks().size(); i++) {
            if (user.getTasks().get(i).getId() == id) {
                task = user.getTasks().get(i);
                index = i;
                break;
            }
        }
        if (task == null) {
            System.out.println("Task Not found ?!");
        } else {
            if (title == null) {
                title = task.getTitle();
            }
            if (description == null) {
                description = task.getDescription();
            }
            if (deadLine == null) {
                newDeadLine = task.getDeadLine();
            } else {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                newDeadLine = LocalDateTime.parse(deadLine, format);
            }

            switch (status) {
                case 1:
                    newStatus = Status.ToDo;
                    break;
                case 2:
                    newStatus = Status.Doing;
                    break;
                case 3:
                    newStatus = Status.Done;
                    break;
                default:
                    newStatus = task.getStatus();
            }

            user.getTasks().get(index).setTitle(title);
            user.getTasks().get(index).setDescription(description);
            user.getTasks().get(index).setDeadLine(newDeadLine);
            user.getTasks().get(index).setStatus(newStatus);
            System.out.println("Task edited successfully");
        }
    }

    public static void delete(User user, int id) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = user.getTasks();
        boolean findIt = false;
        for (int i = 0; i < tasks.size(); i++) {
            if ((tasks.get(i).getId()) == id) {
                findIt = true;
                System.out.println("Are you sure you want to delete this Task (Yes/No)");
                String choice = input.nextLine();
                if (choice.equals("Yes")) {
                    tasks.remove(i);
                    break;
                }
            }
        }
        if (!findIt) {
            System.out.println("The id doesnt exist , hmmm...");
        }

    }

    public static ArrayList<Task> filterByTitle(User user){
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Enter the title of Task");
        String title = input.nextLine();
        for(Task task : user.getTasks()){
            if(task.getTitle().contains(title)){
                tasks.add(task);
            }
        }
        return tasks;
    }
    public static ArrayList<Task> filterByDeadLine(User user){
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Enter the deadline (yyyy-MM-dd HH:mm:ss)");
        String deadline = input.nextLine();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(deadline,format);
        for(Task task : user.getTasks()){
            if(task.getDeadLine().equals(time)){
                tasks.add(task);
            }
        }
        return tasks;
    }
    public static ArrayList<Task> filterByStatus(User user){
        Status status;
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Enter the status of Tasks (1-ToDo , 2-Doing , 3-Done)");
        int option = input.nextInt();
        switch (option){
            case  1 : status = Status.ToDo;break;
            case  2 : status = Status.Doing;break;
            default:  status = Status.Done;break;
        }
        for(Task task : user.getTasks()){
            if(task.getStatus().equals(status)){
                tasks.add(task);
            }
        }
        return tasks;
    }
    public static ArrayList<Task> sort(User user){
        Scanner input = new Scanner(System.in);
        System.out.println("Do want to sort by order 1-increase or 2-decrease");
        int option = input.nextInt();
        ArrayList<Task> tasks = user.getTasks();
        if(option==1){
            for(int i = tasks.size()-1; i >=0 ; i--){
                for(int j = 0 ; j<i ; j++){

                    if((tasks.get(j).getTitle().toLowerCase().compareTo(tasks.get(j + 1).getTitle().toLowerCase()) > 0)){
                        Task temp = tasks.get(j);
                        tasks.set(j,tasks.get(j+1));
                        tasks.set(j+1,temp);
                    }
                }
            }
        }else{
            for(int i = tasks.size()-1; i >=0 ; i--){
                for(int j = 0 ; j<i ; j++){

                    if((tasks.get(j).getTitle().toLowerCase().compareTo(tasks.get(j + 1).getTitle().toLowerCase()) < 0)){
                        Task temp = tasks.get(j);
                        tasks.set(j,tasks.get(j+1));
                        tasks.set(j+1,temp);
                    }
                }
            }
        }
        return tasks;
    }
}

