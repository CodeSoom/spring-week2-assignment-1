package repository;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TodoRepository {

    private static Map<Long, Task> store= new HashMap<>();
    private static Long sequence = 0L;

    public synchronized Task save(Task task){
        task.setId(++sequence);
        store.put(task.getId(),task);
        return task;
    }

    public List<Task> findAll() {
        return new ArrayList<>(store.values());

    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Task update(Task task){
        store.replace(task.getId(),task);
        return task;
    }

    public void deleteById(Long id) {
        store.remove(id);
        return;
    }
}
