package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryTaskRepository implements TaskRepository {

  private static final Map<Long, Task> store = new HashMap<>();
  private final AtomicLong counter = new AtomicLong();

  @Override
  public Task save(Task task) {
    task.setId(counter.incrementAndGet());
    store.put(task.getId(), task);
    return task;
  }

  @Override
  public Optional<Task> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public List<Task> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public void deleteById(Long id) {
    if (Objects.isNull(id)) {
      throw new IllegalArgumentException("Id cannot be null");
    }
    store.remove(id);
  }
}
