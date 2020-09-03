package com.example.todolist.controller

import com.example.todolist.task.Task
import com.example.todolist.task.TaskRepository
import com.example.todolist.view.TaskCreateForm
import com.example.todolist.view.TaskUpdateForm
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * Controller class for task requests.
 */
@Controller
@RequestMapping("tasks")
class TaskController(private val taskRepository: TaskRepository) {
    @GetMapping("")
    fun index(model: Model): String {
        val tasks = this.taskRepository.findAll()
        model.addAttribute("tasks", tasks)
        return "tasks/index"
    }

    @GetMapping("new")
    fun new(form: TaskCreateForm): String {
        return "tasks/new"
    }

    @PostMapping("")
    fun create(@Validated form: TaskCreateForm, bindingResult: BindingResult): String {
        if(bindingResult.hasErrors())
            return "tasks/new"

        val content = requireNotNull(form.content)
        this.taskRepository.create(content)
        return "redirect:/tasks"
    }

    @GetMapping("{id}/edit")
    fun edit(@PathVariable("id") id: Long, form: TaskUpdateForm): String {
        val task = this.taskRepository.findById(id) ?: throw NotFoundException()
        form.content = task.content
        form.done = task.done
        return "tasks/edit"
    }

    @PostMapping("{id}")
    fun update(@PathVariable("id") id: Long, @Validated form: TaskUpdateForm, bindingResult: BindingResult): String {
        if(bindingResult.hasErrors())
            return "tasks/edit"

        val task = this.taskRepository.findById(id) ?: throw NotFoundException()
        val newTask = task.copy(content = requireNotNull(form.content), done = form.done)
        this.taskRepository.update(newTask)
        return "redirect:/tasks"
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(): String {
        return "tasks/not_found"
    }
}