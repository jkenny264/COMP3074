package ca.gbc.comp3074.kenny_john.lab04

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CustomerViewModelFactory (
    private val customerDao: CustomerDAO
): ViewModelProvider.Factory
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(customerDao) as T
        }



        throw IllegalArgumentException("Unknown ViewModel class")
    }
}