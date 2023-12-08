package com.gebeya.event.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.gebeya.event.R
import com.gebeya.event.data.db.model.Events
import com.gebeya.event.ui.common.EventCard

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navBack: () -> Unit,
    navToAddEvent: () -> Unit,
    listOfEvent: List<Events>
) {

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
        Button(onClick = navToAddEvent) {
            Text(text = "create")
        }
    }) { paddingVal ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingVal)
        ) {
            items(listOfEvent) { event ->
                EventCard(
                    name = event.name, date = event.date, image = event.image
                )
            }
        }
    }
    HomeChild (
        navBack = navBack
    )
}

@Composable
fun HomeChild(
    navBack: () -> Unit,
){

}