package com.vijay.time_table_fin

import android.app.Application
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val allUsers: LiveData<List<User>>
    val userRepository: UserRepository
    val auth: FirebaseAuth
    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        userRepository = UserRepository(userDao)
        allUsers = userRepository.users
        auth = userRepository.auth
    }

    private fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.addUser(user)
        }
    }

    private fun getNewUserEntry(email: String, branch: String, sem: String, div: String) : User {
        return User(
            email = email,
            branch = branch,
            sem = sem,
            div = div,
            subjects = "-,-,-,-,-,-,-,-," + "-,-,-,-,-,-,-,-," + "-,-,-,-,-,-,-,-," + "-,-,-,-,-,-,-,-," + "-,-,-,-,-,-,-,-"
        )
    }

    fun addNewUser(email: String, branch: String, sem: String, div: String) {
        val newUser = getNewUserEntry(email, branch, sem, div)
        addUser(newUser)
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            userRepository.updateUser(user)
        }
    }
}