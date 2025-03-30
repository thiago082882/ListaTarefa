package br.thiago.listadetarefas.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.thiago.listadetarefas.ui.theme.BlueLight
import br.thiago.listadetarefas.ui.theme.DARK_BLUE
import br.thiago.listadetarefas.ui.theme.DARK_PINK
import br.thiago.listadetarefas.ui.theme.White
import br.thiago.listadetarefas.ui.theme.shapeButton

@Composable
fun BotaoAuth(
    onClick: () -> Unit,
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 20.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(DARK_PINK, DARK_BLUE)
                ),
                shape = shapeButton.medium
            )
    ) {
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp
            ),
            shape = shapeButton.medium,
            border = BorderStroke(2.dp, Color.White)
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun BotaoAuthPreview() {
    BotaoAuth(
        onClick = {},
        text = "Login"
    )

    
}