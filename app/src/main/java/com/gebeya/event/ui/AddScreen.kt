package com.gebeya.event.ui

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gebeya.event.R
import com.gebeya.event.data.db.model.Events
import com.gebeya.event.viewmodel.MainActivityViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEvent(
    saveEvent: ( event: Events) -> Unit,
    mainViewModel: MainActivityViewModel
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var name by remember { mutableStateOf("") }
        var location by remember { mutableStateOf("") }
        var id by remember {
            mutableStateOf("")
        }

        val verticalPadding = 20.dp
        val horizontalPadding = 10.dp

        var nameError by remember { mutableStateOf("") }
        var idError by remember { mutableStateOf("") }

        val context = LocalContext.current

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = horizontalPadding, vertical = verticalPadding
                ),
            text = stringResource(id = R.string.add_event),
            style = MaterialTheme.typography.titleMedium
        )

        TextField(
            modifier= Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = horizontalPadding, vertical = verticalPadding
                ),
            value = name,
            onValueChange = {
                name = it
            },
            supportingText = {
                if(nameError.isNotEmpty()) Text(text = nameError)
            },
            isError = nameError.isNotEmpty()
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = horizontalPadding, vertical = verticalPadding
                ),
            value = location,
            onValueChange = {
                location = it
            },
            placeholder = {
                Row {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
                    Text(text = "Location")
                }
            },
            singleLine = true
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = horizontalPadding, vertical = verticalPadding
                ),
            value = id,
            onValueChange = {
                id = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
                IconButton(onClick = {
                    Toast.makeText(context, "Leading icon clicked", Toast.LENGTH_LONG).show()
                }) {
                    Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = "")
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    Toast.makeText(context, "Trailing icon clicked", Toast.LENGTH_LONG).show()
                }) {
                    Icon(imageVector = Icons.Rounded.Call, contentDescription = "")
                }
            },
            supportingText = {
                if(idError.isNotEmpty()) Text(text = "supporting text")
            },
            isError = idError.isNotEmpty()
        )

        Button(onClick = {
            if(!mainViewModel.isNameValid(name)){
                nameError = "Name is required"
            }else if(!mainViewModel.isIdValid(id)){
                idError = "Id is required"
            }else{
                saveEvent(
                    Events(
                        name = name,
                        location = location,
                        date = LocalDate.now(),
                        image = R.drawable.placeholder,
                        id = id.toInt()
                    )
                )
            }
        }) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}