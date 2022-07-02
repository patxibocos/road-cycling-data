package io.github.patxibocos.mycyclist.ui.teams

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import io.github.patxibocos.mycyclist.data.Rider
import io.github.patxibocos.mycyclist.ui.preview.riderPreview
import io.github.patxibocos.mycyclist.ui.preview.teamPreview
import io.github.patxibocos.mycyclist.ui.util.rememberFlowWithLifecycle

@Composable
internal fun TeamRoute(
    onRiderSelected: (Rider) -> Unit = {},
    onBackPressed: () -> Unit = {},
    viewModel: TeamViewModel = hiltViewModel()
) {
    val teamViewState by viewModel.teamViewState.rememberFlowWithLifecycle(
        viewModel.viewModelScope,
        TeamViewState.Empty
    )
    TeamScreen(
        teamViewState = teamViewState,
        onRiderSelected = onRiderSelected,
        onBackPressed = onBackPressed
    )
}

@Preview
@Composable
internal fun TeamScreen(
    teamViewState: TeamViewState = TeamViewState(
        teamPreview,
        listOf(riderPreview)
    ),
    onRiderSelected: (Rider) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    Column {
        SmallTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent
            ),
            title = {
                Text(text = teamViewState.team?.name.toString())
            },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        )
        if (teamViewState.team != null) {
            Text(text = teamViewState.team.name)
            RidersList(teamViewState.riders, onRiderSelected)
        }
    }
}

@Composable
private fun RidersList(riders: List<Rider>, onRiderSelected: (Rider) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = riders, key = Rider::id, itemContent = { rider ->
            RiderRow(rider, onRiderSelected)
        })
    }
}

@Composable
private fun RiderRow(rider: Rider, onRiderSelected: (Rider) -> Unit) {
    Text(
        text = rider.lastName,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRiderSelected(rider) }
    )
}
