import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import br.thiago.listadetarefas.R
import br.thiago.listadetarefas.model.Tarefa
import br.thiago.listadetarefas.repository.TarefasRepositorio
import br.thiago.listadetarefas.ui.theme.Radio_Button_Green_Selected
import br.thiago.listadetarefas.ui.theme.Radio_Button_Yellow_Selected
import br.thiago.listadetarefas.ui.theme.Radio_Button_red_Selected
import br.thiago.listadetarefas.ui.theme.shapeCardPrioridade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TarefaItem(
    position: Int,
    listaTarefa: MutableList<Tarefa>,
    context: Context,
    navController: NavController
) {
    val tituloTrefa = listaTarefa[position].tarefa
    val descTrefa = listaTarefa[position].desc
    val prioridade = listaTarefa[position].prioridade
    var tarefaConcluida = listaTarefa[position].checkTarefa

    val scope = rememberCoroutineScope()
    val tarefasRepositorio = TarefasRepositorio()

    var isChecked by remember { mutableStateOf(tarefaConcluida) }

    fun dialogDeletar() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog
            .setTitle("Deletar Tarefa")
            .setMessage("Deseja realmente deletar a tarefa?")
            .setPositiveButton("Sim") { _, _ ->

                tarefasRepositorio.deletarTarefa(tituloTrefa.toString())
                scope.launch(Dispatchers.Main) {
                    listaTarefa.removeAt(position)
                    navController.navigate("listaTarefas")
                    Toast.makeText(context, "Deletado com sucesso", Toast.LENGTH_SHORT).show()
                }


            }
            .setNegativeButton("Não") { _, _ ->
                Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
            }
            .show()

    }


    var nivelDePrioridade: String = when (prioridade) {
        0 -> {
            "Sem Prioridade"
        }

        1 -> {
            "Prioridade Baixa"
        }

        2 -> {
            "Prioridade Média"
        }

        else -> {
            "Prioridade Alta"
        }
    }

    val color = when (prioridade) {
        0 -> {
            Color.Black
        }

        1 -> {
            Radio_Button_Green_Selected
        }

        2 -> {
            Radio_Button_Yellow_Selected
        }

        else -> {
            Radio_Button_red_Selected
        }
    }
    Card(
        colors = CardDefaults.cardColors(
            Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)

        ) {
            val (txtTitulo, txtDesc, cardPrioridade, txtPrioridade, btnDeletar, checkTarefa) = createRefs()
            Text(
                text = tituloTrefa.toString(),
                modifier = Modifier.constrainAs(txtTitulo) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = descTrefa.toString(),
                modifier = Modifier.constrainAs(txtDesc) {
                    top.linkTo(txtTitulo.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )
            Text(
                text = nivelDePrioridade,
                modifier = Modifier.constrainAs(txtPrioridade) {
                    top.linkTo(txtDesc.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card(

                colors = CardDefaults.cardColors(
                    containerColor = color
                ),
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardPrioridade) {
                        top.linkTo(txtDesc.bottom, margin = 10.dp)
                        start.linkTo(txtPrioridade.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = shapeCardPrioridade.large
            ) {

            }
            IconButton(onClick = {
                dialogDeletar()
            },
                modifier = Modifier.constrainAs(btnDeletar) {
                    top.linkTo(txtDesc.bottom, margin = 10.dp)
                    start.linkTo(cardPrioridade.end, margin = 30.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }

            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = null
                )
            }
            Checkbox(
                checked = isChecked!!,
                onCheckedChange = {
                    isChecked = it
                    scope.launch(Dispatchers.IO) {
                        if (isChecked!!) {
                            tarefasRepositorio.atualizarTarefa(tituloTrefa!!, true)
                        } else {
                            tarefasRepositorio.atualizarTarefa(tituloTrefa!!, false)
                        }
                    }

                },
                modifier = Modifier.constrainAs(checkTarefa) {
                    start.linkTo(btnDeletar.end, margin = 10.dp)
                    top.linkTo(txtDesc.bottom, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Radio_Button_Green_Selected,
                    uncheckedColor = Color.Black
                ),
            )

        }
    }

}

