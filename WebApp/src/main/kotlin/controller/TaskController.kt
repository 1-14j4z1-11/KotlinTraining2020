package controller

import common.convertToObjectFromJsonString
import common.toJsonString
import spark.Route
import spark.Spark
import task.TaskRepository

/**
 * Controller Class that responds to task request.
 */
class TaskController(private val taskRepository: TaskRepository) {
    /**
     * Returns a [Route] that responds to tasks request.
     */
    fun findAll(): Route {
        return Route { _, res ->
            res.type(ContentType.applicationJson)
            this.taskRepository.findAll().toJsonString()
        }
    }

    /**
     * Returns a [Route] that responds to single task request.
     * @param paramName Param name to get a task id from request params.
     */
    fun findById(paramName: String): Route {
        return Route { req, res ->
            val taskId = req.params(paramName)?.toLongOrNull() ?: throw Spark.halt(404)
            val task = this.taskRepository.find { it.id == taskId } ?: throw Spark.halt(404)

            res.type(ContentType.applicationJson)
            task.toJsonString()
        }
    }

    /**
     * Returns a [Route] that responds to task creation request.
     */
    fun create(): Route {
        return Route { req, res ->
            val createRequest = convertToObjectFromJsonString<CreateRequestJson>(req.body()) ?: throw Spark.halt(400)
            val newTask = this.taskRepository.create(createRequest.content)

            res.status(200)
            res.type(ContentType.applicationJson)
            newTask.toJsonString()
        }
    }

    /**
     * Returns a [Route] that responds to task update request.
     * @param paramName Param name to get a task id from request params.
     */
    fun update(paramName: String): Route {
        return Route { req, res ->
            val taskId = req.params(paramName)?.toLongOrNull() ?: throw Spark.halt(404)
            val task = this.taskRepository.find { it.id == taskId } ?:throw Spark.halt(404)
            val updateRequest = convertToObjectFromJsonString<UpdateRequestJson>(req.body()) ?: throw Spark.halt(400)
            val newTask = task.copy(
                    content = updateRequest.content ?: task.content,
                    done = updateRequest.done ?: task.done)

            this.taskRepository.update(newTask)
            res.status(204)
        }
    }

    /**
     * Returns a [Route] that responds to task removal request.
     * @param paramName Param name to get a task id from request params.
     */
    fun remove(paramName: String): Route {
        return Route { req, res ->
            val taskId = req.params(paramName)?.toLongOrNull() ?: throw Spark.halt(404)

            if(this.taskRepository.removeIf { it.id == taskId }) {
                res.status(204)
            } else {
                res.status(404)
            }
        }
    }

    private companion object {
        /**
         * Object retaining constant values of ContentType.
         */
        object ContentType {
            const val applicationJson = "application/json"
        }
    }
}