package ru.dzmakats.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dzmakats.dto.TaskDTO;
import ru.dzmakats.entity.Task;
import ru.dzmakats.service.TaskService;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

/**
 * Created by Denis Zolotarev on 12.02.2024
 */

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public String tasks(Model model,
                        @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                        @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        List<Task> tasks = taskService.getAll((page - 1) * limit, limit);
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentPage", page);
        int totalPages = (int) Math.ceil(1.0 * taskService.getAllCount() / limit);
        if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("page_numbers", pageNumbers);
        }
        return "tasks";
    }

    @PostMapping("/{id}")
    public String edit(Model model,
                       @PathVariable("id") Long id,
                       @RequestBody TaskDTO taskDTO) {
        if (isNull(id) || id < 0) {
            throw new RuntimeException("Invalid ID");
        }
        Task task = taskService.edit(id, taskDTO.getDescription(), taskDTO.getStatus());
        return tasks(model, 1, 10);
    }

    @PostMapping("/")
    public String add(Model model,
                      @RequestBody TaskDTO taskDTO) {
        Task task = taskService.create(taskDTO.getDescription(), taskDTO.getStatus());
        return tasks(model, 1, 10);
    }

    @DeleteMapping("/{id}")
    public String delete(Model model,
                         @PathVariable("id") Long id) {
        if (isNull(id) || id < 0) {
            throw new RuntimeException("Invalid ID");
        }
        taskService.delete(id);
        return tasks(model, 1, 10);
    }

}
