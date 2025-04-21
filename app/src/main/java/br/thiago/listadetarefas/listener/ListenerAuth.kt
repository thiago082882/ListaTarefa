package br.thiago.listadetarefas.listener

interface ListenerAuth {
    fun onSuccess(mensagem: String,tela: String)
    fun onFail(erro: String)
}