package br.thiago.listadetarefas.repository

import br.thiago.listadetarefas.datasource.DataSource
import br.thiago.listadetarefas.model.Tarefa
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TarefasRepositorio @Inject constructor(
    private val dataSource: DataSource
) {


    fun salvarTarefa(tarefa: String, desc: String, prioridade: Int,checkTarefa: Boolean) {
        dataSource.salvartarefa(tarefa, desc, prioridade,checkTarefa)
    }
    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {
        return dataSource.recuperarTarefas()
    }
    fun deletarTarefa(tarefa: String) {
        dataSource.deletarTarefa(tarefa)
    }
    fun atualizarTarefa(tarefa: String,checkTarefa: Boolean) {
        dataSource.atualizarEstadoTarefa(tarefa, checkTarefa)
    }


}