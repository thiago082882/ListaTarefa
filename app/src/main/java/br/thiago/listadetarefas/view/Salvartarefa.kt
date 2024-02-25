package br.thiago.listadetarefas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.thiago.listadetarefas.components.Botao
import br.thiago.listadetarefas.components.CaixaTexto
import br.thiago.listadetarefas.ui.theme.PurpleGrey80
import br.thiago.listadetarefas.ui.theme.Radio_Button_Green_Disabled
import br.thiago.listadetarefas.ui.theme.Radio_Button_Green_Selected
import br.thiago.listadetarefas.ui.theme.Radio_Button_Yellow_Disabled
import br.thiago.listadetarefas.ui.theme.Radio_Button_Yellow_Selected
import br.thiago.listadetarefas.ui.theme.Radio_Button_red_Disabled
import br.thiago.listadetarefas.ui.theme.Radio_Button_red_Selected
import br.thiago.listadetarefas.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarTarefa(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Salvar tarefa",
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
    ) {

        var tituloTarefa by remember {
            mutableStateOf("")
        }

        var DescricaoTarefa by remember {
            mutableStateOf("")
        }

        var semPrioridadeTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeBaixaTarefa by remember {
            mutableStateOf(false)
        }
        var PrioridadeMediaTarefa by remember {
            mutableStateOf(false)
        }
        var PrioridadeAltaTarefa by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            CaixaTexto(
                    value =tituloTarefa,
            onValueChange ={
                tituloTarefa = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 70.dp, 20.dp, 0.dp),
            label ="Titulo tarefa",
            maxlines =1,
            keyboardType = KeyboardType.Text
            )
            CaixaTexto(
                value =DescricaoTarefa,
                onValueChange ={
                    DescricaoTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                label ="Descrição tarefa",
                maxlines =5,
                keyboardType = KeyboardType.Text
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
              Text(text = "Nivel de Prioridade")
                RadioButton(
                    selected = prioridadeBaixaTarefa,
                    onClick = {
                     prioridadeBaixaTarefa = !prioridadeBaixaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Radio_Button_Green_Disabled,
                        selectedColor = Radio_Button_Green_Selected
                    )


                )
                RadioButton(
                    selected = PrioridadeMediaTarefa,
                    onClick = {
                        PrioridadeMediaTarefa = !PrioridadeMediaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Radio_Button_Yellow_Disabled,
                        selectedColor = Radio_Button_Yellow_Selected
                    )


                )
                RadioButton(
                    selected = PrioridadeAltaTarefa,
                    onClick = {
                        PrioridadeAltaTarefa = !PrioridadeAltaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Radio_Button_red_Disabled,
                        selectedColor = Radio_Button_red_Selected
                    )


                )
            }
            Botao(onClick = {


            },
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp),
                texto = "Salvar"
            )
        }
    }

}