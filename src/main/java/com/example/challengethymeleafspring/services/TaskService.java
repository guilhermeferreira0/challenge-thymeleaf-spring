package com.example.challengethymeleafspring.services;

import com.example.challengethymeleafspring.controllers.dtos.TaskDto;
import com.example.challengethymeleafspring.controllers.dtos.UpdateTaskDto;
import com.example.challengethymeleafspring.infra.entity.Task;
import com.example.challengethymeleafspring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task create(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setStatus(taskDto.status());
        task.setPriority(taskDto.priority());

        String dateStr = taskDto.date();
        Instant instant = LocalDate.parse(dateStr)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();
        task.setDate(instant);

        return taskRepository.save(task);
    }

    public Task update(UpdateTaskDto updateTask) {
        Task task = taskRepository.findById(updateTask.id()).orElseThrow(() -> new RuntimeException("Task not found"));

        if (updateTask.taskDto().date() != null) task.setTitle(updateTask.taskDto().title());
        if (updateTask.taskDto().description() != null) task.setDescription(updateTask.taskDto().description());
        if (updateTask.taskDto().status() != null) task.setStatus(updateTask.taskDto().status());
        if (updateTask.taskDto().priority() != null) task.setPriority(updateTask.taskDto().priority());

        return taskRepository.save(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public void delete(String id) {
        taskRepository.deleteById(id);
    }

    public Task getById(String id) {
        return taskRepository.findById(id).orElse(null);
    }
}
