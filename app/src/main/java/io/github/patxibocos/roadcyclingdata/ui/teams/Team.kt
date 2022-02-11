package io.github.patxibocos.roadcyclingdata.ui.teams

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.patxibocos.pcsscraper.protobuf.RiderOuterClass.Rider
import io.github.patxibocos.roadcyclingdata.ui.data.TeamOfRiders
import io.github.patxibocos.roadcyclingdata.ui.preview.riderPreview
import io.github.patxibocos.roadcyclingdata.ui.preview.teamPreview

@Preview
@Composable
internal fun TeamScreen(
    teamOfRiders: TeamOfRiders = TeamOfRiders(
        teamPreview,
        listOf(riderPreview)
    ),
    onRiderSelected: (Rider) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = teamOfRiders.team.name)
        RidersList(teamOfRiders.riders, onRiderSelected)
    }
}

@Composable
private fun RidersList(riders: List<Rider>, onRiderSelected: (Rider) -> Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = riders, key = Rider::getId, itemContent = { rider ->
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
