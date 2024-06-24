package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mad_project2024.R
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {

    Scaffold (

        topBar = {
            TopAppBar(title = stringResource(id = R.string.app_name))
        },
        bottomBar = {
            BottomBar()
        }






    ) {innerPadding ->
     



        Column(
modifier = Modifier
.fillMaxSize()
.padding(32.dp),
horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.Center

) {
    ModeCard(title = stringResource(R.string.travel_mode), description = stringResource(R.string.description_travel_mode))
    Spacer(Modifier.size(64.dp))
    ModeCard(title = stringResource(R.string.general_mode), description = stringResource(R.string.description_general_mode))
}

    }



}





@Composable
fun ModeCard(title: String, description: String, modifier:Modifier = Modifier) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(Modifier.size(8.dp))

        Text(
            text = description,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String) {

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },

            )

}


@Composable
fun BottomBar(

) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Home, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = "Localized description",
                )
            }

        }
    )
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MovieAppMAD24Theme {
        HomeScreen()
    }
}