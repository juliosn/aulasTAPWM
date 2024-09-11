package com.example.crud_teste.repository

import com.example.crud_teste.model.Pessoa
import com.google.firebase.firestore.FirebaseFirestore

class PessoaRepository(private val db: FirebaseFirestore) {

    fun addPessoa(
        pessoa: Pessoa,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("pessoas")
            .add(pessoa)
            .addOnSuccessListener { documentReference ->
                onSuccess(documentReference.id)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun getAllPessoas(
        onSuccess: (List<Pair<String, Pessoa>>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("pessoas")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val lista = querySnapshot.documents.mapNotNull { document ->
                    document.id to document.toObject(Pessoa::class.java)
                }
                onSuccess(lista as List<Pair<String, Pessoa>>)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun updatePessoa(
        id: String,
        pessoa: Pessoa,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("pessoas").document(id)
            .set(pessoa)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun deletePessoa(
        id: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("pessoas").document(id)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
}
