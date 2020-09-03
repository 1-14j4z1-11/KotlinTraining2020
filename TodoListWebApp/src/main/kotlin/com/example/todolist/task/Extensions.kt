package com.example.todolist.task

import org.springframework.jdbc.core.JdbcTemplate
import java.lang.AssertionError


inline fun <reified T> JdbcTemplate.queryForObject(sql: String): T {
    return queryForObject(sql, T::class.java) ?: throw AssertionError()
}