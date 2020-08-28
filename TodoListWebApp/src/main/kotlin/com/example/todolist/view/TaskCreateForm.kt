package com.example.todolist.view

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Class for html form to create a task.
 */
class TaskCreateForm {
    @NotBlank
    @Size(max = 20)
    var content: String? = null
}