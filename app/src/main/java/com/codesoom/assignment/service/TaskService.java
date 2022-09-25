package com.codesoom.assignment.service;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.exception.TaskIdNotFoundException;
import com.codesoom.assignment.model.TaskInfo;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * 등록된 모든 할 일들을 리턴한다.
     *
     * @return JSON으로 리턴될 등록된 모든 할 일
     */
    public List<TaskInfo> findAll() {
        List<TaskInfo> taskInfoList = new ArrayList<>();
        taskRepository.findAll().forEach(task -> taskInfoList.add(new TaskInfo(task)));
        return taskInfoList;
    }

    /**
     * 요청받은 할 일 ID로 해당 할 일을 조회해서 리턴한다.
     *
     * @param id 요청 할 일 ID
     * @return JSON으로 리턴될 검색된 할 일
     * @throws TaskIdNotFoundException 할 일을 못 찾을 경우 발생하는 예외
     */
    public TaskInfo findById(Long id) {
        return new TaskInfo(taskRepository.findById(id));
    }

    /**
     * 클라이언트가 입력한 할 일을 저장하고 저장한 할 일을 리턴한다.
     *
     * @param taskParam 입력한 할 일
     * @return JSON으로 리턴될 저장된 할 일
     */
    public TaskInfo insertTask(TaskDto.TaskParam taskParam) {
        return new TaskInfo(taskRepository.save(taskParam.toEitity()));
    }

    /**
     * 수정할 할 일 ID와 수정내용을 받아서 해당 ID의 할 일을 수정내용으로 수정 후
     * 수정된 할 일을 리턴한다.
     *
     * @param taskParam 수정 할 일
     * @return JSON으로 리턴될 수정된 할 일
     */
    public TaskInfo updateTask(TaskDto.TaskParam taskParam) {
        return new TaskInfo(taskRepository.update(taskParam.toEitity()));

    }

    /**
     * 삭제할 할 일 ID를 받아서 해당 할 일을 삭제한다.
     *
     * @param id 삭제할 할 일 ID
     */
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }
}
