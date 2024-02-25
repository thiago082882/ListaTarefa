package br.thiago.listadetarefas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.thiago.listadetarefas.R
import br.thiago.listadetarefas.ui.theme.Black
import br.thiago.listadetarefas.ui.theme.PurpleGrey80
import br.thiago.listadetarefas.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefas(navController: NavController) {


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
                containerColor= PurpleGrey80,
                shape = CircleShape


            ) {
                
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add), contentDescription = "Icone de salvar tarefa")

            }
        }
    ) {

    }
}