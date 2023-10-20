package com.task.task.api.model

data class WorkingDay(
    val end_time: String,
    val is_24: Int,
    val start_time: String,
    val working_day: String
)