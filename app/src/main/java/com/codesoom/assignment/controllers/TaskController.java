//Todo
// 5. build. gradel Tpfvm flqb

package com.codesoom.assignment.controllers;

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


@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

  private Long id = 0L;
  private HashMap<Long, Task> tasks = new HashMap();

  /**
   * http get 요청을 받아 알일들을 리턴합니다.
   *
   * @return tasks를 list로 반환
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
   * http get 요청을 id와 함꼐 받아서 그 해당 id에 해당하는 할일을 반환합니다.
   *
   * @param id
   * @return 해당 id task
   * @throws HttpClientErrorException task가 없는 id로 요청시
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task targetTask(@PathVariable("id") Long id) throws HttpClientErrorException {
    if (!tasks.containsKey(id)) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }
    System.out.println(tasks.get(id));
    return tasks.get(id);
  }

  /**
   * http요청의 body 문자열을 받아 할일 객체를 생성하고 리턴합니다.
   *
   * @param task
   * @return 힐일 객체
   * @throws Throwable
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task create(@RequestBody Task task) throws Throwable {
    Long generatedId = generateId();
    task.setId(generatedId);
    tasks.put(generatedId, task);
    return task;
  }

  /**
   * patch나 put 요청의 경우, id와 body 문자열을 받아 해당 id에 해당하는 task 객체의 내용을 수정한후, 해당 task 객체를 return합니다.
   *
   * @param rewrittenTask
   * @param id
   * @return 수정한 task객체 반환
   * @throws Throwable
   */
  @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
  @ResponseStatus(HttpStatus.OK)
  public Task rewrite(@RequestBody Task rewrittenTask, @PathVariable("id") Long id)
      throws Throwable {
    Task targetTask = tasks.get(id);
    targetTask.setTitle(rewrittenTask.getTitle());
    return targetTask;
  }

  /**
   * id를 받아서 해당 id에 해당하는 task 객체를 삭제합니다.
   *
   * @param id
   * @throws HttpClientErrorException
   */
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) throws HttpClientErrorException {
    if (!tasks.containsKey(id)) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }
    tasks.remove(id);
  }


  private synchronized Long generateId() {
    return ++id;
  }

}
