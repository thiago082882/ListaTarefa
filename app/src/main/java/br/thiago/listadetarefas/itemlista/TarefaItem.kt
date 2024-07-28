import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.thiago.listadetarefas.R
import br.thiago.listadetarefas.model.Tarefa
import br.thiago.listadetarefas.ui.theme.Radio_Button_Green_Selected
import br.thiago.listadetarefas.ui.theme.Radio_Button_Yellow_Selected
import br.thiago.listadetarefas.ui.theme.Radio_Button_red_Selected
import br.thiago.listadetarefas.ui.theme.shapeCardPrioridade

@Composable
fun TarefaItem(
    position : Int ,
    listaTarefa : MutableList<Tarefa>
) {
    val tituloTrefa = listaTarefa[position].tarefa
    val descTrefa = listaTarefa[position].desc
    val prioridade = listaTarefa[position].prioridade


    var nivelDePrioridade :String = when(prioridade){
        0->{
         "Sem Prioridade"
        }
        1->{
            "Prioridade Baixa"
        }
        2->{
            "Prioridade Média"
        }
       else->{
           "Prioridade Alta"
        }
    }

    val color = when(prioridade){
        0->{
           Color.Black
        }
        1->{
            Radio_Button_Green_Selected
        }
        2->{
         Radio_Button_Yellow_Selected
        }
        else->{
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
            val (txtTitulo, txtDesc, cardPrioridade, txtPrioridade, btnDeletar) = createRefs()
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

            },
                modifier = Modifier.constrainAs(btnDeletar){
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
        }
    }
}

