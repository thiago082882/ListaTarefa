package br.thiago.listadetarefas.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.thiago.listadetarefas.ui.theme.BlueLight
import br.thiago.listadetarefas.ui.theme.White

@Composable
fun Botao(
    onClick: () -> Unit,
    modifier: Modifier,
    texto: String
) {

    Button(onClick, modifier, colors = ButtonDefaults.buttonColors(
        containerColor= BlueLight,
        contentColor = White
    )) {

        Text(text = texto, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }

}