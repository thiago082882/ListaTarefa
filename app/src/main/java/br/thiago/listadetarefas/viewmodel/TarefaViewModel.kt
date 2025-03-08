package br.thiago.listadetarefas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.thiago.listadetarefas.model.Tarefa
import br.thiago.listadetarefas.repository.TarefasRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TarefaViewModel @Inject constructor(
    private val  tarefaRepositorio: TarefasRepositorio
) :ViewModel() {

    private val _todasTarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private  val todasTarefas : StateFlow<MutableList<Tarefa>> = _todasTarefas
    fun salvarTarefa(tarefa:String,desc:String,prioridade:Int ,checkTarefa:Boolean){
        viewModelScope.launch {
            tarefaRepositorio.salvarTarefa(tarefa,desc,prioridade,checkTarefa)

        }
    }
    fun recuperarTarefas():Flow<MutableList<Tarefa>>{
        viewModelScope.launch {
            tarefaRepositorio.recuperarTarefas().collect{
                _todasTarefas.value = it

            }
        }
        return  todasTarefas
    }
    fun deletarTarefa(tarefa:String){
        viewModelScope.launch {
            tarefaRepositorio.deletarTarefa(tarefa)
        }

    }
    fun atualizarEstadoTarefa(tarefa:String, checkTarefa:Boolean){
        viewModelScope.launch {
            tarefaRepositorio.atualizarTarefa(tarefa,checkTarefa)
        }
    }


}