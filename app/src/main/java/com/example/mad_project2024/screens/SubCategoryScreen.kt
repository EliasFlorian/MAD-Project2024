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
import com.example.mad_project2024.navigation.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubCategoryScreen() {

    Scaffold (

        topBar = {
            TopAppBar(title = stringResource(id = R.string.travel_mode))
        },
        bottomBar = {
            BottomBar()
        }

    ) {paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,


            ) {
            ContentCard(title = "Public Holidays")

        }

    }

}



@Composable
fun ContentCard(title: String) {

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

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(bottom = 8.dp),
                style = MaterialTheme.typography.titleLarge
            )

            LazyColumn {

                item {
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))
                    InformationCard(content = stringResource(id = R.string.general1))



                }
            }

        }




    }

}


@Composable
fun InformationCard(content: String) {

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {

            }
    ) {
        Column {
            Text(
                text = content,
                modifier = Modifier
                    .padding(8.dp, bottom = 0.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleSmall
            )

//            Text(
//                text = description,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                textAlign = TextAlign.Left,
//                style = MaterialTheme.typography.bodySmall
//            )
        }

    }
}

//@Composable
//fun InformationList(informations: List<String>) {
//    LazyColumn(
//        //modifier = Modifier.horizontalScroll(rememberScrollState())
//    ) {
//        items(informations) { informations ->
//            InformationCard(description = stringResource(id = R.string.general1), subcategory = "Public Holidays")
//
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun InformationCardPreview() {
//    MovieAppMAD24Theme {
//        InformationCard(description = stringResource(id = R.string.general1), subcategory = "Public Holidays")
//    }
//}

@Preview(showBackground = true)
@Composable
fun SubCategoryScreenPreview() {
    MovieAppMAD24Theme {
        SubCategoryScreen()
    }
}