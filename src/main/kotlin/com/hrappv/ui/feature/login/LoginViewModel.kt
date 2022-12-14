package com.hrappv.ui.feature.login

import com.hrappv.User
import com.hrappv.data.repo.MyRepo
import com.hrappv.ui.security.UserAuthSate
import com.hrappv.util.ViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val myRepo: MyRepo,
    // Inject your repos here...
) : ViewModel() {
    private val _userAuthSate = MutableStateFlow(UserAuthSate())
    val userAuthSate: StateFlow<UserAuthSate> = _userAuthSate

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
                val users = myRepo.users.getAllUsers()
                println(" ..... users  ${users.toString()}")
//                if(users.isEmpty()){
//                    println("user table is empty  .....")
//
//                    myRepo.users.upsertUser(user = "admin", password = "123456", playRole = "admin")
//                    users =   myRepo.users.getAllUsers()
//                    println(" ..... users  ${users.toString()}")
//
//                }

                val user: User? = myRepo.users.getUser(user!!)
                println("userAuthenticate ..... user is ${user.toString()}")
                if (user != null) {
                    if (user.pass == pass) {
                        println("userAuthenticate ..... user is ${user.toString()} succc")
//                        _userAuthSate.value = UserAuthSate(auth = true , username = user.user_name)
                        _userAuthSate.update { currentState ->
                            currentState.copy(auth = true, username = user.user_name)
                        }

//                        _isUserAuthenticated.emit(true)

                    } else {
                        println("userAuthenticate ..... user is ${user.toString()} faild")
//                        _isUserAuthenticated.emit(false)
                        _userAuthSate.update { currentState ->
                            currentState.copy(auth = false ,error = "user name or password is wrong" )
                        }

//                        _userAuthSate.value = UserAuthSate(auth = false , error = "log in failed")

                    }
                } else {
//                    _userAuthSate.value = UserAuthSate(auth = false , error = "some thing goes wrong")
                    _userAuthSate.update { currentState ->
                        currentState.copy(auth = false ,error = "some thing goes wrong" )
                    }

                }
//                val authReq = UsernamePasswordAuthenticationToken(user, pass)
//                val auth: Authentication = authManager.authenticate(authReq)
//                val sc = SecurityContextHolder.getContext()
//                sc.authentication = auth
//                _isUserAuthenticated.auth.emit(auth.isAuthenticated)
//                _isUserAuthenticated.emit(true)
            } catch (e: Exception) {
                _errorState.emit(e.message)
//                _userAuthSate.value = UserAuthSate(auth = false , error = e.message)
                _userAuthSate.update { currentState ->
                    currentState.copy(auth = false ,error = e.message )
                }



            }
        }
    }


    fun emitError(error: String?) {
        launchOnIO {
//            _errorState.emit(null)
//            _userAuthSate.value = UserAuthSate(auth = false , error = error)
            _userAuthSate.update { currentState ->
                currentState.copy(auth = false ,error = error )
            }

        }
    }

    fun logOut(){
        _userAuthSate.update { currentState ->
            currentState.copy(auth = false )
        }

    }
}