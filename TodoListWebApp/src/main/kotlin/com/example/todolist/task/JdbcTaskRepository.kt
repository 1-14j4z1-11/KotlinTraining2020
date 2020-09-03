package com.example.todolist.task

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Class that implements [TaskRepository].
 * This class uses Jdbc database.
 */
@Repository
class JdbcTaskRepository(private val jdbcTemplate: JdbcTemplate) : TaskRepository {
    private val rowMapper = RowMapper<Task> { rs, _ ->
        Task(rs.getLong("id"), rs.getString("content"), rs.getBoolean("done"))
    }

    override fun create(content: String): Task {
        this.jdbcTemplate.update("INSERT INTO task(content) VALUES(?)", content)

        // Since SQL statement 'last_insert_id()' cannot be used, get last id by using findAll()
        //val id = this.jdbcTemplate.queryForObject<Long>("SELECT last_insert_id()") ?: throw AssertionError()
        val id = this.findAll().asSequence().map { it.id }.max() ?: throw AssertionError()

        return Task(id, content, false)
    }

    override fun update(task: Task) {
        this.jdbcTemplate.update("UPDATE task SET content = ?, done = ? WHERE id = ?",
        task.content,
        task.done,
        task.id)
    }

    override fun findAll(): List<Task> {
        return this.jdbcTemplate.query("SELECT id, content, done FROM task", this.rowMapper)
    }

    override fun findById(id: Long): Task? {
        return this.jdbcTemplate.query("SELECT id, content, done FROM task WHERE id = ?", this.rowMapper, id).firstOrNull()
    }
}