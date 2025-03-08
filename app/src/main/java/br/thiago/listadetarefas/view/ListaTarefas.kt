package br.thiago.listadetarefas.view

import TarefaItem
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBarDefaults.containerColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.thiago.listadetarefas.R
import br.thiago.listadetarefas.model.Tarefa
import br.thiago.listadetarefas.repository.TarefasRepositorio
import br.thiago.listadetarefas.ui.theme.Black
import br.thiago.listadetarefas.ui.theme.PurpleGrey80
import br.thiago.listadetarefas.ui.theme.White
import br.thiago.listadetarefas.viewmodel.TarefaViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefas(navController: NavController,viewModel: TarefaViewModel = hiltViewModel()) {


    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = PurpleGrey80
                )
            )

        },
        containerColor = Black,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("salvarTarefa")
                },
                containerColor = PurpleGrey80,
                shape = CircleShape


            ) {

                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "Icone de salvar tarefa"
                )

            }
        }
    ) {
        val listaTarefas = viewModel.recuperarTarefas().collectAsState(mutableListOf()).value

        LazyColumn(
            modifier = Modifier.padding(top = 60.dp)
        ) {
            itemsIndexed(listaTarefas) { position, _ ->
                TarefaItem(position = position, listaTarefa = listaTarefas, context = context,navController=navController,viewModel=viewModel)
            }


        }
    }
}