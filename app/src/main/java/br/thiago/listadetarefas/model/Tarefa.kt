package br.thiago.listadetarefas.model

data class Tarefa(
    val tarefa: String? = "",
    val desc: String? = null,
    val prioridade: Int? = null
) {
}