package ca.gbc.comp3074.kenny_john.lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.gbc.comp3074.kenny_john.lab04.ui.theme.Lab04Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab04Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    )
                }
            }
        }
    }
}


@Composable
fun CustomerScreen(
    customers: List<Customer>,
    onAddCustomer: (String, Int, Boolean) -> Unit,
    onDeleteCustomer: (Customer) -> Unit
){
    var name = remember { mutableStateOf("")}
    var ageTxt = remember { mutableStateOf("")}
    var isActive = remember { mutableStateOf("")}
    var message = remember { mutableStateOf("")}

    Column(
        Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Lab04")
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it},
            label = { Text("Name")},
            singleLine = true,
            modifier = Modifier.fillMaxSize()

        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = ageTxt.value,
            onValueChange = {
                newValue ->
                if()
                            },
            label = { Text("Name")},

            singleLine = true,
            modifier = Modifier.fillMaxSize()

        )

    }
}