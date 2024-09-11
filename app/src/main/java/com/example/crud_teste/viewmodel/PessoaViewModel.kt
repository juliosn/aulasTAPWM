package com.example.crud_teste.viewmodel

import androidx.lifecycle.ViewModel
import com.example.crud_teste.model.Pessoa
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.crud_teste.repository.PessoaRepository
import kotlinx.coroutines.launch

class PessoaViewModel(private val repository: PessoaRepository) : ViewModel() {

    // Função para adicionar uma pessoa
    fun addPessoa(
        pessoa: Pessoa,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            repository.addPessoa(pessoa, onSuccess, onFailure)
        }
    }

    // Função para buscar todas as pessoas
    fun getAllPessoas(
        onSuccess: (List<Pair<String, Pessoa>>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getAllPessoas(onSuccess, onFailure)
    }

    // Função para atualizar uma pessoa
    fun updatePessoa(
        id: String,
        pessoa: Pessoa,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            repository.updatePessoa(id, pessoa, onSuccess, onFailure)
        }
    }

    // Função para deletar uma pessoa
    fun deletePessoa(
        id: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            repository.deletePessoa(id, onSuccess, onFailure)
        }
    }

    // Factory para criar o ViewModel
    class Factory(private val repository: PessoaRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PessoaViewModel(repository) as T
        }
    }
}
