package com.example.mad_project2024.screens



import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_project2024.R
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.data.categories
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.data.subcategoryData


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainCategoryScreen(navController: NavController) {

    Scaffold(

        topBar = {
            TopAppBar(title = stringResource(id = R.string.travel_mode), navController)
        },
        bottomBar = {
            BottomBar(navController)
        }

    ) { innerPadding ->
//

        MainView()
    }



}


@Composable
fun MainView() {



        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,



            ) {

            item {
                CategoryCard(title = stringResource(R.string.com_general), description = stringResource(R.string.com_description_general))
                Spacer(Modifier.size(56.dp))
            }
            item {
                CategoryCard(title = stringResource(R.string.com_communication), description = stringResource(R.string.com_description_communication))
                Spacer(Modifier.size(56.dp))
            }
            item {
                CategoryCard(title = stringResource(R.string.com_travel), description = stringResource(R.string.com_description_travel))
            }



        }


}


@Composable
fun CategoryCard(title: String, description: String) {

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

            if (title == stringResource(id = R.string.com_general)) {
                SubCategoryListGeneral()
            }
            if (title == stringResource(id = R.string.com_communication)) {
                SubCategoryListCommunication()
            }
            if (title == stringResource(id = R.string.com_travel)) {
                SubCategoryListTravelGuide()
            }

        }

    }

}


@Composable
fun SubCategoryCard(subcategory: String, description: String) {

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                //navController.navigate(route = Screen.SubCategoryScreen.route)
            }
    ) {
        Column {
            Text(
                text = subcategory,
                modifier = Modifier
                    .padding(8.dp, bottom = 0.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}



@Composable
fun SubCategoryListGeneral() {
    Column {
        SubCategoryCard(description = "Information about public holidays.", subcategory = "Public Holidays" )
        SubCategoryCard(description = "Details about the healthcare system.", subcategory = "Healthcare-System")
        SubCategoryCard(description = "Current restrictions in place.", subcategory = "Restrictions")
        SubCategoryCard(description = "Some fun facts.", subcategory = "Fun facts")
        SubCategoryCard(description = "Important dos and don’ts.", subcategory = "Dos and Don’ts")
    }

}
@Composable
fun SubCategoryListCommunication() {
    Column {
        SubCategoryCard(description = "Common greetings used locally.", subcategory = "Local greetings")
        SubCategoryCard(description = "Proper etiquette to follow.", subcategory = "Etiquette")
        SubCategoryCard(description = "What to expect when communicating.", subcategory = "Expectations")
        SubCategoryCard(description = "Popular phrases and their meanings.", subcategory = "Popular phrases")
        SubCategoryCard(description = "Common gestures and facial expressions.", subcategory = "Gestures and facial expressions")
    }

}
@Composable
fun SubCategoryListTravelGuide() {
    Column {
        SubCategoryCard(description = "Information about public transport.", subcategory = "Public Transport")
        SubCategoryCard(description = "Payment norms and methods.", subcategory = "Payment norms")
        SubCategoryCard(description = "Guide to traveling by car.", subcategory = "Traveling by car")
        SubCategoryCard(description = "Guide to traveling on foot.", subcategory = "Traveling on foot")
        SubCategoryCard(description = "Potential risks and dangers.", subcategory = "Risks and dangers")
        SubCategoryCard(description = "Shopping tips and locations.", subcategory = "Shopping")
        SubCategoryCard(description = "Local food and gastronomy.", subcategory = "Gastronomy")
    }

}


@Preview(showBackground = true)
@Composable
fun MainCategoryScreenPreview() {
    MovieAppMAD24Theme {
        MainCategoryScreen(rememberNavController())
    }
}