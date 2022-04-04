package com.codesoom.assignment.services;

import com.codesoom.assignment.controllers.TaskController;
import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.networks.BaseResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Nested
@DisplayName(value = "TaskController 테스트에서")
class TaskControllerTest {

    private static TaskController taskController;

    @BeforeAll
    static void init() {
        taskController = new TaskController(new TaskService());
    }

    @Nested
    @DisplayName(value = "readTasks() 매소드는 ")
    class Describe_readTasks {

        @Nested
        @DisplayName("할일목록이 없다면")
        class Context_with_empty_tasks {

            @Test
            @DisplayName("빈 배열을 반환한다.")
            void it_returns_empty_list() {
                //TODO.taskList 를 empty 상태로 만들어야 한다.

                BaseResponse<List<Task>> res = taskController.readTasks();
                List<Task> tasks = res.getBody();

                assertTrue(tasks.isEmpty(), "반환된 배열은 비어있다.");
            }
        }

        @Nested
        @DisplayName("할일목록에 할일이 있다면")
        class Context_with_tasks {

            @Test
            @DisplayName("가지고 있는 모든 할일 리스트를 반환한다.")
            void it_returns_full_list() {
                taskController.addTask(new Task("코드숨 과제하기"));

                BaseResponse<List<Task>> response = taskController.readTasks();
                List<Task> tasks = response.getBody();

                assertTrue(tasks.size() > 0L);
                assertTrue(tasks.contains(new Task(1L, "코드숨 과제하기")));
            }
        }
    }

    @Nested
    @DisplayName("readTask() 매소드는")
    class Describe_readTask {

        @Nested
        @DisplayName("path id 가 정상적일때")
        class Context_with_valid_path {

            @Test
            @DisplayName("id와 일치하는 값이 있다면 > task 내용을 반환한다.")
            void it_returns_single_task() {
                taskController.addTask(new Task(1L, "코드숨 과제하기"));

                BaseResponse<Task> response = taskController.readTask(1L);
                Task task = response.getBody();

                assertEquals(task.getId(), 1L);
                assertTrue(task.getTitle().contains("코드숨 과제하기"));
            }

            @Test
            @DisplayName("id와 일치하는 값이 없다면 > Not Found Exception 을 반환한다.")
            void it_throws_not_found_exception() {
                assertThrows(Exception.class, () -> {
                    taskController.readTask(123L);
                });
            }
        }

        @Nested
        @DisplayName("path id 가 0이하의 값으로 비정상일때")
        class Context_with_invalid_path {

            @Test
            @DisplayName("예외를 발생시킨다.")
            void it_throws_exception() {
                assertThrows(Exception.class, () -> {
                    taskController.readTask(-1L);
                });
            }
        }
    }


    @Nested
    @DisplayName("addTask() 매소드는")
    class Describe_addTask {

        @Nested
        @DisplayName("정상적인 Task 값이 입력되면")
        class Context_normal_task {

            @Test
            @DisplayName("할일 목록에 추가되고, 추가된 할일 객체를 반환한다. ")
            void it_adds_new_task_and_returns_added_task() {
                //given
                BaseResponse<List<Task>> readTaskResponse = taskController.readTasks();
                List<Task> tasks = readTaskResponse.getBody();

                //when
                BaseResponse<Task> addTaskResponse = taskController.addTask(new Task("코드숨 과제하기"));
                Task addedTask = addTaskResponse.getBody();

                //then
                assertTrue(tasks.contains(addedTask));
            }
        }

        @Nested
        @DisplayName("비정상적인 Task 값이 입력되면")
        class Context_abnormal_task {

            @Test
            @DisplayName("오류를 던진다.")
            void it_throws_exception() {
                assertThrows(Exception.class, () -> {
                    taskController.addTask(new Task());
                });
            }
        }
    }

    @Nested
    @DisplayName("editTasks() 매소드는")
    class Describe_editTask {

        @Nested
        @DisplayName("정상적인 path id 이라면")
        class Context_normal_path_id {

            @Nested
            @DisplayName("정상적인 Task 형식이라면")
            class Context_normal_task {

                @Test
                @DisplayName("path id 와 일치하는 task 를 조회 > 제목을 수정한 후 > 수정된 task 를 반환한다.")
                void it_returns_edited_task() {
                    taskController.addTask(new Task(1L, "코드숨 과제하기"));

                    BaseResponse<Task> readResponse = taskController.readTask(1L);
                    Task taskFromDb = readResponse.getBody();

                    BaseResponse<Task> response = taskController.editTask(1L, new Task("코드숨 과제 리펙토링하기"));
                    Task editedTask = response.getBody();

                    assertEquals(taskFromDb.getTitle(), editedTask.getTitle());
                }

            }

            @Nested
            @DisplayName("비정상적인 Task 형식이라면")
            class Context_abnormal_task {

                @Test
                @DisplayName("예외를 반환한다.")
                void editTaskTitle_throwErrorIfNotValidTitle() {
                    assertThrows(Exception.class, () -> {
                        taskController.editTask(1L, new Task(""));
                    });
                }
            }

        }

        @Nested
        @DisplayName("비상적인 path id 이라면")
        class Context_abnormal_path_id {

            @Test
            @DisplayName("예외를 던진다.")
            void it_throws_exception() {
                assertThrows(Exception.class, () -> {
                    taskController.editTask(-1L, new Task("코드숨 과제 리펙토링하기"));
                });
            }
        }
    }

    @Nested
    @DisplayName("deleteTask() 매소드는")
    class Describe_deleteTask {

        @Nested
        @DisplayName("정상적인 path id 일때")
        class Context_valid_path_id {

            @Nested
            @DisplayName("path id 와 일치하는 task를 찾을 수 있을때")
            class Context_has_matched_task {

                @Test
                @DisplayName("id에 맞는 할일 조회 후 삭제한다.")
                void it_returns_204() {
                    taskController.addTask(new Task(1L, "코드숨 과제하기"));

                    BaseResponse response = taskController.deleteTask(1L);
                    int statusCode = response.getStatusCode();

                    assertEquals(statusCode, 204);

                    assertThrows(Exception.class, () -> {
                        taskController.readTask(1L);
                    });
                }
            }

            @Nested
            @DisplayName("path id 와 일치하는 task를 찾을 수 없을때")
            class Context_no_matched_task {

                @Test
                @DisplayName("예외를 던진다.")
                void it_throws_exception() {
                    assertThrows(Exception.class, () -> {
                        taskController.deleteTask(1234L);
                    });
                }
            }

        }

        @Nested
        @DisplayName("비정상적인 path id 일때")
        class Context_invalid_path_id {

            @Test
            @DisplayName("예외를 던진다.")
            void it_throws_exception() {
                assertThrows(Exception.class, () -> {
                    taskController.deleteTask(-1L);
                }, "task id 는 0보다 같거나 작을 수 없습니다.");
            }
        }
    }


}
