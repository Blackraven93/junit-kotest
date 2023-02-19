package com.example.junitandkotest


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.junitandkotest.ui.theme.JunitAndKoTestTheme
import kotlin.math.roundToInt

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

@Composable
fun ColorBox(modifier: Modifier) {
    Box(modifier = Modifier
        .padding(1.dp)
        .size(width = 50.dp, height = 10.dp)
        .then(modifier))
}

fun Modifier.exampleLayout(
    fraction: Float
) = layout { measurable, constraints  ->
    val placeable = measurable.measure(constraints)

    val x = -(placeable.width * fraction).roundToInt()

    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x = x, y = 0)
    }
}

@Composable
fun DoNotingLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x=0, y=0)
            }
        }

    }
}

@Composable
fun CascadeLayout(
    spacing: Int = 0,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        var indent = 0
        layout(constraints.maxWidth, constraints.maxHeight) {
            var yCoord = 0
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            placeables.forEach { placeable ->
                placeable.placeRelative(x = indent, y = yCoord)
                indent += placeable.width + spacing
                yCoord += placeable.height + spacing
            }
        }
    }
}
@Composable
fun MainScreen() {
    ConstraintLayout(Modifier.size(width = 200.dp, height = 200.dp)) {
        val (button1, button2, button3) = createRefs()

//        MyButton(text = "Button1", Modifier.constrainAs(button1)
//        {
//            top.linkTo(parent.top, margin = 60.dp)
//            start.linkTo(parent.start, margin = 30.dp)
//        })
        MyButton(text = "Button1", Modifier.constrainAs(button1) {
            centerVerticallyTo(parent)
        })

        MyButton(text = "Button2", Modifier.constrainAs(button2) {
            centerVerticallyTo(parent)
        })

        MyButton(text = "Button3", Modifier.constrainAs(button3) {
            centerVerticallyTo(parent)
        })
    }

    ConstraintLayout(Modifier.size(width = 400.dp, height = 100.dp)) {
        val (button1, button2, button3) = createRefs()

        createHorizontalChain(button1, button2, button3, chainStyle = ChainStyle.SpreadInside)
    }

    ConstraintLayout(Modifier.size(width = 400.dp, height= 200.dp)) {
        val (button1, button2, button3) = createRefs()

        val guide = createGuidelineFromStart(fraction = .60f)

        MyButton(text = "Button1", Modifier.constrainAs(button1) {
            top.linkTo(parent.top, margin = 30.dp)
            end.linkTo(guide, margin = 30.dp)
        })

        MyButton(text = "Button2", Modifier.constrainAs(button2) {
            top.linkTo(button1.bottom, margin = 20.dp)
            end.linkTo(guide, margin = 40.dp)
        })

        MyButton(text = "Button1", Modifier.constrainAs(button3) {
            top.linkTo(button2.bottom, margin = 40.dp)
            end.linkTo(guide, margin = 20.dp)
        })
    }
}

@Composable
fun MyButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier
    ) {
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    JunitAndKoTestTheme {
        DoNotingLayout(Modifier.padding(8.dp)) {
            Text("Text Line 1")
            Text("Text Line 2")
            Text("Text Line 3")
            Text("Text Line 4")
        }

//        Box(contentAlignment= Alignment.Center,
//            modifier = Modifier.size(120.dp, 80.dp)) {
//            Column {
//                ColorBox(
//                    Modifier
//                        .exampleLayout(0f)
//                        .background(Color.Blue))
//                ColorBox(
//                    Modifier
//                        .exampleLayout(0.25f)
//                        .background(Color.Green))
//                ColorBox(
//                    Modifier
//                        .exampleLayout(0.5f)
//                        .background(Color.Yellow))
//                ColorBox(
//                    Modifier
//                        .exampleLayout(0.25f)
//                        .background(Color.Red))
//                ColorBox(
//                    Modifier
//                        .exampleLayout(0.0f)
//                        .background(Color.Magenta))
//            }
//        }

//        Box {
//            CascadeLayout(spacing = 20) {
//                Box(modifier = Modifier
//                    .size(60.dp)
//                    .background(Color.Blue))
//                Box(modifier = Modifier
//                    .size(80.dp, 40.dp)
//                    .background(Color.Red))
//                Box(modifier = Modifier
//                    .size(90.dp, 100.dp)
//                    .background(Color.Cyan))
//                Box(modifier = Modifier
//                    .size(50.dp)
//                    .background(Color.Magenta))
//                Box(modifier = Modifier
//                    .size(70.dp)
//                    .background(Color.Green))
//            }
//        }
        MainScreen()
    }
}


