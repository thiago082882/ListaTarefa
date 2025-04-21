package br.thiago.listadetarefas.repository

import br.thiago.listadetarefas.datasource.Auth
import br.thiago.listadetarefas.listener.ListenerAuth
import javax.inject.Inject

class AuthRepositorio  @Inject constructor(
    private  val auth : Auth
){
    fun cadastrar(nome: String,email: String, senha: String,listenerAuth: ListenerAuth) {
        auth.cadastro(nome,email, senha,listenerAuth)
    }
}