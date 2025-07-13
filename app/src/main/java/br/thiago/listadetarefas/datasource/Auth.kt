package br.thiago.listadetarefas.datasource

import br.thiago.listadetarefas.listener.ListenerAuth
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class Auth @Inject constructor() {

    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    val _verificarUsuarioLogado = MutableStateFlow(false)
    val verificarUsuarioLogado : StateFlow<Boolean> = _verificarUsuarioLogado

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

                    }.addOnFailureListener {exception ->
                        val erro = when(exception){

                            is FirebaseAuthUserCollisionException -> "Está conta já foi cadastrada"
                            is FirebaseAuthWeakPasswordException -> "Digite uma senha com o minimo de 6 caracteres"
                            is FirebaseNetworkException -> "Sem conexão com a internet"
                            else -> "E-mail inválido"

                        }
                    listenerAuth.onFail(erro)


                }
        }

    }

    fun login(
        email: String,
        senha: String,
        listenerAuth: ListenerAuth
    ){
        if(email.isEmpty() || senha.isEmpty()){
            listenerAuth.onFail("Preencha todos os campos!")
        }else{
            auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
                if(it.isSuccessful){
                    listenerAuth.onSuccess("Login realizado com sucesso","listaTarefas")
                }

            }.addOnFailureListener {
                val erro = when(it){
                    is FirebaseAuthInvalidCredentialsException -> "A senha está incorreta"
                    is FirebaseNetworkException -> "Sem conexão com a internet"
                    else -> "E-mail inválido"

                }
                listenerAuth.onFail(erro)
            }


        }


    }
    fun verificarUsuarioLogado():Flow<Boolean>{

        val usuarioAtual = auth.currentUser

        _verificarUsuarioLogado.value = usuarioAtual != null

        return verificarUsuarioLogado

    }
}