package br.thiago.listadetarefas.repository

import br.thiago.listadetarefas.datasource.DataSource

class TarefasRepositorio() {

    private val dataSource= DataSource()
    fun salvarTarefa(tarefa: String, desc: String, prioridade: Int) {
        dataSource.salvartarefa(tarefa, desc, prioridade)
    }
}