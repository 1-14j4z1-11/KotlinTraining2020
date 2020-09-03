package com.example.todolist.controller

import com.example.todolist.task.Task
import com.example.todolist.task.TaskRepository
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@WebMvcTest(TaskController::class)
@SpringJUnitWebConfig
class TaskControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var taskRepository: TaskRepository

    @MockBean
    private lateinit var commandLineRunner: CommandLineRunner

    @Test
    fun index_保存されているタスクを全件表示すること() {
        val tasks = listOf(
                Task(123, "hoge", false),
                Task(234, "fuga", true)
        )

        Mockito.`when`(this.taskRepository.findAll())
                .thenReturn(tasks)

        val r = this.mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                r.andExpect(MockMvcResultMatchers.view().name("tasks/index"))
                .andExpect(MockMvcResultMatchers.model().attribute("tasks", tasks))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<span>hoge</span>")))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<s>fuga</s>")))
    }

    @Test
    fun create_ポストされた内容でタスクを新規作成すること() {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                .param("content", "piyo"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tasks"))

        Mockito.verify(this.taskRepository).create("piyo")
    }
}