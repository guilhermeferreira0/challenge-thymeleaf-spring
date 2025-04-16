package com.example.challengethymeleafspring.controllers;

import com.example.challengethymeleafspring.controllers.dtos.TaskDto;
import com.example.challengethymeleafspring.infra.entity.Task;
import com.example.challengethymeleafspring.repository.TaskRepository;
import com.example.challengethymeleafspring.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity createTask(@RequestBody TaskDto task) {
        System.out.println(task);

        Task taskEntity = taskService.create(task);

        return ResponseEntity.ok(taskEntity);
    }

    @GetMapping(path = {"id"})
    public ResponseEntity getTaskById(@PathVariable("id") String id) {
        Task task = taskService.getById(id);

        return ResponseEntity.ok(task);
    }

    @GetMapping()
    public ResponseEntity getAll() {
        List<Task> tasks = taskService.getAll();

        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteTask(@PathVariable("id") String id) {
        this.taskService.delete(id);

        return ResponseEntity.ok().build();
    }
}
