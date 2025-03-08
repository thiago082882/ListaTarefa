package br.thiago.listadetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.thiago.listadetarefas.ui.theme.ListaDeTarefasTheme
import br.thiago.listadetarefas.view.ListaTarefas
import br.thiago.listadetarefas.view.SalvarTarefa
import br.thiago.listadetarefas.viewmodel.TarefaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeTarefasTheme {
                val navController = rememberNavController()
                val tarefaViewModel: TarefaViewModel = hiltViewModel()

                NavHost(navController = navController, startDestination = "listaTarefas") {
                    composable(
                        route = "listaTarefas"
                    ) {
                        ListaTarefas(navController, tarefaViewModel)
                    }
                    composable(
                        route = "salvarTarefa"
                    ) {
                        SalvarTarefa(navController, tarefaViewModel)
                    }

                }

            }
        }
    }
}

