package task

/**
 * Data class retaining a task data.
 * @property id Task ID
 * @property content Task content
 * @property done Value of whether the task is done or not
 */
data class Task(val id: Long, val content: String, val done: Boolean)