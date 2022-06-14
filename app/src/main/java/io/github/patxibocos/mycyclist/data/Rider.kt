package io.github.patxibocos.mycyclist.data

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import java.time.LocalDate

@Immutable
@Stable
data class Rider(
    val id: String,
    val firstName: String,
    val lastName: String,
    val photo: String,
    val country: String,
    val website: String,
    val birthDate: LocalDate,
    val birthPlace: String,
    val weight: Int,
    val height: Int
) {
    fun fullName(): String {
        return "$firstName $lastName"
    }
}
