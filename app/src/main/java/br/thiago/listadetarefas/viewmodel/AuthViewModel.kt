package br.thiago.listadetarefas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.thiago.listadetarefas.listener.ListenerAuth
import br.thiago.listadetarefas.repository.AuthRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel  @Inject constructor(
private val repository: AuthRepositorio
):ViewModel(){

    fun cadastrar(nome: String, email: String, senha: String,listenerAuth: ListenerAuth) {
      viewModelScope.launch {
          repository.cadastrar(nome,email, senha, listenerAuth)
      }
    }

    fun logar(email: String, senha: String, listenerAuth: ListenerAuth) {
        viewModelScope.launch {
            repository.login(email, senha, listenerAuth)
        }
    }
    fun verificarUsuarioLogado():Flow<Boolean> {

        return repository.verificarUsuarioLogado()

    }
}