package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository {

    /**
     * 저장되어 있는 모든 task를 반환합니다.
     *
     * @return 저장된 모든 task를 List 객체에 담아 반환
     */
    List<Task> findAllTasks();

    /**
     * id를 이용해 매핑된 task를 찾아서 반환합니다.
     *
     * @param id 찾고 싶은 task의 id
     * @return id에 매핑된 task가 존재하면 id에 매핑된 task 값을 가진 Optional 객체를 반환하고 그렇지 않으면 Optional.empty()를 반환
     */
    Optional<Task> findById(Long id);

    /**
     * 인자로 주어진 task를 저장하고, 다시 반환합니다.
     *
     * @param task 저장할 task
     * @return 저장된 task
     */
    Task addTask(Task task);

    /**
     * id에 매핑된 task를 삭제하고, 삭제된 task를 반환합니다.
     *
     * @param id 삭제할 task의 id
     * @return id에 매핑된 task가 존재하면 id에 매핑된 task 값을 가진 Optional 객체를 반환하고, 그렇지 않으면 Optional.empty()를 반환
     */
    Optional<Task> deleteById(Long id);

    /**
     * idList에 있는 모든 id에 매핑된 task를 삭제하고 삭제한 task를 Set 객체에 담아서 반환한다.
     * 만일, id에 매핑된 task가 존재하지 않을 경우에는 Set 객체에 담지 않는다.
     *
     * @param idSet 삭제할 id 값을 담은 Set 객체
     * @return 삭제된 task를 담은 Set 객체, 존재하지 않는 task에 대해서는 아무런 처리를 하지 않는다.
     */
    Set<Task> deleteTasks(Set<Long> idSet);

    /**
     * 최근 추가된 task를 quantity 개수만큼 반환한다.
     *
     * @param quantity 반환할 task의 개수
     * @return quantity 개수 만큼의 최근 추가된 task를 담은 List 객체
     */
    List<Task> findRecentlyAddedTasks(int quantity);

    /**
     * 현재 저장되어 있는 task의 총 개수를 반환한다.
     *
     * @return 저장되어 있는 task의 총 개수
     */
    int getQuantityStored();
}
