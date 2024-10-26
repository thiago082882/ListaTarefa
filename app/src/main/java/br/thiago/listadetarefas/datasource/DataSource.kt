package br.thiago.listadetarefas.datasource

import com.google.firebase.firestore.FirebaseFirestore

class DataSource {

    private val db  = FirebaseFirestore.getInstance()
    fun salvartarefa(tarefa:String , desc : String,prioridade:Int){

        val tarefaHashmap  = hashMapOf(
            "tarefa" to tarefa,
            "desc" to desc,
            "prioridade" to prioridade
        )
      db.collection("tarefas").document(tarefa)
          .set(tarefaHashmap).addOnCompleteListener{

          }.addOnFailureListener{

          }


    }
}