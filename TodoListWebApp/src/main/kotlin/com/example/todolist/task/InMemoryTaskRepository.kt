package com.example.todolist.task

import org.springframework.stereotype.Repository

/**
 * Class that implements [TaskRepository].
 * This class holds tasks in memory.
 */
@Repository
class InMemoryTaskRepository : TaskRepository {
    private val tasks = mutableListOf<Task>()

    override fun findAll(): List<Task> {
        return this.tasks.toList()
    }

    override fun find(predicate: (Task) -> Boolean): Task? {
        return this.tasks.find(predicate)
    }

    override fun create(content: String): Task {
        val newTask = Task(this.getMaxId() + 1, content, false)
        this.tasks.add(newTask)
        return newTask
    }

    override fun update(task: Task) {
        this.tasks.replaceAll { if(it.id == task.id) task else it }
    }

    /**
     * Removes all tasks that satisfy [predicate].
     * @return Returns true if at least one task is removed, otherwise false.
     */
    fun removeIf(predicate: (Task) -> Boolean): Boolean {
        return this.tasks.removeIf(predicate)
    }

    /**
     * Gets a max number id from all tasks.
     * Returns 0 if no task exist.
     */
    private fun getMaxId(): Long {
        return this.tasks.map(Task::id).max() ?: 0
    }
}