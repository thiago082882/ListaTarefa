package br.thiago.listadetarefas.datasource

import android.util.Log
import br.thiago.listadetarefas.model.Tarefa
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DataSource @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()

    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private  val todasTarefas : StateFlow<MutableList<Tarefa>> = _todasTarefas

    private  val _nome = MutableStateFlow<String>("")
    private  val nome : StateFlow<String> = _nome
    fun salvartarefa(tarefa: String, desc: String, prioridade: Int,checkTarefa: Boolean) {

        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid.toString()


        val tarefaHashmap = hashMapOf(
            "tarefa" to tarefa,
            "desc" to desc,
            "prioridade" to prioridade,
            "checkTarefa" to checkTarefa

        )
        db.collection("tarefas").document(usuarioID)
            .collection("tarefas_usuario").document(tarefa)
            .set(tarefaHashmap).addOnCompleteListener {

            }.addOnFailureListener {

            }


    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {

        val listaTarefas: MutableList<Tarefa> = mutableListOf()
        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid.toString()
        db.collection("tarefas").document(usuarioID).collection("tarefas_usuario")
            .get().addOnCompleteListener { querySnapshot ->
            if (querySnapshot.isSuccessful) {
                for (document in querySnapshot.result!!) {
                    val tarefa = document.toObject(Tarefa::class.java)
                    listaTarefas.add(tarefa)
                    _todasTarefas.value = listaTarefas
                    Log.d("DataSource", "${document.id} => ${document.data}")
                }
            }
        }
        return todasTarefas
    }
    fun deletarTarefa(tarefa: String){
        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid.toString()
        db.collection("tarefas").document(usuarioID)
            .collection("tarefas_usuario").document(tarefa)
            .delete().addOnCompleteListener {

        }.addOnFailureListener {

        }
    }
    fun atualizarEstadoTarefa(tarefa: String,checkTarefa: Boolean) {
        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid.toString()
        db.collection("tarefas").document(usuarioID)
            .collection("tarefas_usuario").document(tarefa)
            .update("checkTarefa", checkTarefa)
            .addOnCompleteListener {

            }.addOnFailureListener {

            }
    }

    fun perfilUsuario():Flow<String>{
        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid.toString()

        db.collection("usuarios").document(usuarioID).get().addOnCompleteListener {
            if(it.isSuccessful){
                val nome = it.result.getString("nome").toString()
                _nome.value = nome
            }
        }
        return nome
    }
}