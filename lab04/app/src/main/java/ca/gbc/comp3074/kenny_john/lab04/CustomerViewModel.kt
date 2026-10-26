package ca.gbc.comp3074.kenny_john.lab04

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CustomerViewModel (
    private var customerDao: CustomerDAO
): ViewModel() {
    val customers: Flow<List<Customer>> = customerDao.getAll()

    suspend fun addCustomer(
        name: String,
        age: Int,
        isActive: Boolean

    ) {
        customerDao.insert(
            Customer(
                name = name,
                age = age,
                isActive = isActive
            )
        )
    }

    suspend fun deleteCustomer(customer:Customer) {
        customerDao.delete(customer)
    }

}