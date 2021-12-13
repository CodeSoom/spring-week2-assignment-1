package com.codesoom.assignment.service;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * To-do List를 생성,조회,상세조회,수정,삭제 기능을 제공하는 서비스입니다.
 * @exception TaskNotFoundException
 * */
@Service
public class TaskService {

    private final Map<Long, Task> tasks = new LinkedHashMap<Long, Task>();
    private Long newId = 0L;
    /**
     * 저장된 To-Do List를 리턴합니다.
     * */
    public List<Task> list() {

        return new ArrayList<Task>(tasks.values());
    }

    /**
     * Task 객체를 받아 id를 생성하고 상태를 yet(아직)으로 설정한 task를 리턴합니다.
     * */
    public Task create(Task task) {
        Long id = generateId();
        task.setId(id);
        task.setState("yet");
        tasks.put(id, task);

        return task;
    }
    /**
     * 요청한 id에 해당하는 task가 없으면 TaskNotFoundException 처리하고, 그렇지 않다면 task를 리턴합니다.
     * */
    public Optional<Task> view(Long id) {
        Optional<Task> task = findTask(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException("요청하신 " + id + "의 Task가 없습니다.");
        }
        return task;
    }

    public Optional<Task> update(Task body, Long id) {
        Optional<Task> task = findTask(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException( id + "에 해당하는 Task가 없으므로 업데이트를 할 수 없습니다.");
        }
        task.get().setTitle(body.getTitle());
        task.get().setState(body.getState());
        return task;
    }

    public void delete(Long id) {
        Optional<Task> task = findTask(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(id + "에 해당하는 Task가 없으므로 삭제를 할 수 없습니다.");
        }
        tasks.remove(id);
    }

    private synchronized Long generateId() {
        newId += 1;
        return newId;
    }

    private Optional<Task> findTask(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id가 null이므로 Task를 찾을 수 없습니다.");
        }

        return Optional.ofNullable(tasks.get(id));
    }
}
