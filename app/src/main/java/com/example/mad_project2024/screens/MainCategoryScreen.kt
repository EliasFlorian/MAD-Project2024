package com.example.mad_project2024.screens



import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_project2024.R
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.BottomBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainCategoryScreen() {

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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,


        ) {
            CategoryCard(title = stringResource(R.string.com_general), description = stringResource(R.string.com_description_general))
            Spacer(Modifier.size(56.dp))
            CategoryCard(title = stringResource(R.string.com_communication), description = stringResource(R.string.com_description_communication))
            Spacer(Modifier.size(56.dp))
            CategoryCard(title = stringResource(R.string.com_travel), description = stringResource(R.string.com_description_travel))
        }

    }

}





@Composable
fun CategoryCard(title: String, description: String, modifier:Modifier = Modifier) {

    var showInformation by remember {
        mutableStateOf(true)
    }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = title,
                modifier = Modifier,
                style = MaterialTheme.typography.titleMedium
            )
            Icon(modifier = Modifier
                .clickable {
                    showInformation = !showInformation
                },
                imageVector =
                if (showInformation) Icons.Filled.KeyboardArrowDown
                else Icons.Default.KeyboardArrowUp, contentDescription = "show more")

        }

        AnimatedVisibility(visible = showInformation) {
            Text(
                text = description,
                modifier = Modifier.padding(start = 16.dp, top = 0.dp, bottom = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        AnimatedVisibility(visible = !showInformation) {
            InformationList(informations = listOf(
                "test1",
                "test3"
            ))
        }

    }

}


@Composable
fun InformationCard(information: String, subcategory: String) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column {
            Text(
                text = information,
                modifier = Modifier
                    .padding(8.dp, bottom = 0.dp),
                textAlign = TextAlign.Left,
            )

            Text(
                text = subcategory,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.End,
                fontSize = 10.sp
            )
        }

    }
}

@Composable
fun InformationList(informations: List<String>) {
    LazyColumn(
        //modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        items(informations) { informations ->
            InformationCard(information = stringResource(id = R.string.general1), subcategory = "Public Holidays")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun InformationCardPreview() {
    MovieAppMAD24Theme {
        InformationCard(information = stringResource(id = R.string.general1), subcategory = "Public Holidays")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailModeScreenPreview() {
    MovieAppMAD24Theme {
        MainCategoryScreen()
    }
}