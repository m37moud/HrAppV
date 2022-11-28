package com.HrAppV.ui.feature.EmployeResult

import androidx.compose.runtime.*
import com.HrAppV.di.AppComponent
import com.HrAppV.ui.feature.main.MainViewModel
import com.HrAppV.ui.navigation.Component
import com.arkivanov.decompose.ComponentContext
import javax.inject.Inject

class EmployResultScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val onBackClickEmpResult: () ->Unit
): Component, ComponentContext by componentContext {
    @Inject
    lateinit var viewModel: EmployeeResultViewModel

    @Inject
    lateinit var mainViewModel: MainViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
            mainViewModel.init(scope)
        }
//        val folderPath by viewModel.folderPath.collectAsState()
//        if(folderPath.isNotBlank()){
//
//        }
        val backToMain by viewModel.backToMain.collectAsState()
//        val backToMain by mainViewModel.startEmpResult.collectAsState()


        if (backToMain)
        {
            println("startEmpResult is $backToMain")
            onBackClickEmpResult()

        }

        EmployeeResultScreen(viewModel
//            ,mainViewModel
        )
    }
}