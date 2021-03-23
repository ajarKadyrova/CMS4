package com.example.customermanagementsystem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.customermanagementsystem.repository.Repository

class RegisterViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  RegisterViewModel(repository) as  T
    }
}