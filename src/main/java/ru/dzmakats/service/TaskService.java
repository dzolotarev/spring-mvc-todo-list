package ru.dzmakats.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dzmakats.entity.Status;
import ru.dzmakats.entity.Task;
import ru.dzmakats.repository.TaskRepository;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * Created by Denis Zolotarev on 12.02.2024
 */

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAll(int offset, int limit) {
        return taskRepository.getAll(offset, limit);
    }

    public int getAllCount() {
        return taskRepository.getAllCount();
    }

    @Transactional
    public Task edit(Long id, String description, Status status) {
        Task task = taskRepository.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Task id not found"); //по хорошему; ошибки надо отправлять в контроллер
        }
        task.setDescription(description);
        task.setStatus(status);
        taskRepository.saveOrUpdate(task);
        return task;
    }

    public Task create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskRepository.saveOrUpdate(task);
        return task;
    }

    @Transactional
    public void delete(long id) {
        Task task = taskRepository.getById(id);
        if (isNull(task)) {
            throw new RuntimeException("Task id not found"); //по хорошему; ошибки надо отправлять в контроллер
        }
        taskRepository.delete(task);
    }
}
