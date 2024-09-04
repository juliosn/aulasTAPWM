package com.example.aulas_tapwm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.aulas_tapwm.roomDB.Pessoa
import kotlinx.coroutines.launch

class PessoaViewModel(private val repository: Repository): ViewModel() {
    fun getPessoa() = repository.getAllPessoa().asLiveData(viewModelScope.coroutineContext)

    fun upsertPessoa(pessoa: Pessoa){
        viewModelScope.launch {
            repository.upsertPessoa(pessoa)
        }
    }

    fun deletePessoa(pessoa: Pessoa){
        viewModelScope.launch {
            repository.deletePessoa(pessoa)
        }
    }

}