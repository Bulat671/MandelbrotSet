package controls

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.xml.sax.InputSource
import java.awt.image.BufferedImage

@Composable
fun workWithVideoDialog(
    imageList: SnapshotStateList<BufferedImage>,
    close:()->Unit,
) {
    var height by remember { mutableStateOf(0) }
    var width by remember { mutableStateOf(0) }
    var fps by remember { mutableStateOf(0) }
    var duration by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(MaterialTheme.colors.surface, shape = MaterialTheme.shapes.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Текст в диалоговом окне
            Text(
                "Параметры Видео",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
            )
            //Закрытие
            IconButton(
                onClick = close,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Закрыть",
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            intTextField(
                value = height,
                label = "Высота",
                onValueChange = {height=it},
                maxValue = 10000,
                modifier = Modifier.weight(1f)
            )
            intTextField(
                value = width,
                label = "Ширина",
                onValueChange = {width=it},
                maxValue = 10000,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            intTextField(
                value = fps,
                label = "FPS",
                onValueChange = {fps=it},
                minValue = 1,
                maxValue = 240,
                modifier = Modifier.weight(1f)
            )
            intTextField(
                value = duration,
                label = "Длительность, c",
                onValueChange = {duration=it},
                maxValue = 10*60*60,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .height(45.dp)
            ) {
                Text("Создать")
            }
            Button(
                onClick = {

                },
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .height(45.dp)
            ) {
                Text("Очистить")
            }
        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 10.dp, vertical = 2.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ){
//
//        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(imageList) {
                MyCard(it)
            }
        }
    }
}

@Composable
fun MyCard(bufferedImage: BufferedImage) {
    Card(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.primarySurface
    ){
        val composeBitmap = bufferedImage.toComposeImageBitmap()
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawIntoCanvas { canvas ->
                canvas.withSave {
                    canvas.drawImage(composeBitmap, Offset.Zero, Paint())
                    canvas.translate(composeBitmap.width.toFloat(), 0f)
                }
            }
        }
    }
}