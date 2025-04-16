package com.example.challengethymeleafspring.controllers;

import com.example.challengethymeleafspring.controllers.dtos.TaskDto;
import com.example.challengethymeleafspring.controllers.dtos.UpdateTaskDto;
import com.example.challengethymeleafspring.controllers.dtos.UserDto;
import com.example.challengethymeleafspring.infra.entity.Task;
import com.example.challengethymeleafspring.services.TaskService;
import com.example.challengethymeleafspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    /* Redirecting login page */
    @GetMapping("/login")
    public ModelAndView getLoginPage(
            @RequestParam(defaultValue = "false", required = false) Boolean error,
            @RequestParam(defaultValue = "false", required = false) Boolean logout
    ) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (error) modelAndView.addObject("error", true);
        if (logout) modelAndView.addObject("logout", true);

        return modelAndView;
    }

    /* Redirecting register page */
    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register");

        return modelAndView;
    }

    /* Redirecting register task */
    @GetMapping("/task/register")
    public ModelAndView getRegisterTaskPage() {
        ModelAndView modelAndView = new ModelAndView("/task/register-task");

        return modelAndView;
    }

    /* Redirecting list task */
    @GetMapping("/task/all")
    public ModelAndView getListTaskPage() {
        ModelAndView modelAndView = new ModelAndView("/task/list-task");

        modelAndView.addObject("tasks", this.taskService.getAll());

        return modelAndView;
    }

    /* Redirecting update task */
    @GetMapping("/task/update/{id}" )
    public ModelAndView getUpdateTaskPage(@PathVariable("id") String id) {

        ModelAndView modelAndView = new ModelAndView("/task/update-task");
        Task task = this.taskService.getById(id);
        modelAndView.addObject("task", task);

        return modelAndView;
    }

    /* Creating new task*/
    @PostMapping("/task/create")
    public ModelAndView createTask(@ModelAttribute("task") TaskDto task) {
        this.taskService.create(task);

        return new ModelAndView("redirect:/task/all");
    }

    /* Creating new user */
    @PostMapping("/register")
    public ModelAndView registerNewUser(@ModelAttribute("user") UserDto user) {
        this.userService.create(user);

        ModelAndView modelAndView = new ModelAndView("redirect:/login");

        return modelAndView;
    }

    /* Updating task */
    @PostMapping("/task/update/{id}")
    public ModelAndView updateTask(
            @ModelAttribute("task") TaskDto task,
            @PathVariable("id") String id
    ) {
        UpdateTaskDto updateTaskDto = new UpdateTaskDto(id, task);

        this.taskService.update(updateTaskDto);

        return new ModelAndView("redirect:/task/all");
    }
}
