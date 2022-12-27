package com.hrappv.ui.feature.login

import com.hrappv.data.repo.MyRepo
import com.hrappv.util.ViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val myRepo: MyRepo,
    // Inject your repos here...
) : ViewModel() {


    private val _isUserAuthenticated = MutableStateFlow(false)
    val userAuthenticated: StateFlow<Boolean> = _isUserAuthenticated

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun login(user: String?, pass: String?) {
        launchOnIO {
            try {
                println("userAuthenticate .....")
//                val users = flow {
//                    myRepo.users.getAllUsers().collect {
//                        emit(it)
//                        println(" ..... users  ${it.toString()}")
//
//                    }
//                }
                val users =   myRepo.users.getAllUsers()
                println(" ..... users  ${users.toString()}")

                val user = myRepo.users.getUser(user!!)
                println("userAuthenticate ..... user is ${user.toString()}")
                if (user != null) {
                    if (user.pass == pass) {
                        println("userAuthenticate ..... user is ${user.toString()} succc")

                        _isUserAuthenticated.emit(true)

                    } else {
                        println("userAuthenticate ..... user is ${user.toString()} faild")

                        _errorState.emit("user name or password not valid")

                    }
                } else {
                    _errorState.emit("some thing goes wrong")

                }
//                val authReq = UsernamePasswordAuthenticationToken(user, pass)
//                val auth: Authentication = authManager.authenticate(authReq)
//                val sc = SecurityContextHolder.getContext()
//                sc.authentication = auth
//                _isUserAuthenticated.auth.emit(auth.isAuthenticated)
//                _isUserAuthenticated.emit(true)
            } catch (e: Exception) {
                _errorState.emit(e.message)
            }
        }
    }


    fun emitError(error: String?) {
        launchOnIO {
            _errorState.emit(null)
        }
    }
}