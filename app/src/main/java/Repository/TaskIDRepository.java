package Repository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class TaskIDRepository {
    private Long curTaskID = 0L;

    public Long generateID(){
        curTaskID++;
        return curTaskID;
    }
}
