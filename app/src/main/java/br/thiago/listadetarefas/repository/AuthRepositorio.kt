package br.thiago.listadetarefas.repository

import br.thiago.listadetarefas.datasource.Auth
import br.thiago.listadetarefas.listener.ListenerAuth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositorio  @Inject constructor(
    private  val auth : Auth
){
    fun cadastrar(nome: String,email: String, senha: String,listenerAuth: ListenerAuth) {
        auth.cadastro(nome,email, senha,listenerAuth)
    }
    fun login(email: String, senha: String,listenerAuth: ListenerAuth) {
        auth.login(email, senha,listenerAuth)
    }
    fun verificarUsuarioLogado():Flow<Boolean>{

      return  auth.verificarUsuarioLogado()
    }


}