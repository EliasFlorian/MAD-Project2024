package com.example.mad_project2024.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mad_project2024.R
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme


@Composable
fun HomeScreen() {
    ModeCard()

}

@Composable
fun ModeCard(

    modifier:Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {

            Text(
                text = stringResource(R.string.travel_mode),
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(Modifier.size(8.dp))

                Text(
                    text = stringResource(R.string.description_travel_mode),
                    //style = MaterialTheme.typography.displaySmall
                )




        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MovieAppMAD24Theme {
        HomeScreen()
    }
}