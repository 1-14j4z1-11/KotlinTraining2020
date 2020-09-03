package com.example.todolist.task

/**
 * Interface of task repository.
 */
interface TaskRepository {
    /**
     * Returns a list of tasks.
     */
    fun findAll(): List<Task>

    /**
     * Returns a task whose id matches an argument [id].
     */
    fun findById(id: Long): Task?

    /**
     * Adds a new task to this [TaskRepository], and returns the task to be added.
     */
    fun create(content: String): Task

    /**
     * Update the task whose id matches the argument [task].
     */
    fun update(task: Task)
}