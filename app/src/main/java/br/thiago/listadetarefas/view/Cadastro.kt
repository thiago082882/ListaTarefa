package br.thiago.listadetarefas.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.thiago.listadetarefas.R
import br.thiago.listadetarefas.components.BotaoAuth
import br.thiago.listadetarefas.listener.ListenerAuth
import br.thiago.listadetarefas.ui.theme.DARK_BLUE
import br.thiago.listadetarefas.ui.theme.DARK_PINK
import br.thiago.listadetarefas.ui.theme.PurpleGrey80
import br.thiago.listadetarefas.ui.theme.ShapeEditText
import br.thiago.listadetarefas.ui.theme.White
import br.thiago.listadetarefas.ui.theme.color1
import br.thiago.listadetarefas.ui.theme.color2
import br.thiago.listadetarefas.ui.theme.color3
import br.thiago.listadetarefas.ui.theme.color4
import br.thiago.listadetarefas.viewmodel.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(navController: NavController,viewModel: AuthViewModel= hiltViewModel()) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.background(
            brush = Brush.linearGradient(
                listOf(
                    color1,
                    color2,
                    color3,
                    color4
                )
            )
        ),
        containerColor = Color.Transparent,

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            var nome by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var senha by remember { mutableStateOf("") }
            var visibilidadeSenha by remember { mutableStateOf(false) }
            var mensagem by remember { mutableStateOf("") }
            var icon = if (visibilidadeSenha)
                painterResource(id = R.drawable.ic_visibility)
            else
                painterResource(id = R.drawable.ic_visibility_off)

            Text(
                text = "Cadastro",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White


            )
            OutlinedTextField(
                value = nome,
                onValueChange = {
                    nome = it

                },
                label = {
                        Text(text = "Nome")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = White,
                    textColor = Color.Black,
                    cursorColor = PurpleGrey80,
                    focusedBorderColor = PurpleGrey80,
                    focusedLabelColor = PurpleGrey80,
                ),
                shape = ShapeEditText.small,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "nome"
                    )
                },
                maxLines = 1

            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it

                },
                label = {
                    Text(text = "E-mail")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = White,
                    textColor = Color.Black,
                    cursorColor = PurpleGrey80,
                    focusedBorderColor = PurpleGrey80,
                    focusedLabelColor = PurpleGrey80,
                ),
                shape = ShapeEditText.small,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "E-mail"
                    )
                },
                maxLines = 1

            )

            OutlinedTextField(
                value = senha,
                onValueChange = {
                    senha = it

                },
                label = {
                    Text(text = "Senha")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = White,
                    textColor = Color.Black,
                    cursorColor = PurpleGrey80,
                    focusedBorderColor = PurpleGrey80,
                    focusedLabelColor = PurpleGrey80,
                ),
                shape = ShapeEditText.small,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        visibilidadeSenha = !visibilidadeSenha

                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Senha"
                        )
                    }

                },
                maxLines = 1,
                visualTransformation = if (visibilidadeSenha) VisualTransformation.None else PasswordVisualTransformation()

            )

            Text(
                text = mensagem,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(10.dp))

            BotaoAuth(onClick = {
                viewModel.cadastrar(nome,email, senha,object :ListenerAuth{
                    override fun onSuccess(mensagem: String,tela:String) {
                        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
                        navController.navigate(tela)
                    }

                    override fun onFail(erro: String) {
                      mensagem = erro
                    }

                })
                }, text = "Cadastrar")


            Spacer(modifier = Modifier.padding(0.dp,0.dp,0.dp,20.dp))


        }
    }
}