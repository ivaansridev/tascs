@file:OptIn(ExperimentalTime::class)

package dev.ivaansridev.tascs.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

fun today(): LocalDate =
    Clock.System
        .now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

fun LocalDate.isOverdue(today: LocalDate = today()): Boolean = this < today

/** Short, human friendly label for a due date. */
fun LocalDate.dueLabel(today: LocalDate = today()): String {
    val days = toEpochDays() - today.toEpochDays()
    return when {
        days < 0 -> "Overdue · ${shortDate(today.year)}"
        days == 0L -> "Due today"
        days == 1L -> "Due tomorrow"
        else -> "Due ${shortDate(today.year)}"
    }
}

private fun LocalDate.shortDate(currentYear: Int): String {
    val month =
        month.name
            .lowercase()
            .replaceFirstChar { it.uppercase() }
            .take(3)
    return if (year == currentYear) "$day $month" else "$day $month $year"
}

/** The Material date picker works with UTC midnight millis. */
fun Long.toLocalDateUtc(): LocalDate = Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC).date

fun LocalDate.toUtcMillis(): Long = atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()
