package com.example.mad_project2024.screens



import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.BottomBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailModeScreen() {

    Scaffold (

        topBar = {
            TopAppBar(title = stringResource(id = R.string.travel_mode))
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
            CategoryCard(title = stringResource(R.string.com_general), description = stringResource(R.string.com_description_general))
            Spacer(Modifier.size(64.dp))
            CategoryCard(title = stringResource(R.string.com_communication), description = stringResource(R.string.com_description_communication))
            Spacer(Modifier.size(64.dp))
            CategoryCard(title = stringResource(R.string.com_travel), description = stringResource(R.string.com_description_travel))
        }

    }

}





@Composable
fun CategoryCard(title: String, description: String, modifier:Modifier = Modifier) {

    var showDetails by remember {
        mutableStateOf(true)
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()


    ) {

        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = title,
                modifier = Modifier,
                style = MaterialTheme.typography.titleMedium
            )
            Icon(modifier = Modifier
                .clickable {
                    showDetails = !showDetails
                },
                imageVector =
                if (showDetails) Icons.Filled.KeyboardArrowDown
                else Icons.Default.KeyboardArrowUp, contentDescription = "show more")

        }

        //Spacer(Modifier.size(8.dp))

        Text(
            text = description,
            modifier = Modifier.padding(16.dp, top = 0.dp, bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }

}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopAppBar1(title: String) {
//
//    CenterAlignedTopAppBar(
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer,
//            titleContentColor = MaterialTheme.colorScheme.primary,
//        ),
//
//        title = {
//            Text(
//                title,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//            )
//        },
//        navigationIcon = {
//            IconButton(onClick = { /* do something */ }) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "Localized description"
//                )
//            }
//        },
//        actions = {
//            IconButton(onClick = { /* do something */ }) {
//                Icon(
//                    imageVector = Icons.Filled.Menu,
//                    contentDescription = "Localized description"
//                )
//            }
//        },
//
//        )
//
//}
//
//
//@Composable
//fun BottomBar1(
//
//) {
//    BottomAppBar(
//        actions = {
//            Row(modifier = Modifier
//                .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween)
//            {
//                IconButton(onClick = { /* do something */ }) {
//                    Icon(Icons.Filled.Home, contentDescription = "Localized description")
//                }
//                IconButton(onClick = { /* do something */ }) {
//                    Icon(
//                        Icons.Filled.Favorite,
//                        contentDescription = "Localized description",
//                    )
//                }
//                IconButton(onClick = { /* do something */ }) {
//                    Icon(
//                        Icons.Filled.AccountCircle,
//                        contentDescription = "Localized description",
//                    )
//                }
//            }
//
//
//        }
//    )
//}

@Composable
fun IconCategory(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopEnd
    ){
        Icon(
            modifier = Modifier.clickable {
                onFavoriteClick()
                Log.i("Category", "icon clicked")
            },
            tint = MaterialTheme.colorScheme.secondary,
            imageVector =
            if (isFavorite) {
                Icons.Filled.KeyboardArrowDown
            } else {
                Icons.Default.FavoriteBorder
            },

            contentDescription = "Add to favorites")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailModeScreenPreview() {
    MovieAppMAD24Theme {
        DetailModeScreen()
    }
}