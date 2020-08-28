package com.example.todolist.view

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Class for html form to update a task.
 */
class TaskUpdateForm {
    @NotBlank
    @Size(max = 20)
    var content: String? = null

    var done: Boolean = false
}