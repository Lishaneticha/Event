package com.gebeya.event.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gebeya.event.R
import com.gebeya.event.ui.theme.EventTheme
import com.gebeya.event.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainviewModel: MainActivityViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventTheme {
                val showMore = remember {
                    mutableStateOf(false)
                }

                var goToHome by rememberSaveable {
                    mutableStateOf(false)
                }
                // A surface container using the 'background' color from the theme

                if(goToHome){
                    HomeScreen(
                        navBack = {
                            goToHome = false
                        }
                    )
                }else{
                    Greeting(
                        navToHome = {
                            goToHome = true
                        }
                    )
                }

            }
        }
        mainviewModel.getEvent()
    }
}


data class Events(
    val name: String,
    val location: String,
    val date: LocalDate,
    val image: Int
)

@Composable
fun Greeting(
    navToHome: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to our app!")
        Button(
            onClick = navToHome,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(0.dp),
        ) {
            Text(text = "Continue")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navBack: () -> Unit
) {


    val listOfEvent = mutableListOf(
        Events(
            name = "Music concert",
            location = "Addis Ababa",
            date = LocalDate.now(),
            image = R.drawable.placeholder
        ), Events(
            name = "Talent show",
            location = "Adama",
            date = LocalDate.now(),
            image = R.drawable.placeholder
        )
    )

    for (i in 1..1000) {
        listOfEvent.add(
            Events(
                name = "Talent show",
                location = "Adama",
                date = LocalDate.now(),
                image = R.drawable.placeholder
            )
        )
    }

    var goToDetails by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.events))
        }, navigationIcon = {
            IconButton(onClick = navBack) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack, contentDescription = ""
                )
            }
        })
    }, floatingActionButton = {
        Button(onClick = { goToDetails = true }) {
            Text(text = "create")
        }
    }) { paddingVal ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingVal)
        ) {
            items(listOfEvent) { event ->
                EventCard(
                    name = event.name, date = event.date, image = event.image
                )
            }
        }
    }
}

class Address(val name: String, val streetNum: Int)
class Person(
    val address: Address
)

@Composable
fun EventCard(
    name: String,
    date: LocalDate,
    image: Int
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = image),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = name)
                Text(text = date.toString())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Details Screen")
                },
                navigationIcon = {
                    IconButton(onClick = navBack) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Text(text = "Content")
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    EventTheme {
        DetailsScreen()
    }
}

fun DetailsScreen() {
    TODO("Not yet implemented")
}
