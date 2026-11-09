package ca.gbc.comp3074.kenny_john.lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.gbc.comp3074.kenny_john.lab04.ui.theme.Lab04Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(this)
        enableEdgeToEdge()
        setContent {
            Lab04Theme {
                val viewModel = CustomerViewModelFactory(
                    database.customerDao()
                ).create(CustomerViewModel::class.java)
                CustomerApp(viewModel)
            }
        }
    }
}

@Composable
fun CustomerApp(viewModel: CustomerViewModel){
    val customers = viewModel.customers.collectAsState(initial = emptyList())

    CustomerScreen(
        customers = customers.value,
        onAddCustomer = viewModel::addCustomer,
        onDeleteCustomer = viewModel::deleteCustomer
    )

}


@Composable
fun CustomerScreen(
    customers: List<Customer>,
    onAddCustomer: (String, Int, Boolean) -> Unit,
    onDeleteCustomer: (Customer) -> Unit
){
    val name = remember { mutableStateOf("")}
    val ageTxt = remember { mutableStateOf("")}
    val isActive = remember { mutableStateOf(false)}
    val message = remember { mutableStateOf<String?>(null)}

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
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    ageTxt.value = newValue
                }
            },
            label = { Text("Age")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Active")
            Spacer(Modifier.weight(1f))
            Switch(
                checked = isActive.value,
                onCheckedChange = { isActive.value = it}
            )
        }

        Button(onClick = {
            val nameVal = name.value
            val age = ageTxt.value.toIntOrNull()
            if (nameVal.isBlank()) {
                message.value = "Name cannot be blank"
            } else if (age == null) {
                message.value = "Age must be a number"
            } else {
                onAddCustomer(nameVal.trim(), age, isActive.value)
                name.value = ""
                ageTxt.value = ""
                isActive.value = false
            }
        } ) {
            Text(text = "Add Customer")
        }

        CustomerList(
            customers = customers,
            onDeleteCustomer = onDeleteCustomer
        )
    }
}

@Composable
fun CustomerList(
    customers: List<Customer>,
    onDeleteCustomer: (Customer) -> Unit
) {
    if(customers.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No customers found")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(
                items = customers, key = {customer -> customer.id }
            ) {
                customer ->
                CustomerItem(
                    customer = customer,
                    onDeleteCustomer = onDeleteCustomer
                )
            }
        }
    }

}

@Composable
fun CustomerItem(
    customer: Customer,
    onDeleteCustomer: (Customer) -> Unit
) {
    Card(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
        ){
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "${customer.name} (${customer.age})")
                Text(text = if (customer.isActive) "Active" else "Inactive")
            }
            Button(onClick = { onDeleteCustomer(customer) }) {
                Text(text = "Delete")
            }

        }
    }
}