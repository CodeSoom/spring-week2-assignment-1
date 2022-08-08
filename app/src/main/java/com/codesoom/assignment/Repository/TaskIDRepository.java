package com.codesoom.assignment.Repository;
import org.springframework.stereotype.Service;
@Service
public class TaskIDRepository {
    private Long curTaskID = 0L;

    public Long generateID(){
        curTaskID++;
        return curTaskID;
    }
}
