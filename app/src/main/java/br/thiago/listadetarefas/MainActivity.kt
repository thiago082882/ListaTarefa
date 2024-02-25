package br.thiago.listadetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.thiago.listadetarefas.ui.theme.ListaDeTarefasTheme
import br.thiago.listadetarefas.view.ListaTarefas
import br.thiago.listadetarefas.view.SalvarTarefa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeTarefasTheme {
              val navController = rememberNavController()
                
                NavHost(navController =navController , startDestination ="listaTarefas" ){
                    composable(
                        route= "listaTarefas"
                    ){
                        ListaTarefas(navController)
                    }
                    composable(
                        route = "salvarTarefa"
                    ){
                        SalvarTarefa(navController)
                    }

                }
                
            }
        }
    }
}

