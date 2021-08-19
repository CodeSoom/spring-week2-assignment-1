//Todo
// 5. build. gradel Tpfvm flqb

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;


@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

  private Long id = 0L;
  private HashMap<Long, Task> tasks = new HashMap();

  /**
   * 모든 할일들을 리턴합니다.
   *
   * @return 할일들을 모두 리턴
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Task> taskList() {
    return tasks
        .values()
        .stream()
        .collect(Collectors.toList());
  }
  /**
   * 주어진 id에 해당하는 할 일을 검색해 리턴합니다.
   *
   * @param id 할 일의 id
   * @return 감섹걀거ㅣ 칮이넨 할 일
   * @throws TaskNotFoundException 할일을 찾지 못한 경우
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task targetTask(@PathVariable("id") Long id) throws TaskNotFoundException {
    if (!tasks.containsKey(id)) {
      throw new TaskNotFoundException( "Task not found with that id :" +id);
    }
    System.out.println(tasks.get(id));
    return tasks.get(id);
  }

  /**
   * body 문자열을 받아 할 일을 생성하고 리턴합니다.
   *
   * @param task 할 일의 내용
   * @return 만들어진 할 일
   * @throws BadRequest body 문자열이 없는 경우
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task create(@RequestBody Task task) throws BadRequest {
    Long generatedId = generateId();
    task.setId(generatedId);
    tasks.put(generatedId, task);
    return task;
  }

  /**
   * id와 body 문자열을 받아 해당 id에 해당하는 할 일 내용을 수정한후, 해당 할 일을 return합니다.
   *
   * @param rewrittenTask 할 일의 수정된 내용
   * @param id 수정할 할 일의 id
   * @return 수정된 할 일
   * @throws TaskNotFoundException 해당 id에 해당하는 할일을 찾지 못한 경우
   */
  @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
  @ResponseStatus(HttpStatus.OK)
  public Task rewrite(@RequestBody Task rewrittenTask, @PathVariable("id") Long id)
      throws TaskNotFoundException {

    if (!tasks.containsKey(id)) {
      throw new TaskNotFoundException("Task not found with that id :" +id);
    }
    Task targetTask = tasks.get(id);
    targetTask.setTitle(rewrittenTask.getTitle());
    return targetTask;
  }

  /**
   * 해당 id에 해당하는 task 객체를 삭제합니다.
   *
   * @param id 삭제하고 싶은 할 일의 id
   * @throws TaskNotFoundException 해당 id에 해당하는 할일을 찾지 못한 경우
   */
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) throws TaskNotFoundException {
    if (!tasks.containsKey(id)) {
      throw new TaskNotFoundException("Task not found with that id :" +id);
    }
    tasks.remove(id);
  }


  private synchronized Long generateId() {
    return ++id;
  }

}
