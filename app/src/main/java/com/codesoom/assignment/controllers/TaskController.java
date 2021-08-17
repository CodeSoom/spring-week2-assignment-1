//todo
// 2. @ExceptionHandler가 Exception.class를 갖는 건 처리 범위가 필요 이상으로 커 보이네요. 좀 더 구체화된 예외를 정의하고, 새로 정의한 예외를 사용해 보세요.
// 3.다양한 예외가 발생 가능할 텐데 NOT_FOUND로만 응답하는 것은 아쉬운 일이겠죠.
// 4. task를 이렇게 찾으면 엄청나게 많은 task가 등록되었을 때 퍼포먼스 이슈가 있을 거에요. 이걸 어떻게 고치면 좋을까요?
// 6. 응답 코드는 요구사항을 전달해준 사람과 적절히 논의해서 결정하면 됩니다. 여기서 요구사항은 e2e 테스트 코드죠. DELETE에 대한 응답 코드로 200을 쓰는 것이 e2e 테스트 코드를 만족한다면 자유롭게 작업하셔도 문제 없습니다. 자 여기에서 한 가지 더 생각해 볼 문제가 생깁니다.
//
//e2e 테스트를 그대로 두고 작업을 할 것인가? 아니면 e2e 테스트를 수정할 것인가?

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  Long id = 0L;
  private HashMap<Long, Task> tasks = new HashMap();

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Task> taskList() {
    return tasks
        .values()
        .stream()
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task targetTask(@PathVariable("id") Long id) {

    return tasks.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task create(@RequestBody Task task) throws Throwable {
    Long generatedId = generateId();
    task.setId(generatedId);
    tasks.put(generatedId,task);
    return task;
  }


  @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
  @ResponseStatus(HttpStatus.OK)
  public Task rewrite(@RequestBody Task rewrittenTask, @PathVariable("id") Long id)
      throws Throwable {
    Task targetTask = tasks.get(id);
    targetTask.setTitle(rewrittenTask.getTitle());
    return targetTask;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) {
    tasks.remove(id);
    return;
  }


  private Long generateId() {
    return ++id;
  }

}
