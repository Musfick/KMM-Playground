package com.musfick.playground


import dev.icerock.moko.mvvm.flow.CFlow
import dev.icerock.moko.mvvm.flow.cFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SampleViewModel(private val useCase: SampleUseCase) : ViewModel() {
    fun getStringFromUseCase(): String = useCase.getString()

    private val _uiState: MutableStateFlow<UIState<String>> = MutableStateFlow<UIState<String>>(UIState.Loading)
    val uiState: CFlow<UIState<String>> = _uiState.cStateFlow().cFlow()


    init {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            try {
                delay(5000)
                throw Exception("wait what !")
                _uiState.value = UIState.Data("Fetched data from server")
            }catch (e:Exception){
                _uiState.value = UIState.Error(e)
            }
        }
    }
}
