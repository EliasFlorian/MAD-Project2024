package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_project2024.R
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountScreen(navController: NavController) {

    Scaffold (

        topBar = {
            TopAppBar(title = "Account", navController)
        },
        bottomBar = {
            BottomBar(navController)
        }

    ) {innerPadding ->
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            ProfileCard()
        }


    }

}





@Composable
fun ProfileCard() {

    val showPortfolio = remember {
        mutableStateOf(false)
    }


        Card(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                CustomImage(modifier = Modifier.size(150.dp))

                Divider()

                ProfileInfo()

                Button(
                    onClick = {
                        showPortfolio.value = !showPortfolio.value
                    }
                ) {
                    Text("Log Out",
                        style = MaterialTheme.typography.labelMedium)
                }
    }}

}


@Composable
fun ProfileInfo() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Armin Koch", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
        Text(text = "armin.koch@test.com", modifier = Modifier.padding(3.dp))
        Text(text = "Admin", modifier = Modifier.padding(3.dp), style = MaterialTheme.typography.titleSmall)
    }
}


@Composable
fun CustomImage(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.padding(16.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp
    ) {

        Image(painter = painterResource(id = R.drawable.gael_gaborel_orbisterrae_f7i8ueiykag_unsplash),
            contentDescription = "profile image",
            modifier = modifier.size(100.dp),
            contentScale = ContentScale.Crop)

    }
}



@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    MovieAppMAD24Theme {
        AccountScreen(rememberNavController())
    }
}