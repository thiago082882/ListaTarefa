package br.thiago.listadetarefas.datasource

import android.util.Log
import br.thiago.listadetarefas.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private  val todasTarefas : StateFlow<MutableList<Tarefa>> = _todasTarefas
    fun salvartarefa(tarefa: String, desc: String, prioridade: Int,checkTarefa: Boolean) {

        val tarefaHashmap = hashMapOf(
            "tarefa" to tarefa,
            "desc" to desc,
            "prioridade" to prioridade,
            "checkTarefa" to checkTarefa

        )
        db.collection("tarefas").document(tarefa)
            .set(tarefaHashmap).addOnCompleteListener {

            }.addOnFailureListener {

            }


    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {

        val listaTarefas: MutableList<Tarefa> = mutableListOf()
        db.collection("tarefas").get().addOnCompleteListener { querySnapshot ->
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
        db.collection("tarefas").document(tarefa).delete().addOnCompleteListener {

        }.addOnFailureListener {

        }
    }
    fun atualizarEstadoTarefa(tarefa: String,checkTarefa: Boolean) {
        db.collection("tarefas").document(tarefa).update("checkTarefa", checkTarefa)
            .addOnCompleteListener {

            }.addOnFailureListener {

            }
    }
}