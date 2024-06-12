package Task;
import java.time.LocalDateTime;

enum Status{
    ToDo ,
    Doing ,
    Done
}
public class Task {
    private int id ;
    private String title;
    private String description;
    private LocalDateTime deadLine ;
    private Status  status;

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
        return getDeadLine();
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

    public void add(String title , String description , LocalDateTime deadLine , Status status){
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setStatus(status);
    }

}
