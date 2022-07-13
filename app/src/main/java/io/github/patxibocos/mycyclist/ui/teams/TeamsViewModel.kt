package io.github.patxibocos.mycyclist.ui.teams

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.patxibocos.mycyclist.data.DataRepository
import io.github.patxibocos.mycyclist.data.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(dataRepository: DataRepository) :
    ViewModel() {

    val teamsViewState: Flow<TeamsViewState> = dataRepository.teams.map(::TeamsViewState)
}

@Immutable
data class TeamsViewState(val teams: List<Team>) {
    companion object {
        val Empty = TeamsViewState(teams = emptyList())
    }
}
