package com.example.junitandkotest


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.junitandkotest.ui.theme.JunitAndKoTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JunitAndKoTestTheme {

            }
        }
    }
}

@Composable
fun CustomImage(image: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier
    )
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier, fontSize: Int = 150) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(width = 5.dp, color = Color.Black)
    Surface {
        Text(text = text,
            cellModifier.then(Modifier),
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {

    JunitAndKoTestTheme {
        Box(
            modifier = Modifier.size(height = 90.dp, width = 290.dp)
            ) {
            Text("TopStart", Modifier.align(Alignment.TopStart))
            Text("TopCenter", Modifier.align(Alignment.TopCenter))
            Text("TopEnd", Modifier.align(Alignment.TopEnd))

            Text("CenterStart", Modifier.align(Alignment.CenterStart))
            Text("Center", Modifier.align(Alignment.Center))
            Text("CenterEnd", Modifier.align(Alignment.CenterEnd))

            Text("BottomStart", Modifier.align(Alignment.BottomStart))
            Text("BottomCenter", Modifier.align(Alignment.BottomCenter))
            Text("BottomEnt", Modifier.align(Alignment.BottomEnd))
        }
    }
}


