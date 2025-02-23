package com.app.todopp.controller;

import com.app.todopp.models.Task;
import com.app.todopp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")  // Base URL for all endpoints in this controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Fetch all tasks
    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";  // This should match the Thymeleaf template name (tasks.html in templates)
    }

    // Add a new task
    @PostMapping
    public String createTask(@RequestParam String title) {
        taskService.createTask(title);
        return "redirect:/tasks";  // Redirect to updated task list
    }

    // Delete a task by ID (with error handling)
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
        } catch (Exception e) {
            System.out.println("Error while deleting task: " + e.getMessage());
        }
        return "redirect:/tasks";
    }

    // Toggle task completion status
    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        try {
            taskService.toggleTask(id);
        } catch (Exception e) {
            System.out.println("Error while toggling task: " + e.getMessage());
        }
        return "redirect:/tasks";
    }
}
