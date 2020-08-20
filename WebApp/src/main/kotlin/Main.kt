import controller.TaskController
import spark.ModelAndView
import spark.Spark.*
import spark.template.velocity.VelocityTemplateEngine
import task.TaskRepository
import java.util.*

fun main() {
    val repository = TaskRepository()
    val controller = TaskController(repository)

    get("/tasks", controller.findAll())
    get("/tasks/:taskId", controller.findById(":taskId"))
    post("/tasks", controller.create())
    patch("/tasks/:taskId", controller.update(":taskId"))
    delete("/tasks/:taskId", controller.remove(":taskId"))
}
