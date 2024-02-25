package br.thiago.listadetarefas.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.thiago.listadetarefas.ui.theme.Black
import br.thiago.listadetarefas.ui.theme.BlueLight
import br.thiago.listadetarefas.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaixaTexto(
    value :String,
    onValueChange:(String)-> Unit,
    modifier: Modifier,
    label :String,
    maxlines : Int,
    keyboardType : KeyboardType

) {

    OutlinedTextField(
        value = value, onValueChange =onValueChange, modifier = modifier, label = {
            Text(text =label )
        },
        maxLines = maxlines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Black,
            focusedBorderColor = BlueLight,
            focusedLabelColor = BlueLight,
            containerColor = White,
            cursorColor = BlueLight
        ),
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType =keyboardType
        )
    )
}

@Preview
@Composable
fun CaixaTextoPreview() {

    CaixaTexto(value="Thiago", onValueChange = {}, modifier = Modifier.fillMaxWidth(), label = "Description",1, keyboardType = KeyboardType.Text)

}