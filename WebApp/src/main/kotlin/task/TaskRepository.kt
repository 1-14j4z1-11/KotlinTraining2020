package task

/**
 * Class retaining a task list.
 */
class TaskRepository {
    private val tasks = mutableListOf<Task>()

    /**
     * Returns a list of tasks.
     */
    fun findAll(): List<Task> {
        return this.tasks.toList()
    }

    /**
     * Returns a task that satisfies [predicate]
     * Returns null if no task is found.
     */
    fun find(predicate: (Task) -> Boolean): Task? {
        return this.tasks.find(predicate)
    }

    /**
     * Adds a new task to this [TaskRepository], and returns the task to be added.
     */
    fun create(content: String): Task {
        val newTask = Task(this.getMaxId() + 1, content, false)
        this.tasks.add(newTask)
        return newTask
    }

    /**
     * Update the task whose id matches the argument [task].
     */
    fun update(task: Task) {
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