package br.thiago.listadetarefas.datasource

import br.thiago.listadetarefas.listener.ListenerAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class Auth @Inject constructor() {

    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    fun cadastro(
        nome: String,
        email: String,
        senha: String,
        listenerAuth : ListenerAuth
    ){
        if(nome.isEmpty() || email.isEmpty()  ||  senha.isEmpty()){
            listenerAuth.onFail("Preencha todos os campos!")

        }else{
            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        var usuarioId = FirebaseAuth.getInstance().currentUser?.uid.toString()
                       val dadosUsuarioMap = hashMapOf(
                           "nome" to nome,
                           "email" to email,
                           "usuarioId" to usuarioId
                       )
                        db.collection("usuarios").document(usuarioId).set(dadosUsuarioMap).addOnCompleteListener {
                                listenerAuth.onSuccess("Cadastro realizado com sucesso","login")
                        }.addOnFailureListener {
                            listenerAuth.onFail("Erro ao cadastrar usuário")
                        }
                    }

                    }
        }

    }
}