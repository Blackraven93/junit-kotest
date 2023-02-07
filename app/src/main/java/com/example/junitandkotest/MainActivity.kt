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
fun TextCell(text: String, modifier: Modifier = Modifier) {
    val cellModifier = Modifier
        .padding(4.dp)
        .size(100.dp, 100.dp)
        .border(width = 4.dp, color = Color.Black)

    Text(text = text,
        cellModifier.then(Modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val modifier = Modifier
        .padding(all = 10.dp)
        .border(width = 2.dp, color = Color.Black)
    val secondModifier = Modifier.height(100.dp)
    JunitAndKoTestTheme {
        Column() {
            Row(
                modifier = Modifier.size(width = 400.dp, height = 400.dp),
                horizontalArrangement = Arrangement.Center,

                verticalAlignment = Alignment.CenterVertically


            ) {
                TextCell(text = "1", Modifier.align(Alignment.Top))
                TextCell(text = "2", Modifier.align(Alignment.CenterVertically))
                TextCell(text = "3", Modifier.align(Alignment.Bottom))
            }
            Text(
                "Hello Compose",
                modifier.then(secondModifier),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Row {
                Text(
                    text = "Large Text\nMore Text",
                    Modifier.alignBy(LastBaseline),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Small Text",
                    modifier = Modifier.paddingFrom(
                        alignmentLine = FirstBaseline,
                        before = 80.dp, after = 0.dp
                    ),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
//            Spacer(modifier = Modifier.height(16.dp))
//                CustomImage(R.drawable.vacation)
        }
    }
}

