package Task;
import Task.Enums.Status;
import java.time.LocalDateTime;
import User.User;
public class Task {
    private int id ;
    private String title;
    private String description;
    private LocalDateTime deadLine ;
    private Status status;

    public Task(){
        id = (int) (Math.random()*1000000);
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

}
