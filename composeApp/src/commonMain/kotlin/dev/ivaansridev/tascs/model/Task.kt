package dev.ivaansridev.tascs.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int,
    val title: String,
    val subtitle: String? = null,
    val dueDate: LocalDate? = null,
    val done: Boolean = false,
    val createdAt: Long = 0L,
    val modifiedAt: Long = 0L,
)
